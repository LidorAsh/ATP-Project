package algorithms.search;

public class AState {
    private int cost;
    private AState cameFrom;

    public AState(int cost, AState cameFrom) {
        this.cost = cost;
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
}
