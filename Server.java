import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4711);
            for (int i = 0; i < 10; i++) {
                Connection connection = new Connection(server.accept(), "testname" + i);
                connection.send("Hello Client " + i + "!");    
                connection.close();
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
