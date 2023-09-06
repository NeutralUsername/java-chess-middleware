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
            addConnection(id);
            getConnection(id).send("i" + id);
            listenForMessages(id);
        }
    }

    public void listenForMessages(String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String message = connections.get(id).handleNextMessage();
                    if (message == null) {
                        break;
                    }
                    connections.get(id).send("eunknown id");
                }
                removeConnection(id);
            }
        }).start();
    }

    public Connection getConnection(String id) {
        return connections.get(id);
    }

    public void addConnection(String id) {
        try {
            Socket socket = server.accept();

            if (connections.containsKey(id)) {
                System.out.println("Connection with id " + id + " already exists!");
                return;
            }

            connections.put(id, new Connection(socket, id));

            System.out.println("new connection with id " + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeConnection(String id) {
        System.out.println("closing connection with id " + id);
        if (!connections.containsKey(id)) {
            System.out.println("Connection with id " + id + " does not exist!");
            return;
        }

        connections.get(id).close();
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
