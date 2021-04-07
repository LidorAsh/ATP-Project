package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearch extends Isearcher
    {
        public static void DFS(Maze maze )
            {
                //stack data structure
                Stack<Position> stack = new Stack<>();
                Position[] solutions = new Position[maze.getYMazeLength()* maze.getXMazeLength()];
                int solIndex=0;
/*
                int NumberofNodesExpanded=0;
                int depth =0;
                int maxfrontier= 0;
                int tempmax=0;
                //push the start position on to the stack
*/
                Position start = new Position(maze.getStartPosition().getRowIndex(), maze.getStartPosition().getColumnIndex());
                stack.push( start );
                Route r = new Route(start, new LinkedList<Position>());
                r.getPath().add(start);
                /*tempmax=tempmax+1;

                if(tempmax> maxfrontier)
                    maxfrontier=tempmax;
*/


                while( !stack.isEmpty() )
                    {
                        Position curr = (Position) stack.pop();

                        solutions[solIndex]= curr;
                        solIndex=solIndex+1;
/*
                        tempmax=tempmax-1;
                        if(curr.getRowIndex() == maze.getGoalPosition().getRowIndex() && curr.getColumnIndex() == maze.getGoalPosition().getColumnIndex())
                            {

                                System.out.println("Found the End!!!");
                                System.out.println("Number of Nodes Expanded: " + NumberofNodesExpanded);
                                System.out.println("Max tree depth searched: " + depth);
                                System.out.println("Max frontier size: "+maxfrontier);
                                System.out.println();

                                return;
                            }
*/


                        //add neighbors
                        if( !r.Contains(new Position(curr.getRowIndex(), curr.getColumnIndex())))
                            {
                                //update visited
                                r.getPath().add(new Position(curr.getRowIndex(), curr.getColumnIndex()));
                                //NumberofNodesExpanded=NumberofNodesExpanded+1;



                                //add up neighbor
                                if( canMove(maze, r, "up") )
                                    {
                                        Position temp = new Position(curr.getRowIndex(), curr.getColumnIndex()-1);
                                        stack.push(temp);
                                        r.getPath().add(temp);
                                        /*tempmax=tempmax+1;
                                        if(tempmax> maxfrontier)
                                            maxfrontier=tempmax;

                                        //if(depth < curr.ht+1)
                                        //    depth = curr.ht+1;*/
                                    }


                                //add right neighbor
                                if( canMove(maze, r, "right") )
                                    {
                                        Position temp = new Position(curr.getRowIndex()+1, curr.getColumnIndex());
                                        stack.push(temp);
                                        r.getPath().add(temp);
                                        /*tempmax=tempmax+1;
                                        if(tempmax> maxfrontier)
                                            maxfrontier=tempmax;

                                        //if(depth < curr.ht+1)
                                         //   depth = curr.ht+1;
*/
                                    }

                                //add left neighbor
                                if( canMove(maze, r, "left") )
                                    {
                                        Position temp = new Position(curr.getRowIndex()-1, curr.getColumnIndex());
                                        stack.push(temp);
                                        r.getPath().add(temp);
                                        /*tempmax=tempmax+1;
                                        if(tempmax> maxfrontier)
                                            maxfrontier=tempmax;

                                        //if(depth < curr.ht+1)
                                        //    depth = curr.ht+1;*/
                                    }

                                //add down neighbor
                                if( canMove(maze, r, "down") )
                                    {
                                        Position temp = new Position(curr.getRowIndex(), curr.getColumnIndex()+1);
                                        stack.push(temp);
                                        r.getPath().add(temp);
                                        /*tempmax=tempmax+1;
                                        if(tempmax> maxfrontier)
                                            maxfrontier=tempmax;

                                        //if(depth < curr.ht+1)
                                        //    depth = curr.ht+1;*/
                                    }
                            }
                    }
            }
}
