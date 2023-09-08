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
        chess.printBoard();

        chess.move(1, 4, 2, 4);
        chess.printBoard();

        chess.move(6, 5, 5, 5);
        chess.printBoard();

        chess.move(1, 2, 2, 2);
        chess.printBoard();

        chess.move(6, 3, 5, 3);
        chess.printBoard();

        chess.move(0, 1, 2, 0);
        chess.printBoard();

        chess.move(7, 2, 3, 6);
        chess.printBoard();

        chess.move(0, 3, 2, 1);
        chess.printBoard();

        chess.move(6, 4, 4, 4);
        chess.printBoard();

        chess.move(1, 3, 2, 3);
        chess.printBoard();

        chess.move(6, 7, 5, 7);
        chess.printBoard();

        chess.move(0, 2, 1, 3);
        chess.printBoard();

        chess.move(7, 1, 5, 2);
        chess.printBoard();

        chess.move(0, 4, 0, 2); //fails because bishop is threatening square in between
        chess.printBoard();

        chess.move(0, 5, 1, 4); //success
        chess.printBoard();

        chess.move(5, 2, 3, 1);
        chess.printBoard();

        chess.move(0, 4, 0, 2);  //success because the bishop is no longer threatening the square in between
        chess.printBoard();

        chess.move(3, 1, 1, 0);
        chess.printBoard();

        chess.move(2, 1, 3, 1); //fails because king is in check and this move does not remove the check
        chess.printBoard();

        chess.move(2, 1, 1, 0); //success gets king out of check
        chess.printBoard();

    }
}

/*
 * en passant
 * Chess chess = new Chess();
 * chess.printBoard();
 * 
 * chess.move(1, 0, 2, 0);
 * chess.printBoard();
 * 
 * chess.move(6, 0, 4, 0);
 * chess.printBoard();
 * 
 * chess.move(1, 7, 3, 7);
 * chess.printBoard();
 * 
 * chess.move(4, 0, 3, 0);
 * chess.printBoard();
 * 
 * chess.move(1, 1, 3, 1);
 * chess.printBoard();
 * 
 * chess.move(3, 0, 2, 1);
 * chess.printBoard();
 */

/*
 * fools mate
 * 
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

/*
 * castling (king side) (failed because king has moved)
 * chess.move(0, 6, 2, 5);
 * chess.printBoard();
 * 
 * chess.move(6, 5, 4, 5);
 * chess.printBoard();
 * 
 * chess.move(1, 6, 2, 6);
 * chess.printBoard();
 * 
 * chess.move(7, 4, 6, 5);
 * chess.printBoard();
 * 
 * chess.move(0, 5, 1, 6);
 * chess.printBoard();
 * 
 * chess.move(6, 5, 5, 4);
 * chess.printBoard();
 * 
 * chess.move(0, 4, 0, 5);
 * chess.printBoard();
 * 
 * chess.move(5, 4, 4, 3);
 * chess.printBoard();
 * 
 * chess.move(0, 5, 0, 4);
 * chess.printBoard();
 * 
 * chess.move(4, 3, 3, 2);
 * chess.printBoard();
 * 
 * chess.move(0, 4, 0, 6);
 * chess.printBoard();
 */

/*
 * castling (king side) (failed because rook has moved)
 * chess.move(0, 6, 2, 5);
 * chess.printBoard();
 * 
 * chess.move(6, 5, 4, 5);
 * chess.printBoard();
 * 
 * chess.move(1, 6, 2, 6);
 * chess.printBoard();
 * 
 * chess.move(7, 4, 6, 5);
 * chess.printBoard();
 * 
 * chess.move(0, 5, 1, 6);
 * chess.printBoard();
 * 
 * chess.move(6, 5, 5, 4);
 * chess.printBoard();
 * 
 * chess.move(0, 7, 0, 6);
 * chess.printBoard();
 * 
 * chess.move(5, 4, 4, 3);
 * chess.printBoard();
 * 
 * chess.move(0, 6, 0, 7);
 * chess.printBoard();
 * 
 * chess.move(4, 3, 3, 2);
 * chess.printBoard();
 * 
 * chess.move(0, 4, 0, 6);
 * chess.printBoard();
 */

/*
 * castling (queen side)
 * chess.move(0, 1, 2, 2);
 * chess.printBoard();
 * 
 * chess.move(6, 4, 4, 4);
 * chess.printBoard();
 * 
 * chess.move(1, 3, 3, 3);
 * chess.printBoard();
 * 
 * chess.move(7, 3, 4, 6);
 * chess.printBoard();
 * 
 * chess.move(1, 1, 3, 1);
 * chess.printBoard();
 * 
 * chess.move(6, 0, 5, 0);
 * chess.printBoard();
 * 
 * chess.move(0, 2, 2, 0);
 * chess.printBoard();
 * 
 * chess.move(6, 7, 5, 7);
 * chess.printBoard();
 * 
 * chess.move(0, 3, 2, 3);
 * chess.printBoard();
 * 
 * chess.move(4, 6, 3, 6);
 * chess.printBoard();
 * 
 * chess.move(0, 4, 0, 2);
 * chess.printBoard();
 */

/* castling (queen side)
 * chess.move(1, 4, 2, 4);
 * chess.printBoard();
 * 
 * chess.move(6, 5, 5, 5);
 * chess.printBoard();
 * 
 * chess.move(1, 2, 2, 2);
 * chess.printBoard();
 * 
 * chess.move(6, 3, 5, 3);
 * chess.printBoard();
 * 
 * chess.move(0, 1, 2, 0);
 * chess.printBoard();
 * 
 * chess.move(7, 2, 3, 6);
 * chess.printBoard();
 * 
 * chess.move(0, 3, 2, 1);
 * chess.printBoard();
 * 
 * chess.move(6, 4, 4, 4);
 * chess.printBoard();
 * 
 * chess.move(1, 3, 2, 3);
 * chess.printBoard();
 * 
 * chess.move(3, 6, 4, 5);
 * chess.printBoard();
 * 
 * chess.move(0, 2, 1, 3);
 * chess.printBoard();
 * 
 * chess.move(7, 1, 5, 2);
 * chess.printBoard();
 * 
 * chess.move(0, 4, 0, 2);
 * chess.printBoard();
 */
/*
 * castling (queen side) (king in check after castling)
 * chess.move(0, 1, 2, 2);
 * chess.printBoard();
 * 
 * chess.move(6, 4, 4, 4);
 * chess.printBoard();
 * 
 * chess.move(1, 3, 3, 3);
 * chess.printBoard();
 * 
 * chess.move(7, 3, 4, 6);
 * chess.printBoard();
 * 
 * chess.move(1, 1, 3, 1);
 * chess.printBoard();
 * 
 * chess.move(6, 0, 5, 0);
 * chess.printBoard();
 * 
 * chess.move(0, 2, 2, 0);
 * chess.printBoard();
 * 
 * chess.move(6, 7, 5, 7);
 * chess.printBoard();
 * 
 * chess.move(0, 3, 2, 3);
 * chess.printBoard();
 * 
 * chess.move(6, 6, 5, 6);
 * chess.printBoard();
 * 
 * chess.move(0, 4, 0, 2);
 * chess.printBoard();
 */