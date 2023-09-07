import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Connection {
    private Socket socket;
    private String id;
    private Game game;

    public Connection(Socket socket, String id) {
        this.socket = socket;
        this.id = id;
    }

    public boolean isClosed() {
        return socket.isClosed();
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
