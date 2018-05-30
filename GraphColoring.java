import java.util.*;

public class GraphColoring {
	int nodes; //노드의 개수 nodes
	int[][] adjacent; //인접 행렬 adjacent
	int colors;  //색깔의 개수 colors
	int[] solution; //solution vector
	int totalVisit=1;
	int totalNode;
	
	public static void main(String[] args) {
		GraphColoring hw3 = new GraphColoring();
		hw3.run();
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("<input>"); 
		nodes = scanner.nextInt(); 
		adjacent = new int[nodes][nodes];
		for (int i=0; i<nodes; i++) {
			for (int j=0; j<nodes; j++)
				adjacent[i][j] = scanner.nextInt();
		}
		colors = scanner.nextInt();
		
		System.out.println("<output>");
		
		solution = new int[nodes];
		for(int i=0; i<nodes; i++) //solution vector 초기화
			solution[i] = 0;
		
		int k = 0;
		
		while (-1<k && k<nodes) {
			getnext(k, solution);
			if (solution[k] == 0)
				k--;
			else {
				if (k == nodes-1) {
					for(int i=0; i<nodes; i++)
						System.out.print(solution[i] + " ");
					System.out.println();
				}
				else
					k++;
			}
		}
		
		System.out.print(totalVisit + ", ");
		int powerM = colors;
		for (int i=0; i<nodes; i++)
			powerM *= colors;
		totalNode = (powerM-1)/(colors-1);
		System.out.print(totalNode + ", " +  (double)totalVisit/totalNode*100 + "%");
		
		scanner.close();
	}
	
	public void getnext(int k, int[] x) {
		int i = x[k];
		while (i < nodes-1) {
			i++;
			x[k] = i;
			totalVisit++;
			if (bound(k, x))
				return;
		}
		if (i == nodes-1)
			x[k] = 0;
	}
	
	public boolean bound(int k, int[] x) {
		for (int i=0; i<k; i++) {
			if (x[k] == x[i] && adjacent[k][i] == 1)
				return false;
		}
		return true;
	}
}
