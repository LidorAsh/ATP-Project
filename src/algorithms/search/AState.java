package algorithms.search;

public class AState {
    private int cost;
    private boolean visited;
    private AState cameFrom;

    public AState(int cost, boolean visited, AState cameFrom) {
        this.cost = cost;
        this.visited = visited;
        this.cameFrom = cameFrom;
    }

    public AState() {

    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
