import java.net.Socket;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Connection {
    private Socket socket;
    private String id;

    public Connection(Socket socket, String id) {
        this.socket = socket;
        this.id = id;
    }

    public void listenForMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String message = receiveMessage();
                    if (message != null) {
                        System.out.println("received message: " + message);
                    }
                }
            }
        }).start();
    }

    public String receiveMessage() {
        try {
            while (socket.getInputStream().available() == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            InputStream in = socket.getInputStream();
            byte b[] = new byte[100];
            in.read(b);
            return new String(b);
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
