package algorithms.search;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQ extends PriorityQueue
    {
        public void add(AState a)
        {
            pQueue.add(a);
        }

        static class MyComparator implements Comparator<AState>
            {
                @Override
                public int compare(AState e1, AState e2)
                {
                    return e1.getCost() - e2.getCost();
                }
            }


        MyComparator comparator = new MyComparator();

        PriorityQueue<AState> pQueue = new PriorityQueue<AState>(7, comparator);
    }
