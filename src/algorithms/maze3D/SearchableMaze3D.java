package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;
import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private final Maze3D maze3D;

    public SearchableMaze3D(Maze3D m) throws IllegalArgumentException{
        if(m == null)
            throw new IllegalArgumentException("3D Maze can not be null");
        this.maze3D = m;
    }

    //get the start position from the maze and return it
    @Override
    public AState getStartState() {
        return new Maze3DState(this.maze3D.getStartPosition());
    }

    //get the goal position from the maze and return it
    @Override
    public AState getGoalState() {
        return new Maze3DState(this.maze3D.getGoalPosition());
    }

    //finds and return all the successors of the current state
    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        if (!(s instanceof Maze3DState))
            return null;
        int[][][] mazeMap = this.maze3D.getMap();
        Maze3DState mazeState = (Maze3DState) s;
        ArrayList<Position3D> neighbors = mazeState.getAllNeighbors();
        ArrayList<AState> validNeighbors = new ArrayList<>();

        //getting all the valid neighbors
        for (Position3D p: neighbors) {
            int depth = p.getDepthIndex(), row = p.getRowIndex(), column = p.getColumnIndex();
            if (row >=0 && row < maze3D.getYMazeLength() && column >= 0 && column < maze3D.getXMazeLength() && depth >= 0 && depth < maze3D.getZMazeLength() && mazeMap[depth][row][column] != 1)
                validNeighbors.add(new Maze3DState(p));
        }
        return validNeighbors;
    }
}
