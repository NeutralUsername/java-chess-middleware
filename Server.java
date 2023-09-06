import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4711);
            System.out.println("Server: waiting for connection");
            Socket client = server.accept();
            System.out.println("Server: connected to Client " +
                    client.getInetAddress());
            OutputStream stream = client.getOutputStream();
            String message = "Hello Client";
            byte bmessage[] = message.getBytes();
            stream.write(bmessage);
            stream.flush();
            stream.close();
            client.close();
            server.close();
        } catch (IOException e) {
            // Generelles Exception-Handling zu Demo-Zwecken
            e.printStackTrace();
        }
    }
}
