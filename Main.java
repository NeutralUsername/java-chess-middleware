public class Main {
       public static void main(String[] args) {
        Server server = new Server(4711);
        for (int i = 0; i < 10; i++) {
            server.acceptConnection("testname"+i);
        }
        server.close();
    }
}
