package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.util.Objects;

public class MazeState extends AState{
    private Position p;

    public MazeState(Position p) {
        super();
        this.p = p;
    }

    public ArrayList<Position> getAllNeighbors(){
        return this.p.getNeighborsOfPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MazeState)) return false;
        MazeState mazeState = (MazeState) o;
        return p.equals(mazeState.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p);
    }

    @Override
    public String toString() {
        return p.toString();
    }
}

