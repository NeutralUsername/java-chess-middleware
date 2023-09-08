import chess.Chess;

public class Main {
    public static void main(String[] args) {

        /*
         * Server server = new Server(4711);
         * if (server.getServerSocket() == null) {
         * return;
         * }
         * server.listenForConnections();
         */

        Chess chess = new Chess();
        chess.move(1, 5, 2, 5);
        chess.printBoard();

        chess.move(6, 4, 4, 4);
        chess.printBoard();

        chess.move(1, 6, 3, 6);
        chess.printBoard();

        chess.move(7, 3, 3, 7);
        chess.printBoard();

        chess.move(1, 0, 2, 0);
        chess.printBoard();
    }
}
