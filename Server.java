import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

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
            String id = utility.generateRandomAlphaNumericString(10);
            acceptConnection(id);
            this.getConnection(id).send("hello " + id + "!");
        }
    }

    public Connection getConnection(String id) {
        return connections.get(id);
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
