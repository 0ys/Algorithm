import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static ArrayList<Node>[] adj;
	static ArrayList<Node>[] reverseAdj;
	static class Node {
		int to, time;
		Node(int to, int time){
			this.to = to;
			this.time = time;
		}
	}
	static int[] degree;
	static long[] DP;
	static int start, end;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[N+1];
		reverseAdj = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
			reverseAdj[i] = new ArrayList<>();
		}
		degree = new int[N+1];
		DP = new long[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[s].add(new Node(e, w));
			reverseAdj[e].add(new Node(s, w));
			degree[e] += 1;
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		Queue<Integer> que = new ArrayDeque<>();
		que.add(start);
		
		while(!que.isEmpty()) {
			int now = que.poll();
			for(Node e : adj[now]) {
				int next = e.to;
				int cost = e.time;
				degree[next]--;
				
				if(DP[next] < DP[now]+cost) {
					DP[next] = DP[now]+cost;
				}
				if(degree[next] == 0) {
					que.add(next);
				}
			}
		}
		
		System.out.println(DP[end]);
		
		int cnt = 0; // 도로의 수
		boolean[] visited = new boolean[N+1];
		Queue<Integer> reverseQue = new ArrayDeque<>();
		reverseQue.add(end);
		visited[end] = true;
		
		while(!reverseQue.isEmpty()) {
			int now = reverseQue.poll();
			for(Node e : reverseAdj[now]) {
				int next = e.to;
				int cost = e.time;
				
				if(DP[now] == DP[next] + cost) {
					cnt++;
					if(!visited[next]) {
						visited[next] = true;
						reverseQue.add(next);
					}
				}
				
			}
		}
		
		System.out.println(cnt);
	}
}
