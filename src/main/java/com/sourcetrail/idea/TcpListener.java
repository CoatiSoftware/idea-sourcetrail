package io.sourcetrail.idea;

import com.intellij.ide.impl.ProjectUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileEditor.impl.text.PsiAwareTextEditorImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.impl.IdeFocusManagerImpl;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpListener extends Thread implements ApplicationComponent {

    public TcpListener() {
        super("Sourcetrail plugin TCP listener");
    }

    @NonNls @NotNull
    public String getComponentName() {
        return getClass().getName();
    }

    public void initComponent() {
        start();
    }

    public void disposeComponent() {
        interrupt();
    }

    public void run() {
        SourcetrailOptions option = SourcetrailOptions.getInstance();
        try {
            ServerSocket ss = new ServerSocket(option.getEditorPort(),0, InetAddress.getByName("localhost"));
            Ping.send();
            while(true) {
                Socket socket = ss.accept();
                try {
                    BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = r.readLine().trim();

                    ApplicationManager.getApplication().invokeLater(() -> Logger.getInstance(Ping.class).info("Sourcetrail message received: " + message));

                    SourcetrailPingMessage pingMessage = new SourcetrailPingMessage(message);
                    if (pingMessage.isValid) {
                        Ping.send();
                        continue;
                    }

                    SourcetrailMoveCursorMessage moveCursorMessage = new SourcetrailMoveCursorMessage(message);
                    if(!moveCursorMessage.filename.isEmpty()) {
                        ApplicationManager.getApplication().invokeLater(new Runnable() {
                            public void run() {
                                File f = new File(moveCursorMessage.filename);
                                if (f != null) {
                                    VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(f);
                                    Project project = com.intellij.openapi.project.ProjectUtil.guessProjectForFile(vf);
                                    if(!project.isOpen()) {
                                        ProjectUtil.openOrImport(f.getAbsolutePath(), null, true);
                                    }
                                    if (project != null) {
                                        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, vf);
                                        descriptor.navigateInEditor(project, true);
                                        Editor editor =
                                                ((PsiAwareTextEditorImpl)FileEditorManager.getInstance(project).getSelectedEditor(vf)).getEditor();
                                        LogicalPosition position = new LogicalPosition(moveCursorMessage.row, moveCursorMessage.col);
                                        editor.getCaretModel().moveToLogicalPosition(position);
                                        editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
                                        IdeFocusManagerImpl.findInstance().requestDefaultFocus(true);
                                    }
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
