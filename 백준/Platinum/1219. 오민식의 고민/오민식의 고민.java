import java.util.*;
import java.io.*;

public class Main {
	static Edge[] edges;
	static class Edge {
		int s, e, w;
		Edge(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	static long[] DP;
	static int[] city;
	static int N, M, start, end;
	static int MIN = Integer.MIN_VALUE;
	static int MAX = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new Edge[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(s, e, w);
		}
		
		DP = new long[N];
		Arrays.fill(DP, MIN);
		
		city = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		
		DP[start] = city[start];
		for(int i = 0; i < N+100; i++) {
			for(Edge e : edges) {
				int now = e.s;
				int next = e.e;
				int cost = e.w;
				
				if(DP[now] != MIN && DP[next] < DP[now] + city[next] - cost) {
					if(DP[now] == MAX) {
						DP[next] = MAX;
						continue;
					}
					DP[next] = DP[now] + city[next] - cost;
					if(i >= N - 1) {
						DP[next] = MAX;
					}
				}
			}
		}
		
		if(DP[end] == MIN) System.out.println("gg");
		else if(DP[end] == MAX) System.out.println("Gee");
		else System.out.println(DP[end]);
	}
}