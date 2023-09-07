package chess;

import java.util.ArrayList;

import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Rook;
import chess.pieces.Knight;
import chess.pieces.Bishop;
import chess.pieces.Queen;
import chess.pieces.King;

public class Chess {

    private Field[][] board;
    private ArrayList<Action> actions = new ArrayList<Action>();

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

    public boolean isWhiteTurn() {
        return actions.size() % 2 == 0;
    }

    public boolean fieldIsUnderAttack(int row, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; i < 8; i++) {
                if (board[i][j].getPiece() != null && board[i][j].getPiece().isWhite() != isWhiteTurn()) {
                    if (isValidMovement(i, j, row, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValidMovement(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (fromRow < 0 || fromRow > 7 || fromColumn < 0 || fromColumn > 7 || toRow < 0 || toRow > 7 || toColumn < 0
                || toColumn > 7) {
            return false;
        }
        Piece piece = board[fromRow][fromColumn].getPiece();
        if (piece == null) {
            return false;
        }
        if (piece.isWhite() != isWhiteTurn()) {
            return false;
        }
        if (piece instanceof Pawn) {
            return isValidPawnMovement(fromRow, fromColumn, toRow, toColumn);
        }
        if (piece instanceof Rook) {
            return isValidRookMovement(fromRow, fromColumn, toRow, toColumn);
        }
        if (piece instanceof Knight) {
            return isValidKnightMovement(fromRow, fromColumn, toRow, toColumn);
        }
        if (piece instanceof Bishop) {
            return isValidBishopMovement(fromRow, fromColumn, toRow, toColumn);
        }
        if (piece instanceof Queen) {
            return isValidQueenMovement(fromRow, fromColumn, toRow, toColumn);
        }
        if (piece instanceof King) {
            return isValidKingMovement(fromRow, fromColumn, toRow, toColumn);
        }

        Piece toPiece = board[toRow][toColumn].getPiece();
        board[toRow][toColumn].setPiece(piece);
        board[fromRow][fromColumn].setPiece(null);

        Field kingField = getKingField();
        boolean kindUnderAttack = fieldIsUnderAttack(kingField.getRow(), kingField.getColumn());

        board[fromRow][fromColumn].setPiece(piece);
        board[toRow][toColumn].setPiece(toPiece);

        return !kindUnderAttack;
    }

    private Field getKingField() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; i < 8; i++) {
                if (board[i][j].getPiece() instanceof King && board[i][j].getPiece().isWhite() == isWhiteTurn()) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    private boolean isValidPawnMovement(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (isWhiteTurn()) {
            if (fromRow == 1 && fromColumn == toColumn && toRow == 3 && board[2][toColumn].getPiece() == null
                    && board[3][toColumn].getPiece() == null) {
                return true;
            }
            if (fromRow == toRow + 1 && fromColumn == toColumn && board[toRow][toColumn].getPiece() == null) {
                return true;
            }
            if (fromRow == toRow + 1 && fromColumn == toColumn + 1 && board[toRow][toColumn].getPiece() != null
                    && !board[toRow][toColumn].getPiece().isWhite()) {
                return true;
            }
            if (fromRow == toRow + 1 && fromColumn == toColumn - 1 && board[toRow][toColumn].getPiece() != null
                    && !board[toRow][toColumn].getPiece().isWhite()) {
                return true;
            }
        } else {
            if (fromRow == 6 && fromColumn == toColumn && toRow == 4 && board[5][toColumn].getPiece() == null
                    && board[4][toColumn].getPiece() == null) {
                return true;
            }
            if (fromRow == toRow - 1 && fromColumn == toColumn && board[toRow][toColumn].getPiece() == null) {
                return true;
            }
            if (fromRow == toRow - 1 && fromColumn == toColumn + 1 && board[toRow][toColumn].getPiece() != null
                    && board[toRow][toColumn].getPiece().isWhite()) {
                return true;
            }
            if (fromRow == toRow - 1 && fromColumn == toColumn - 1 && board[toRow][toColumn].getPiece() != null
                    && board[toRow][toColumn].getPiece().isWhite()) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidRookMovement(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (fromRow == toRow) {
            if (fromColumn < toColumn) {
                for (int i = fromColumn + 1; i < toColumn; i++) {
                    if (board[fromRow][i].getPiece() != null) {
                        return false;
                    }
                }
                return board[toRow][toColumn].getPiece() == null
                        || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn();
            } else {
                for (int i = fromColumn - 1; i > toColumn; i--) {
                    if (board[fromRow][i].getPiece() != null) {
                        return false;
                    }
                }
                return board[toRow][toColumn].getPiece() == null
                        || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn();
            }
        }
        if (fromColumn == toColumn) {
            if (fromRow < toRow) {
                for (int i = fromRow + 1; i < toRow; i++) {
                    if (board[i][fromColumn].getPiece() != null) {
                        return false;
                    }
                }
                return board[toRow][toColumn].getPiece() == null
                        || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn();
            } else {
                for (int i = fromRow - 1; i > toRow; i--) {
                    if (board[i][fromColumn].getPiece() != null) {
                        return false;
                    }
                }
                return board[toRow][toColumn].getPiece() == null
                        || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn();
            }
        }
        return false;
    }

    private boolean isValidKnightMovement(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (fromRow == toRow + 2 && fromColumn == toColumn + 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow + 2 && fromColumn == toColumn - 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow - 2 && fromColumn == toColumn + 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow - 2 && fromColumn == toColumn - 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }

        if (fromRow == toRow + 1 && fromColumn == toColumn + 2 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow + 1 && fromColumn == toColumn - 2 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow - 1 && fromColumn == toColumn + 2 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow - 1 && fromColumn == toColumn - 2 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        return false;
    }

    private boolean isValidBishopMovement(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (fromRow < toRow && fromColumn < toColumn) {
            for (int i = 1; i < toRow - fromRow; i++) {
                if (board[fromRow + i][fromColumn + i].getPiece() != null) {
                    return false;
                }
            }
            return board[toRow][toColumn].getPiece() == null
                    || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn();
        }
        if (fromRow < toRow && fromColumn > toColumn) {
            for (int i = 1; i < toRow - fromRow; i++) {
                if (board[fromRow + i][fromColumn - i].getPiece() != null) {
                    return false;
                }
            }
            return board[toRow][toColumn].getPiece() == null
                    || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn();
        }
        if (fromRow > toRow && fromColumn < toColumn) {
            for (int i = 1; i < fromRow - toRow; i++) {
                if (board[fromRow - i][fromColumn + i].getPiece() != null) {
                    return false;
                }
            }
            return board[toRow][toColumn].getPiece() == null
                    || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn();
        }
        if (fromRow > toRow && fromColumn > toColumn) {
            for (int i = 1; i < fromRow - toRow; i++) {
                if (board[fromRow - i][fromColumn - i].getPiece() != null) {
                    return false;
                }
            }
            return board[toRow][toColumn].getPiece() == null
                    || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn();
        }
        return false;
    }

    private boolean isValidQueenMovement(int fromRow, int fromColumn, int toRow, int toColumn) {
        return isValidRookMovement(fromRow, fromColumn, toRow, toColumn)
                || isValidBishopMovement(fromRow, fromColumn, toRow, toColumn);
    }

    private boolean isValidKingMovement(int fromRow, int fromColumn, int toRow, int toColumn) {
        King king = (King) board[fromRow][fromColumn].getPiece();
        if (!king.hasMoved() && !fieldIsUnderAttack(fromRow, fromColumn)) {
            if (isWhiteTurn()) {
                if (fromRow == 0 && fromColumn == 4 && toRow == 0 && toColumn == 6
                        && board[0][5].getPiece() == null
                        && board[0][6].getPiece() == null
                        && board[0][7].getPiece().isWhite()
                        && board[0][7].getPiece() instanceof Rook
                        && !((Rook) board[0][7].getPiece()).hasMoved()
                        && !fieldIsUnderAttack(0, 5)
                        && !fieldIsUnderAttack(0, 6)
                        && !fieldIsUnderAttack(0, 7)) {
                    return true;
                }
                if (fromRow == 0 && fromColumn == 4 && toRow == 0 && toColumn == 2
                        && board[0][1].getPiece() == null
                        && board[0][2].getPiece() == null
                        && board[0][3].getPiece() == null
                        && board[0][0].getPiece().isWhite()
                        && board[0][0].getPiece() instanceof Rook
                        && !((Rook) board[0][0].getPiece()).hasMoved()
                        && !fieldIsUnderAttack(0, 0)
                        && !fieldIsUnderAttack(0, 2)
                        && !fieldIsUnderAttack(0, 3)) {
                    return true;
                }
            } else {
                if (fromRow == 7 && fromColumn == 4 && toRow == 7 && toColumn == 6
                        && board[7][5].getPiece() == null
                        && board[7][6].getPiece() == null
                        && !board[7][7].getPiece().isWhite()
                        && board[7][7].getPiece() instanceof Rook
                        && !((Rook) board[7][7].getPiece()).hasMoved()
                        && !fieldIsUnderAttack(7, 5)
                        && !fieldIsUnderAttack(7, 6)
                        && !fieldIsUnderAttack(7, 7)) {
                    return true;
                }
                if (fromRow == 7 && fromColumn == 4 && toRow == 7 && toColumn == 2
                        && board[7][1].getPiece() == null
                        && board[7][2].getPiece() == null
                        && board[7][3].getPiece() == null
                        && !board[7][0].getPiece().isWhite()
                        && board[7][0].getPiece() instanceof Rook
                        && !((Rook) board[7][0].getPiece()).hasMoved()
                        && !fieldIsUnderAttack(7, 0)
                        && !fieldIsUnderAttack(7, 2)
                        && !fieldIsUnderAttack(7, 3)) {
                    return true;
                }
            }
        }
        if (fromRow == toRow + 1 && fromColumn == toColumn && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow + 1 && fromColumn == toColumn + 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow + 1 && fromColumn == toColumn - 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow && fromColumn == toColumn + 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow && fromColumn == toColumn - 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow - 1 && fromColumn == toColumn && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow - 1 && fromColumn == toColumn + 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        if (fromRow == toRow - 1 && fromColumn == toColumn - 1 && (board[toRow][toColumn].getPiece() == null
                || board[toRow][toColumn].getPiece().isWhite() != isWhiteTurn())) {
            return true;
        }
        return false;
    }
}
