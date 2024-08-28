import java.util.Scanner;

public class Solution {
	static int[] parents;
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			parents = new int[N+1];
			for(int i=0; i<=N; i++) {
				parents[i] = i;
			}
			
			sb.append("#"+t+" ");
			for(int i=0; i<M; i++) {
				int flag = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				if(flag == 0) Union(a, b);
				else {
					if(Find(a) == Find(b)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				}
			}
			
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void Union(int a, int b) {
		int aRoot = Find(a);
		int bRoot = Find(b);
		if(aRoot != bRoot) {
			parents[aRoot] = bRoot;
		}
	}
	
	static int Find(int x) {
		if(x == parents[x]) return x;
		return parents[x] = Find(parents[x]);
	}

}