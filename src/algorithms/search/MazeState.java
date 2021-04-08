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

        //ArrayList<AState> stateArray = new ArrayList<>();
//        ArrayList<Position> positionArray = this.p.getNeighborsOfPosition();
//        for (Position po: positionArray) {
//            stateArray.add(new MazeState(po));
//        }
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

