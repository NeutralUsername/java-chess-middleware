import java.net.Socket;

import chess.Chess;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Connection {
    private Socket socket;
    private String id;

    private Chess game;
    private Connection opponent;
    private Boolean isWhite;

    public Connection(Socket socket, String id) {
        this.socket = socket;
        this.id = id;
    }

    public Chess getGame() {
        return game;
    }

    public Boolean isWhite() {
        return isWhite;
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public void setOpponent(Connection opponent) {
        this.opponent = opponent;
    }

    public Connection getOpponent() {
        return opponent;
    }

    public String readNextMessage() {
        try {
            InputStream in = socket.getInputStream();
            String message = "";
            do {
                int byt = in.read();
                if (byt == -1) {
                    return null;
                }
                if (byt == 0) {
                    return message;
                }
                message += (char) byt;
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isIngame() {
        return game != null;
    }

    public String getId() {
        return id;
    }

    public void setGame(Chess game, Boolean isWhite) {
        this.game = game;
        this.isWhite = isWhite;
    }

    public void sendMessage(String messageType, String messageContent) {
        try {
            OutputStream stream = socket.getOutputStream();
            byte bmessage[] = (messageType + messageContent + "\0").getBytes();
            stream.write(bmessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
