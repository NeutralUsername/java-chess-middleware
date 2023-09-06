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

    public String handleNextMessage() {
        try {
            InputStream in = socket.getInputStream();
            String message = "";
            do {
                int byt = in.read();
                if (byt == -1) {
                    return null;
                } 
                message += (char) byt;

            } while (in.available() > 0);
            return message;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void send(String message) {
        try {
            OutputStream stream = socket.getOutputStream();
            byte bmessage[] = message.getBytes();
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
