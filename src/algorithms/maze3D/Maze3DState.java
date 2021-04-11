package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.MazeState;
import java.util.ArrayList;
import java.util.Objects;

public class Maze3DState extends AState {
    private Position3D position3D;

    public Maze3DState(Position3D position3D) {
        super();
        this.position3D = position3D;
    }

    public ArrayList<Position3D> getAllNeighbors(){
        return this.position3D.getNeighborsOfPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Maze3DState)) return false;
        Maze3DState mazeState = (Maze3DState) o;
        return position3D.equals(mazeState.position3D);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position3D);
    }

    @Override
    public String toString() {
        return this.position3D.toString();
    }
}
