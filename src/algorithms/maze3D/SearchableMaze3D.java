package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private final Maze3D maze3D;

    public SearchableMaze3D(Maze3D m) {
        this.maze3D = m;
    }

    @Override
    public AState getStartState() {
        return new Maze3DState(this.maze3D.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new Maze3DState(this.maze3D.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        if (!(s instanceof Maze3DState))
            return null;
        int[][][] mazeMap = this.maze3D.getMap();
        Maze3DState mazeState = (Maze3DState) s;
        ArrayList<Position3D> neighbors = mazeState.getAllNeighbors();
        ArrayList<AState> validNeighbors = new ArrayList<>();

        for (Position3D p: neighbors) {
            int depth = p.getDepthIndex(), row = p.getRowIndex(), column = p.getColumnIndex();
            if (row >=0 && row < maze3D.getYMazeLength() && column >= 0 && column < maze3D.getXMazeLength() && depth >= 0 && depth < maze3D.getZMazeLength() && mazeMap[depth][row][column] != 1)
                validNeighbors.add(new Maze3DState(p));
        }
        return validNeighbors;
    }
}
