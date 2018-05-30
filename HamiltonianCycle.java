import java.util.*;

public class HamiltonianCycle {
	int nodes;
	int[][] adjacent;
	int colors;
	int[] solution;
	
	public static void main(String[] args) {
		HamiltonianCycle hw3 = new HamiltonianCycle();
		hw3.run();
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("<input>");
		nodes = scanner.nextInt();
		adjacent = new int[nodes+1][nodes+1];
		for (int i=1; i<=nodes; i++) {
			for (int j=1; j<=nodes; j++)
				adjacent[i][j] = scanner.nextInt();
		}
		
		System.out.println("<output>");
		
		solution = new int[nodes+1];
		for (int i=1; i<=nodes; i++)
			solution[i] = 0;
		
		int k = 1;
		
		while (0<k && k<=nodes) {
			getnext(k, solution);
			if (solution[k] == 0)
				k--;
			else {
				if (k==nodes) {
					System.out.print("1-");
					for (int i=1; i<=nodes; i++)
						System.out.print(solution[i]);
					System.out.print("-1");
				}
				else
					k++;
			}
		}	
		scanner.close();
	}
	
	public void getnext(int k, int[] x) {
		int i = x[k];
		while (i < nodes) {
			i++;
			x[k] = i;
			if (bound(k, x))
				return;
		}
		if (i == nodes)
			x[k] = 0;
	}
	
	public boolean bound(int k, int[] x) {
		for (int i=1; i<k; i++) {
			if (x[k] == x[i])
				return false;
		}
		if ((k>1 && adjacent[k][k-1] == 1) || (k==nodes && adjacent[1][nodes] ==1))
			return true;
		else
			return false;
	}
}
