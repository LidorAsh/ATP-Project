package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;


public class BreadthFirstSearch extends Isearcher
    {
        public static void BFS(Maze maze)
        {
            Route que = new Route(null, null);
            Position[] solutions = new Position[maze.getYMazeLength()* maze.getXMazeLength()];
            int solIndex=0;

            int NumberofNodesExpanded=0;
            int depth =0;
            int maxfrontier= 0;
            int tempmax=0;


            //push the start position on to the stack
            Position start = new Position(maze.getStartPosition().getRowIndex(), maze.getStartPosition().getColumnIndex());
            que.getPath().add( start );


            tempmax=tempmax+1;

            if(tempmax> maxfrontier)
                maxfrontier=tempmax;


            while( !que.getPath().isEmpty() )
            {
                Position curr = (Position) que.getPath().remove();
                solutions[solIndex]= curr;
                solIndex=solIndex+1;

                tempmax=tempmax-1;
                if(curr.getRowIndex() == maze.getGoalPosition().getRowIndex() && curr.getColumnIndex() == maze.getGoalPosition().getColumnIndex())
                {
                    System.out.println("Found the End!!!");
                    System.out.println("Number of Nodes Expanded: " + NumberofNodesExpanded);
                    System.out.println("Max tree depth searched: " + depth);
                    System.out.println("Max frontier size: "+maxfrontier);
                    System.out.println();

                    //printSol(maze, solutions, curr);

                    return;
                }



                //add neighbors
                if( !que.Contains(new Position(curr.getRowIndex(), curr.getColumnIndex())) )
                {
                    //update visited
                    que.getPath().add(new Position(curr.getRowIndex(), curr.getColumnIndex()));

                    NumberofNodesExpanded=NumberofNodesExpanded+1;

                    //add left neighbor
                    if( canMove(maze,  que, "left") )
                    {
                        Position temp = new Position(curr.getRowIndex()-1, curr.getColumnIndex());
                        que.getPath().add(temp);
                        tempmax=tempmax+1;
                        if(tempmax> maxfrontier)
                            maxfrontier=tempmax;

                        //if(depth < curr.ht+1)
                         //   depth = curr.ht+1;

                    }
                    //add down neighbor
                    if( canMove(maze, que, "down") )
                    {
                        Position temp = new Position(curr.getRowIndex(), curr.getColumnIndex()+1);
                        que.getPath().add(temp);
                        tempmax=tempmax+1;
                        if(tempmax> maxfrontier)
                            maxfrontier=tempmax;
/*
                        if(depth < curr.ht+1)
                            depth = curr.ht+1;*/

                    }

                    //add up neighbor
                    if( canMove(maze, que, "up") )
                    {
                        Position temp = new Position(curr.getRowIndex(), curr.getColumnIndex()-1);
                        que.getPath().add(temp);
                        tempmax=tempmax+1;
                        if(tempmax> maxfrontier)
                            maxfrontier=tempmax;
/*
                        if(depth < curr.ht+1)
                            depth = curr.ht+1;*/
                    }


                    //add right neighbor
                    if( canMove(maze, que, "right") )
                    {
                        Position temp = new Position(curr.getRowIndex()+1, curr.getColumnIndex());
                        que.getPath().add(temp);
                        tempmax=tempmax+1;
                        if(tempmax> maxfrontier)
                            maxfrontier=tempmax;


                        //if(depth < curr.ht+1)
                        //    depth = curr.ht+1;
                    }
                }
            }
        }
    }
