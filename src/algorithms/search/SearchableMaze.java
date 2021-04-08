package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private final Maze maze;

    public SearchableMaze(Maze m) {
        this.maze = m;
    }

    @Override
    public AState getStartState() {
        return new MazeState(this.maze.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(this.maze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        if (!(s instanceof MazeState))
            return null;
        int[][] mazeMap = this.maze.getMap();
        MazeState mazeState = (MazeState) s;
        ArrayList<Position> neighbors = mazeState.getAllNeighbors();
        ArrayList<AState> validNeighbors = new ArrayList<>();

        for (Position p: neighbors) {
            int row = p.getRowIndex(), column = p.getColumnIndex();
            if (row >=0 && row < maze.getYMazeLength() && column >= 0 && column < maze.getXMazeLength() && mazeMap[row][column] != 1)
                validNeighbors.add(new MazeState(p));
        }
        return validNeighbors;
    }
}
