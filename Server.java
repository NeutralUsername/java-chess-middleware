import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

public class Server {
    private HashMap<String, Connection> connections;
    private ServerSocket server;

    public Server(int port) {
        connections = new HashMap<String, Connection>();
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenForConnections() {
        while (true) {
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
        
            String rndString = random.ints(leftLimit, rightLimit + 1)
              .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
              .limit(targetStringLength)
              .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
              .toString();
        
            acceptConnection(rndString);
        }
    }

    public void acceptConnection(String id) {
        try {
            Socket socket = server.accept();

            if (connections.containsKey(id)) {
                System.out.println("Connection with id " + id + " already exists!");
                return;
            }

            Connection connection = new Connection(socket, id);
            connections.put(id, connection);
            connection.send("Hello " + id + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(String id) {
        Connection connection = connections.get(id);
        connection.close();
        connections.remove(id);
    }

    public void closeServer() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
