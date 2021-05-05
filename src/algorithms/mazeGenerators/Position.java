package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class represent a position in the maze (using coordinates for row and column)
 */
public class Position implements Serializable {
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

    public ArrayList<Position> getNeighborsOfPosition(){
        ArrayList<Position> neighbors = new ArrayList<>();
        // Clockwise starting from the top
        neighbors.add(new Position(this.row - 1, this.column));
        neighbors.add(new Position(this.row - 1, this.column + 1));
        neighbors.add(new Position(this.row, this.column + 1));
        neighbors.add(new Position(this.row + 1, this.column + 1));
        neighbors.add(new Position(this.row + 1, this.column));
        neighbors.add(new Position(this.row + 1, this.column - 1));
        neighbors.add(new Position(this.row, this.column -1));
        neighbors.add(new Position(this.row - 1, this.column - 1));

        return neighbors;
    }
}











