package io.sourcetrail.idea;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.diagnostic.Logger;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Ping {

    public static void send() {
        SourcetrailOptions options = SourcetrailOptions.getInstance();
        try {
            String text = "ping>>" + ApplicationNamesInfo.getInstance().getFullProductName() + "<EOM>";
            Socket socket = new Socket("localhost", options.getSourcetrailPort());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(text);
            writer.flush();
            socket.close();
        } catch (Exception e) {
            String errorMsg = "No connection to a running Sourcetrail instance. Make sure Sourcetrail is running and the "
                            + "right address is used (localhost:" + options.getSourcetrailPort() + ")";
            ApplicationManager.getApplication().invokeLater(() -> Logger.getInstance(Ping.class).info(errorMsg));
            e.printStackTrace();
        }
    }
}
