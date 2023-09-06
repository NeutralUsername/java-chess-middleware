import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;

public class Connection {
    private Socket socket;
    private String id;

    public Connection(Socket socket, String id) {
        this.socket = socket;
        this.id = id;
    }

    public void send(String message) {
        try {
            OutputStream stream = socket.getOutputStream();
            byte bmessage[] = message.getBytes();
            stream.write(bmessage);
            stream.flush();
            stream.close();
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
