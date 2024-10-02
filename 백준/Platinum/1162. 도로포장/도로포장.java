import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static ArrayList<Node>[] adj;
	static long[][] distance; // DP : [도시의개수][도로포장개수]
	// 도로포장을 하지 않을 때부터[0] 포장했을 때[k]까지의 거리비용을 전부 저장
	
	static final long INF = Long.MAX_VALUE;
	
	static class Node implements Comparable<Node>{
		int to, cnt;
		long time;
		public Node(int to, long time, int cnt) {
			this.to = to;
			this.time = time;
			this.cnt = cnt; // 도로포장 횟수 k
		}
		@Override
		public int compareTo(Node arg0) {
			return (int)(this.time - arg0.time);
		}	
	}
	
	public static void main(String[] args) throws Exception {
		//InputStream fileInput = new FileInputStream("input.txt");
		//System.setIn(fileInput);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		distance = new long[N+1][K+1];
		adj = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(distance[i], INF);
			adj[i] = new ArrayList<>();
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b, time, 0));
			adj[b].add(new Node(a, time, 0));
		}
		
		dijkstra(1);
		
		long answer = INF;
		for(long d : distance[N]) {
			answer = Math.min(answer, d);
		}
		
		System.out.println(answer);
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		distance[start][0] = 0;
		que.add(new Node(start, 0, 0));
		
		while(!que.isEmpty()) {
			Node a = que.poll();
			int now = a.to;
			long cost = a.time;
			int nowCnt = a.cnt;
			long nowDistance = distance[now][nowCnt];
			
			if(cost>nowDistance) continue;
			
			for(Node e: adj[now]) {
				int next = e.to;
				long nextCost = e.time+nowDistance;
				
				// 도로 포장을 하는 경우
				/*
				 * 도로 포장을 하면, 현재 도로의 time은 0으로 바뀌므로
				 * 지금까지의 최단시간인 nowDistance값으로 비교를 한다.
				 * 그리고 도로를 포장했으므로 [next][nowCnt+1]를 업데이트해준다.
				 */
				if(nowCnt < K && nowDistance < distance[next][nowCnt+1]) {
					distance[next][nowCnt+1] = nowDistance;
					que.add(new Node(next, nowDistance, nowCnt+1));
				}
				
				// 도로 포장을 하지 않은 경우
				if(nextCost<distance[next][nowCnt]) {
					distance[next][nowCnt] = nextCost;
					que.add(new Node(next, nextCost, nowCnt));
				}
			}
			
		}
		
	}
}
