package algorithms.mazeGenerators;

import java.util.Objects;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRowIndex() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumnIndex() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("{%d, %d}", this.row, this.column);

    }


    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Position) {
            Position temp = (Position) other;
            return (this.row == temp.row && this.column == temp.column);
        }
        else
            return false;
    }
}
