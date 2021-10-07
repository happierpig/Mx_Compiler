package Utils;

import org.antlr.v4.runtime.Token;

public class Position{
    private int row,column;

    public Position(int row, int col) {
        this.row = row;
        this.column = col;
    }

    public Position(Token token) {
        this.row = token.getLine();
        this.column = token.getCharPositionInLine();
    }

    public int getRow() { return row; }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() { return "( row : " + row + ", column : " + column + " )"; }
}