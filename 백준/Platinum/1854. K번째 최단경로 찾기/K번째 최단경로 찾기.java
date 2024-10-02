import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static PriorityQueue<Integer>[] DP;
	static boolean[] visited;
	static ArrayList<Node>[] adj;
	static class Node implements Comparable<Node>{
		int to, cost;
		public Node(int t, int c) {
			this.to = t;
			this.cost = c;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost; // 내림차순
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		DP = new PriorityQueue[N+1];
		adj = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			DP[i] = new PriorityQueue<>(Collections.reverseOrder());
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b, c));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		DP[1].add(0);
		
		while(!pq.isEmpty()) {
			Node a = pq.poll();
			int now = a.to;
			int nowCost = a.cost;
			
			for(Node b : adj[now]) {
				int next = b.to;
				int nextCost = b.cost + nowCost;
				if(DP[next].size() < K) {
					DP[next].add(nextCost);
					pq.add(new Node(next, nextCost));
				}
				else {
					if(DP[next].peek() > nextCost) {
						DP[next].poll();
						DP[next].add(nextCost);
						pq.add(new Node(next, nextCost));
					}
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(DP[i].size() == K) System.out.println(DP[i].peek());
			else System.out.println(-1);
		}
		
	}
}
