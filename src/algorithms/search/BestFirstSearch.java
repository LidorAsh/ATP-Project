package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.*;

public class BestFirstSearch extends ASearchingAlgorithm
    {
        public static void GreedySearch(Maze maze )
            {

                Route Gque = new Route(null, null);
                Position[] solutions = new Position[maze.getYMazeLength()* maze.getXMazeLength()];
                int solIndex=0;

                int NumberofNodesExpanded=0;
                int depth =0;
                int maxfrontier= 0;


                //push the start position on to the stack
                Position start = new Position(maze.getStartPosition().getRowIndex(), maze.getStartPosition().getColumnIndex());
                Gque.getPath().add( start );

                ArrayList< Position > frontier = new ArrayList< Position>();

                while( !Gque.getPath().isEmpty() )
                    {
                        Position curr = Gque.getPath().remove();

                        solutions[solIndex]= curr;
                        solIndex=solIndex+1;


                        if(curr.getRowIndex() == maze.getGoalPosition().getRowIndex() && curr.getColumnIndex() == maze.getGoalPosition().getColumnIndex())
                            {
                                //reachedEnd = true;
                                System.out.println("Found the End!!!");
                                System.out.println("Number of Nodes Expanded: " + (NumberofNodesExpanded-3) );
                                System.out.println("Max tree depth searched: " + depth);
                                System.out.println("Max frontier size: "+maxfrontier);
                                System.out.println();

                                return;
                            }

                        if(Gque.getPath().isEmpty() && !frontier.isEmpty())
                            {

                                Position min2 = findMinFrontier( maze, frontier);
                                Gque.getPath().add(min2);

                                if(frontier.size()> maxfrontier)
                                    maxfrontier=frontier.size();
/*
                                if(depth < min2.ht+1)
                                    depth = min2.ht+1;*/
                            }


                        //add neighbors
                        if( !Gque.Contains(new Position(curr.getRowIndex(), curr.getColumnIndex())) )
                            {
                                //update visited
                                Gque.getPath().add(new Position(curr.getRowIndex(), curr.getColumnIndex()));

                                //
                                NumberofNodesExpanded=NumberofNodesExpanded+1;



                                //add left neighbor
                                if( canMove( maze,Gque, "left") )
                                    {
                                        Position temp = new Position(curr.getRowIndex()-1, curr.getColumnIndex());
                                        frontier.add(temp);

                                    }

                                //add down neighbor
                                if( canMove(maze, Gque, "down") )
                                    {
                                        Position temp = new Position(curr.getRowIndex(), curr.getColumnIndex()+1);
                                        frontier.add(temp);

                                    }

                                //add up neighbor
                                if( canMove(maze, Gque, "up") )
                                    {
                                        Position temp = new Position(curr.getRowIndex(), curr.getColumnIndex()-1);
                                        frontier.add(temp);
                                    }


                                //add right neighbor
                                if( canMove(maze, Gque, "right") )
                                    {
                                        Position temp = new Position(curr.getRowIndex()+1, curr.getColumnIndex());
                                        frontier.add(temp);
                                    }


                                Position min = findMinFrontier( maze, frontier);

                                Gque.getPath().add(min);


                                if(frontier.size()> maxfrontier)
                                    maxfrontier=frontier.size();
/*
                                if(depth < min.ht+1)
                                    depth = min.ht+1;*/
                            }
                    }
            }
        private static Position findMinFrontier( Maze maze, ArrayList<Position> frontier) {

            Position minPosition= null;
            int distance;

            for (Position temp : frontier)
                {
                    distance = (Math.abs(temp.getRowIndex() - maze.getGoalPosition().getRowIndex()) + Math.abs(temp.getColumnIndex() - maze.getGoalPosition().getColumnIndex()));

                    if (minPosition == null)
                        {
                            minPosition = temp;
                        } else
                        {
                            int minDist = (Math.abs(minPosition.getRowIndex() - maze.getGoalPosition().getRowIndex()) + Math.abs(minPosition.getColumnIndex() - maze.getGoalPosition().getColumnIndex()));
                            if (minDist >= distance)
                                {
                                    minPosition = temp;
                                }

                        }

                }
            frontier.remove(minPosition);
            return minPosition;
        }

        @Override
        public Solution solve(ISearchable iSearchable) {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public int getNumberOfNodesEvaluated() {
            return 0;
        }
    }
