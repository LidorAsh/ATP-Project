package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm
    {
        public Position[] DFS(Maze maze )
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
                        Position curr =  stack.pop();

                        solutions[solIndex]= curr;
                        solIndex=solIndex+1;

//                        tempmax=tempmax-1;
//                        if(curr.getRowIndex() == maze.getGoalPosition().getRowIndex() && curr.getColumnIndex() == maze.getGoalPosition().getColumnIndex())
//                            {
//
//                                System.out.println("Found the End!!!");
//                                System.out.println("Number of Nodes Expanded: " + NumberofNodesExpanded);
//                                System.out.println("Max tree depth searched: " + depth);
//                                System.out.println("Max frontier size: "+maxfrontier);
//                                System.out.println();
//
//                                return;
//                            }



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
                return solutions;
            }

        @Override
        public Solution solve(ISearchable iSearchable) {
            Stack<AState> toHandle = new Stack<>();
            HashSet<AState> visited = new HashSet<>();
            ArrayList<AState> solutionSteps = new ArrayList<>();

            boolean findSolution = false;

            AState start = iSearchable.getStartState();
            AState end = iSearchable.getGoalState();

            toHandle.push(start);
            visited.add(start);
//            start.setVisited(true);

            AState current = null;
             while (!toHandle.empty()){
//            for (int j = 0; j < 10; j++){
                current = toHandle.pop();


                //System.out.println(current);
                if (current.equals(end)){
                    findSolution = true;
                    break;
                }


                ArrayList<AState> neighbors = iSearchable.getAllSuccessors(current);
                for (int i = 0; i < neighbors.size(); i++) {
                    AState nextNeighbor = neighbors.get(i);
                    if(!visited.contains(nextNeighbor)) {
                        this.NumberOfNodesEvaluated++;
//                    nextNeighbor.setVisited(true);
                        visited.add(nextNeighbor);
                        nextNeighbor.setCameFrom(current);
                        toHandle.push(nextNeighbor);
                    }
                }
            }


            if(findSolution){
                while(current.getCameFrom() != null){
                    solutionSteps.add(current);
                    current = current.getCameFrom();
                }
                solutionSteps.add(new MazeState(new Position(1,0)));

            }

            Collections.reverse(solutionSteps);
            return new Solution(solutionSteps);
        }

        @Override
        public String getName() {
            return "DepthFirstSearch";
        }

        @Override
        public int getNumberOfNodesEvaluated() {
            return this.NumberOfNodesEvaluated;
        }
    }
