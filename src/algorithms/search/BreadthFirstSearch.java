package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
        @Override
        public Solution solve(ISearchable iSearchable) {
            Queue<AState> toHandle = new ArrayDeque<>(); //contain all the unhandled situations
            HashSet<AState> visited = new HashSet<>(); //contain all the points we visited
            ArrayList<AState> solutionSteps = new ArrayList<>(); //contain the path of the solution

            boolean findSolution = false;

            //importing the start and end position from the given maze and mark the start as visited
            AState start = iSearchable.getStartState();
            AState end = iSearchable.getGoalState();
            toHandle.add(start);
            visited.add(start);

            //handle unhandled points and looking for the end
            AState current = null;
            while (!toHandle.isEmpty()){
                current = toHandle.poll();

                if (current.equals(end)){
                    findSolution = true;
                    break;
                }

                //go throughout all the neighbors and mark as visited and send to handle
                ArrayList<AState> neighbors = iSearchable.getAllSuccessors(current);
                for (AState nextNeighbor : neighbors) {
                    if (!visited.contains(nextNeighbor)) {
                        this.NumberOfNodesEvaluated++;
                        visited.add(nextNeighbor);
                        nextNeighbor.setCameFrom(current);
                        toHandle.add(nextNeighbor);
                    }
                }
            }

            //once found the end, go back and save the path
            if(findSolution){
                while(current.getCameFrom() != null){
                    solutionSteps.add(current);
                    current = current.getCameFrom();
                }
                solutionSteps.add(start);
            }


            //organize the path to be from the start to end and return it
            Collections.reverse(solutionSteps);
            return new Solution(solutionSteps);
        }

        @Override
        public String getName() {
            return "BreadthFirstSearch";
        }

        @Override
        public int getNumberOfNodesEvaluated() {
            return this.NumberOfNodesEvaluated;
        }

    }
