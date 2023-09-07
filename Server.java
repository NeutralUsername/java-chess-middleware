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
            Connection connection = addConnection(utility.generateRandomAlphaNumericString(10));
            connection.send("i", connection.getId());
            listenForMessages(connection);
        }
    }

    public void listenForMessages(Connection connection) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String message = connection.readNextMessage();
                    if (message == null) {
                        break;
                    }
                    handleMessage(connection, message.substring(0, 1), message.substring(1));
                }
                removeConnection(connection);
            }
        }).start();
    }

    public void handleMessage(Connection senderConnection, String messageType, String messageContent) {
        switch (messageType) {
            case "c":

                Connection receiverConnection = connections.get(messageContent);
                if (receiverConnection == null) {
                    senderConnection.send("e", "unknown id");
                } else if (senderConnection == receiverConnection) {
                    senderConnection.send("e", "invalid id");
                } else {
                    if (receiverConnection.isIngame() || senderConnection.isIngame()) {
                        senderConnection.send("e", "already in game");
                    } else {
                        senderConnection.send("c", messageContent);
                        connections.get(messageContent).send("c", senderConnection.getId());
                    }
                }
                break;
            case "m": {
                System.out.println("test");
            }
        }
    }

    public Connection getConnection(String id) {
        return connections.get(id);
    }

    public Connection addConnection(String id) {
        try {
            Socket socket = server.accept();
            if (connections.containsKey(id)) {
                System.out.println("Connection with id " + id + " already exists!");
                return null;
            }
            Connection connection = new Connection(socket, id);
            connections.put(id, connection);
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeConnection(Connection connection) {
        connection.close();
        connections.remove(connection.getId());
    }

    public void closeServer() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
