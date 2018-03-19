package io.sourcetrail.idea;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ActivateTokenAction extends AnAction {

    public ActivateTokenAction() {
        super("ActivateToken");
    }

    public void actionPerformed(AnActionEvent event) {
        SourcetrailOptions options = SourcetrailOptions.getInstance();
        String MESSAGE_SPLIT_STRING = ">>";
        VirtualFile vFile = event.getData(PlatformDataKeys.VIRTUAL_FILE);
        String fileName = vFile != null ? vFile.getPath() : null;
        LogicalPosition logicalPosition = new LogicalPosition(0,0);
        final Editor editor = event.getData(CommonDataKeys.EDITOR);
        if (editor != null) {
            CaretModel caretModel = editor.getCaretModel();
            if ( caretModel != null) {
                logicalPosition = caretModel.getLogicalPosition();
            }
        }

        String text = "setActiveToken" + MESSAGE_SPLIT_STRING +
                fileName + MESSAGE_SPLIT_STRING
                + (logicalPosition.line + 1) + MESSAGE_SPLIT_STRING + logicalPosition.column + "<EOM>";
        try {
            Socket socket = new Socket("localhost", options.getSourcetrailPort());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(text);
            writer.flush();
            socket.close();
        } catch(Exception e) {
            String errorMsg = "No connection to a running Sourcetrail instance. Make sure Sourcetrail is running and the "
                            + "right address is used (localhost:" + options.getSourcetrailPort() + ")";
            Messages.showMessageDialog(errorMsg, "SourcetrailPluginError", Messages.getErrorIcon());
            e.printStackTrace();
        }

        StatusBar statusbar = WindowManager.getInstance().getStatusBar(PlatformDataKeys.PROJECT.getData(event.getDataContext()));

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder("Location sent to Sourcetrail", MessageType.INFO, null)
                .setFadeoutTime(3000)
                .createBalloon()
                .show(RelativePoint.getCenterOf(statusbar.getComponent()), Balloon.Position.atRight);
    }
}
