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
        chess.move(1, 4, 3, 4);
        chess.printBoard();

        chess.move(6, 4, 4, 4);
        chess.printBoard();

        chess.move(0, 3, 2, 5);
        chess.printBoard();

        /* chess.move(0, 5, 2, 3);
        chess.printBoard(); */

       /*  chess.move(1, 0, 3, 0);
        chess.printBoard();

        chess.move(6,0, 4,0);
        chess.printBoard();

        chess.move(0, 0, 2, 0);
        chess.printBoard(); */
    }
}
