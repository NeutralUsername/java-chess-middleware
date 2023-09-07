package chess;

import java.util.ArrayList;

import chess.pieces.Pawn;
import chess.pieces.Rook;
import chess.pieces.Knight;
import chess.pieces.Bishop;
import chess.pieces.Queen;
import chess.pieces.King;

public class Chess {

    private Field[][] board;
    private ArrayList<Actions> movements = new ArrayList<Actions>();

    public Chess() {
        board = new Field[8][8];
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Field(1, i, new Pawn(true));
            board[6][i] = new Field(6, i, new Pawn(false));
        }
        board[0][0] = new Field(0, 0, new Rook(true));
        board[0][1] = new Field(0, 1, new Knight(true));
        board[0][2] = new Field(0, 2, new Bishop(true));
        board[0][3] = new Field(0, 3, new Queen(true));
        board[0][4] = new Field(0, 4, new King(true));
        board[0][5] = new Field(0, 5, new Bishop(true));
        board[0][6] = new Field(0, 6, new Knight(true));

        board[0][7] = new Field(0, 7, new Rook(true));
        board[7][0] = new Field(7, 0, new Rook(false));
        board[7][1] = new Field(7, 1, new Knight(false));
        board[7][2] = new Field(7, 2, new Bishop(false));
        board[7][3] = new Field(7, 3, new Queen(false));
        board[7][4] = new Field(7, 4, new King(false));
        board[7][5] = new Field(7, 5, new Bishop(false));
        board[7][6] = new Field(7, 6, new Knight(false));
        board[7][7] = new Field(7, 7, new Rook(false));
    }
}
