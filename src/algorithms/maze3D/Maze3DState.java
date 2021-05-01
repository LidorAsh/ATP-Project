package algorithms.maze3D;

import algorithms.search.AState;
import java.util.ArrayList;
import java.util.Objects;

public class Maze3DState extends AState {
    private final Position3D position3D;

    //creates a 3D maze state using the 2D maze state constructor
    public Maze3DState(Position3D position3D) {
        super();
        this.position3D = position3D;
    }

    public ArrayList<Position3D> getAllNeighbors(){
        return this.position3D.getNeighborsOfPosition();
    }

    //checks if the states are equal
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Maze3DState)) return false;
        Maze3DState mazeState = (Maze3DState) o;
        return position3D.equals(mazeState.position3D);
    }

    //return the hash code of a state
    @Override
    public int hashCode() {
        return Objects.hash(position3D);
    }

    //return a characteristic representation of the state
    @Override
    public String toString() {
        return this.position3D.toString();
    }
}
