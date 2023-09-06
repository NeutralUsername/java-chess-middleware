import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4711);
            Connection connection = new Connection(server.accept(), "testname1");
            connection.send("Hello Client");    
            connection.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
