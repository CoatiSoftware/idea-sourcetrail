package io.coati.idea;

public class CoatiMoveCursorMessage {
    int row = 0;
    int col = 0;
    String filename = "";

    CoatiMoveCursorMessage(String message)
    {
        if(message.contains("<EOM>"))
        {
            message = message.replace("<EOM>", "");
            String[] split = message.split("\\>\\>");
            if (split[0].equals("moveCursor"))
            {
                filename = split[1];
                row = Integer.parseInt(split[2]) - 1;
                col = Integer.parseInt(split[3]);
                return;
            }
        }

    }

}
