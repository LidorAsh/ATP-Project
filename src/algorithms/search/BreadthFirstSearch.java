package algorithms.search;

import algorithms.mazeGenerators.Position;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
        @Override
        public Solution solve(ISearchable iSearchable) {
            Queue<AState> toHandle = new ArrayDeque<>();
            HashSet<AState> visited = new HashSet<>();
            ArrayList<AState> solutionSteps = new ArrayList<>();

            boolean findSolution = false;

            AState start = iSearchable.getStartState();
            AState end = iSearchable.getGoalState();
            toHandle.add(start);
            visited.add(start);

            AState current = null;
            while (!toHandle.isEmpty()){
                current = toHandle.poll();

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
                        toHandle.add(nextNeighbor);
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
            return "BreadthFirstSearch";
        }

        @Override
        public int getNumberOfNodesEvaluated() {
            return this.NumberOfNodesEvaluated;
        }

    }
