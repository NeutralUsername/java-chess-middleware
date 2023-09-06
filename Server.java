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
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String rndString = new String(array, Charset.forName("UTF-8"));
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
