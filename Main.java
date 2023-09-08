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

        /*
         * fools mate
         * chess.move(1, 5, 2, 5);
         * chess.printBoard();
         * 
         * chess.move(6, 4, 4, 4);
         * chess.printBoard();
         * 
         * chess.move(1, 6, 3, 6);
         * chess.printBoard();
         * 
         * chess.move(7, 3, 3, 7);
         * chess.printBoard();
         * 
         * chess.move(1, 0, 2, 0);
         * chess.printBoard();
         * 
         * chess.move(6, 0, 4, 0);
         * chess.printBoard();
         */

        /*
         * castling (king side)
         * chess.move(0, 6, 2, 5);
         * chess.printBoard();
         * 
         * chess.move(6, 5, 4, 5);
         * chess.printBoard();
         * 
         * chess.move(1, 6, 2, 6);
         * chess.printBoard();
         * 
         * chess.move(7,4, 6, 5);
         * chess.printBoard();
         * 
         * chess.move(0,5, 1, 6);
         * chess.printBoard();
         * 
         * chess.move(6, 5, 5,4);
         * chess.printBoard();
         * 
         * chess.move(0, 4, 0, 6);
         * chess.printBoard();
         */

        //castling (queen side)
        chess.move(0, 1, 2, 2);
        chess.printBoard();

        chess.move(6, 4, 4, 4);
        chess.printBoard();

        chess.move(1, 3, 3, 3);
        chess.printBoard();

        chess.move(7, 3, 4, 6);
        chess.printBoard();

        chess.move(1, 1, 3, 1);
        chess.printBoard();

        chess.move(6, 0, 5, 0);
        chess.printBoard();

        chess.move(0, 2, 2, 0);
        chess.printBoard();

        chess.move(6, 7, 5, 7);
        chess.printBoard();

        chess.move(0, 3, 2, 3);
        chess.printBoard();

        /* block castling 
         * chess.move(6, 6, 5, 6);
         * chess.printBoard();
         */
        /**/chess.move(4, 6, 3, 6);
        /**/chess.printBoard();

        chess.move(0, 4, 0, 2);
        chess.printBoard();

    }
}
