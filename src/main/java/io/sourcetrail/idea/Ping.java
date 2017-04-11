package io.sourcetrail.idea;

import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.Project;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Ping {

    public static void send()
    {
        SourcetrailOptions options = SourcetrailOptions.getInstance();
        try
        {
            String text = "ping>>" + ApplicationNamesInfo.getInstance().getFullProductName() + "<EOM>";
            Socket socket = new Socket(options.getIp(), options.getSourcetrailPort());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(text);
            writer.flush();
            socket.close();
        }
        catch(Exception e)
        {
            String errorMsg =
                    "No connection to a Sourcetrail instance\n\n Make sure Sourcetrail is running and the right address is used("
                            + options.getIp() + ":" + options.getSourcetrailPort() + ")";
            Messages.showMessageDialog(errorMsg, "SourcetrailPluginError", Messages.getErrorIcon());
            e.printStackTrace();

        }

    }
}
