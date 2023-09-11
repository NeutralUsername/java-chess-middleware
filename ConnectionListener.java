import java.net.Socket;

public class ConnectionListener implements Runnable {
    private Server server;
    private boolean running = false;

    public ConnectionListener(Server server) {
        this.server = server;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean stop() {
        if (!running) {
            return false;
        }
        running = false;
        return true;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            Socket socket = server.acceptNextSocket();
            String id = utility.generateRandomAlphaNumericString(10);
            while (server.getConnection(id) != null) {
                id = utility.generateRandomAlphaNumericString(10);
            }
            Connection connection = new Connection(server, socket, id);
            server.addConnection(connection);
            connection.sendMessage("i", connection.getId());
            connection.startMessageListener();
        }
    }
}
