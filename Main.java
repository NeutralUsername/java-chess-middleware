import chess.Chess;

public class Main {
    public static void main(String[] args) {

       /*  Server server = new Server(4711);
        if (server.getServerSocket() == null) {
            return;
        }
        server.listenForConnections(); */

        Chess chess = new Chess();
        chess.move(1, 4, 3, 4);
        chess.printBoard();

        chess.move(6, 4, 4, 4);
        chess.printBoard();
    }
}
