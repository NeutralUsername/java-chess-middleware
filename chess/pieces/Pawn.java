package chess.pieces;

public class Pawn extends Piece {
    private boolean isWhite;
    private boolean hasMoved = false;

    public Pawn(boolean isWhite) {
        super(isWhite);
    }
}
