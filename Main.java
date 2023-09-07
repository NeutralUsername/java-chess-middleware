public class Main {
    public static void main(String[] args) {

        Server server = new Server(4711);
        if (server.getServerSocket() == null) {
            return;
        }
        server.listenForConnections();
    }
}
