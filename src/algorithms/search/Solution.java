package algorithms.search;

import java.util.ArrayList;

/** This class represent solution for a searchable problem
 */
public class Solution {
    private final ArrayList<AState> path;

    public Solution(ArrayList<AState> path) {
        this.path = path;
    }

    public ArrayList<AState> getSolutionPath(){
        return this.path;
    }
}
