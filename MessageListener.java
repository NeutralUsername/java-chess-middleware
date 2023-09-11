public class MessageListener implements Runnable {

    private Connection connection;
    private Server server;
    private boolean running = false;

    public MessageListener(Connection connection, Server server) {
        this.connection = connection;
        System.out.println(server);
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
            String message = connection.readNextMessage();
            if (message == null) {
                break;
            }
            server.handleMessage(connection, message.substring(0, 1), message.substring(1));
        }
        running = false;
        connection.terminate();
    }
}
