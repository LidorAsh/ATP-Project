package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {
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

            AState current = null;
            while (!toHandle.empty()){
                current = toHandle.pop();

                if (current.equals(end)){
                    findSolution = true;
                    break;
                }

                ArrayList<AState> neighbors = iSearchable.getAllSuccessors(current);
                for (AState nextNeighbor : neighbors) {
                    if (!visited.contains(nextNeighbor)) {
                        this.NumberOfNodesEvaluated++;
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
                solutionSteps.add(start);
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
