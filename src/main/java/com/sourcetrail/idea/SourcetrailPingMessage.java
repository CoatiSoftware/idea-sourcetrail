package io.sourcetrail.idea;

public class SourcetrailPingMessage {

    boolean isValid = false;

    SourcetrailPingMessage(String message) {
        if(message.contains("<EOM>")) {
            message = message.replace("<EOM>", "");
            String[] split = message.split("\\>\\>");
            if (split[0].equals("ping")) {
                isValid = true;
            }
        }
    }
}
