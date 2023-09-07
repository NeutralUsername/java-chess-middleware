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
            getConnection(id).send("i" , id);
            listenForMessages(id);
        }
    }

    public void listenForMessages(String id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String message = connections.get(id).readNextMessage();
                    if (message == null) {
                        break;
                    }
                    handleMessage(id, message.substring(0, 1), message.substring(1));
                }
                removeConnection(id);
            }
        }).start();
    }

    public void handleMessage(String senderId, String messageType, String messageContent) {
        switch (messageType) {
            case "c":
                System.out.println("connect "+messageContent);
                connections.get(senderId).send("e", "unknown id");
                break;
        }
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeConnection(String id) {
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
