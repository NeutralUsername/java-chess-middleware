import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private HashMap<String, Connection> connections;
    private ServerSocket server;

    public Server() {
        connections = new HashMap<String, Connection>();
        try {
            server = new ServerSocket(4711);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptConnection(String name) {
        try {
            Socket socket = server.accept();
            Connection connection = new Connection(socket, name);
            connections.put(name, connection);
            connection.send("Hello " + name + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
