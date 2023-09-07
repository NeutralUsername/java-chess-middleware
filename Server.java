import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private HashMap<String, Connection> connections;
    private ServerSocket serverSocket;

    public Server(int port) {
        connections = new HashMap<String, Connection>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenForConnections() {
        while (true) {
            Connection connection = acceptNextConnection();
            if (connection == null) {
                continue;
            }
            connection.sendMessage("i", connection.getId());
            listenForMessages(connection);
        }
    }

    public Connection acceptNextConnection() {
        try {
            Socket socket = serverSocket.accept();
            String id = utility.generateRandomAlphaNumericString(10);
            while (getConnection(id) != null) {
                id = utility.generateRandomAlphaNumericString(10);
            }
            Connection connection = new Connection(socket, id);
            addConnection(connection);
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
                terminateConnection(connection);
            }
        }).start();
    }

    public void handleMessage(Connection senderConnection, String messageType, String messageContent) {
        switch (messageType) {
            case "c":
                Connection receiverConnection = getConnection(messageContent);
                if (receiverConnection == null) {
                    senderConnection.sendMessage("e", "unknown id");
                } else if (senderConnection == receiverConnection) {
                    senderConnection.sendMessage("e", "invalid id");
                } else {
                    if (receiverConnection.isIngame() || senderConnection.isIngame()) {
                        senderConnection.sendMessage("e", "already in game");
                    } else {
                        Chess game = new Chess(senderConnection.getId(), receiverConnection.getId());
                        senderConnection.setGame(game);
                        receiverConnection.setGame(game);
                        senderConnection.sendMessage("g", messageContent);
                        receiverConnection.sendMessage("g", senderConnection.getId());
                    }
                }
                break;
            case "m": {
                System.out.println("test");
            }
        }
    }

    public void terminateConnection(Connection connection) {
        connection.close();
        removeConnection(connection.getId());
    }

    public void terminateServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addConnection(Connection connection) {
        connections.put(connection.getId(), connection);
    }

    public void removeConnection(String id) {
        connections.remove(id);
    }

    public Connection getConnection(String id) {
        return connections.get(id);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
