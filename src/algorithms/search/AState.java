package algorithms.search;

public class AState implements Comparable<AState>{
    private int cost;
    private AState cameFrom;

    public AState(int cost, AState cameFrom) {
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    public AState() { }

    public int getCost()
        {
            return cost;
        }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public int compareTo(AState o) {
        return this.getCost() - o.getCost();
    }
}
