package algorithms.maze3D;

import java.util.ArrayList;
import java.util.Objects;

public class Position3D {
    private int depth;
    private int row;
    private int column;

    public Position3D(int depth, int row, int column) {
        this.row = row;
        this.column = column;
        this.depth = depth;
    }

    //return the depth index
    public int getDepthIndex() {
        return this.depth;
    }

    //return the row index
    public int getRowIndex() {
        return this.row;
    }

    //return the column index
    public int getColumnIndex(){
        return this.column;
    }

    //return a characteristic representation of the position
    @Override
    public String toString() {
        return String.format("{%d, %d, %d}", this.depth, this.row, this.column);
    }

    //checks if the positions are equal
    @Override
    public boolean equals(Object o) {
        if(o instanceof Position3D) {
            Position3D that = (Position3D) o;
            return depth == that.depth && row == that.row && column == that.column;
        }
        else
            return false;
    }

    //return the hash code of a state
    @Override
    public int hashCode() {
        return Objects.hash(depth, row, column);
    }

    //remembers the neighbors of the current position
    public ArrayList<Position3D> getNeighborsOfPosition(){
        ArrayList<Position3D> neighbors = new ArrayList<>();
        // Clockwise starting from the top
        neighbors.add(new Position3D(this.depth, this.row - 1, this.column)); //up
        neighbors.add(new Position3D(this.depth, this.row + 1, this.column)); //down
        neighbors.add(new Position3D(this.depth, this.row, this.column - 1)); //left
        neighbors.add(new Position3D(this.depth, this.row, this.column + 1)); //right
        neighbors.add(new Position3D(this.depth - 1, this.row, this.column)); //inside
        neighbors.add(new Position3D(this.depth + 1, this.row, this.column)); //outside

        return neighbors;
    }
}
