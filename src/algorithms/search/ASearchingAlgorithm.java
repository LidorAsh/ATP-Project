package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm
    {
        protected int NumberOfNodesEvaluated = 0;
        static boolean canMove(Maze m, Route r, String s)
            {
                if(s.equals("right"))
                    {
                        //no wall to the right of the current position and it is inbounds of the walls
                        return (r.getLocation().getRowIndex() + 1 < m.getXMazeLength() - 1) && (!r.Contains(new Position(r.getLocation().getRowIndex() + 1,r.getLocation().getColumnIndex())));
                    }
                if(s.equals("left"))
                    {
                        //no wall to the left of the current position and it is inbounds of the walls
                        return r.getLocation().getRowIndex() - 1 > 0 && (r.Contains(new Position(r.getLocation().getRowIndex() - 1,r.getLocation().getColumnIndex())));
                    }
                if(s.equals("up"))
                    {
                        //no wall above the current position and it is inbounds of the walls
                        return r.getLocation().getColumnIndex() - 1 > 0 && (r.Contains(new Position( r.getLocation().getRowIndex(),r.getLocation().getColumnIndex() - 1 )));
                    }
                if(s.equals("down"))
                    {
                        //no wall to the right of the current position and it is inbounds of the walls
                        return r.getLocation().getColumnIndex() + 1 < m.getYMazeLength() - 1 && (r.Contains(new Position(r.getLocation().getRowIndex(),r.getLocation().getColumnIndex() + 1)));
                    }

                else
                    return false;

            }
    }
