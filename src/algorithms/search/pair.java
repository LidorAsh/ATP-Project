package algorithms.search;


public class pair
	{
		int first, second, parentF, parentS, ht, costFromStart;


		public pair(int f, int s, int pf, int ps, int height) {
			first=f;
			second = s;
			parentF= pf;
			parentS = ps;

			ht= height;
			costFromStart = 1;
			// TODO Auto-generated constructor stub
		}

		public pair(int f, int s, int pf, int ps, int height, int c) {
			first=f;
			second = s;
			parentF= pf;
			parentS = ps;

			ht= height;
			costFromStart = c;
			// TODO Auto-generated constructor stub
		}
}
