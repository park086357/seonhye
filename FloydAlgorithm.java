import java.util.*;

public class Project {
	 public static void main(String[] args) {
		 Project floyd = new Project();
		 floyd.run();
	 }
	 
	 public void run() {
		 Scanner scanner = new Scanner(System.in);
		 
		 System.out.println("node 개수, edge weight 행렬, 시작 node, 종료 node 입력하세요.");
		 String str[] = scanner.nextLine().split("\"");
		 int i,j,k;
		 int n = str[0].charAt(0) - 48; //node 개수
		 int s = str[2*n].charAt(2) - 48; //start node
		 int d = str[2*n].charAt(5) - 48; //destination node
		 int[][] W = new int[n][n]; //weight 행렬
		 for (i=0, k=1; i<n; i++, k+=2) {
			 String a[] = str[k].split(", ");
			 for(j=0; j<n; j++) {
				 W[i][j] = Integer.parseInt(a[j]);
				 if (W[i][j] == -1)
					 W[i][j] = 1000;
			 }
		 } 
		 //n,w,s,d 값 저장 끝
		 
		 int[][] D = new int[n][n];
		 int[][] P = new int[n][n];
		 for(i=0; i<n; i++) {
			 for(j=0; j<n; j++) {
				 D[i][j] = W[i][j];
				 P[i][j] = 0;
			 }
		 }
		 for(k=0; k<n; k++) {
			 for(i=0; i<n; i++) {
				 for(j=0; j<n; j++) {
					 if(D[i][j] > D[i][k] + D[k][j]) {
						 D[i][j] = D[i][k] + D[k][j];
						 P[i][j] = k+1;
					 }
				 }
			 }
		 }
		 System.out.println("D");
		 for (i=0; i<n; i++) {
			 for (j=0; j<n; j++) 
				 System.out.print(D[i][j] + " ");
			 System.out.println();
		 }
		 
		 System.out.println("P");
		 for (i=0; i<n; i++) {
			 for (j=0; j<n; j++) 
				 System.out.print(P[i][j] + " ");
			 System.out.println();
		 }
		 System.out.print("\npath : " + s + "-");
		 path(P,s,d);
		 System.out.print(d);
		 
		 scanner.close();
	 }
	 public void path(int[][] P, int q, int r) {
		 if (P[q-1][r-1] != 0) {
			 path(P, q, P[q-1][r-1]);
			 System.out.print(P[q-1][r-1] + "-");
			 path(P,P[q-1][r-1], r);
		 }
	 }
}
