package algorithms.search;

public interface ISearchingAlgorithm {
    public Solution solve(ISearchable iSearchable);
    public String getName();
    public int getNumberOfNodesEvaluated();
}
