import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;
	static ArrayList<Integer>[] graphs;
	static boolean[] visited;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			visited = new boolean[N+1];
			graphs = new ArrayList[N+1];
			for(int i=0; i<=N; i++) {
				parents[i] = -1;
				graphs[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graphs[a].add(b);
				
				Union(a, b);
			}
			
			//System.out.println(Arrays.toString(parents));
			int ans = 0;
			for(int i=1; i<=N; i++) {
				if(parents[i] < 0) ans++;
			}
			
			sb.append("#"+t+" "+ans+"\n");
		}
		System.out.println(sb.toString());
	}

	static void Union(int a, int b) {
		int aRoot = Find(a);
		int bRoot = Find(b);
		if(aRoot != bRoot) {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
	}
	
	static int Find(int x) {
		if(parents[x] < 0) return x;
		return parents[x] = Find(parents[x]);
	}
}