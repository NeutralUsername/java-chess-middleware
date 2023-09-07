public class Main {
    public static void main(String[] args) {

        Server server = new Server(4711);
        server.listenForConnections();

        // unreachable currently
        server.terminateServer();
    }
}
