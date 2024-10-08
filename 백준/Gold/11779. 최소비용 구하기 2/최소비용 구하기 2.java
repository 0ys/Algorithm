import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int S, E;
	static int[] DP, prevNode;
	static ArrayList<Integer> path;
	static boolean[] visited;
	static ArrayList<Node>[] graphs;
	static class Node implements Comparable<Node>{
		int to, cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	public static void main(String[] args) throws Exception {
		//A도시에서 B도시로 가는데 드는 최소비용과 경로를 출력하라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		prevNode = new int[N+1];
		visited = new boolean[N+1];
		DP = new int[N+1];
		Arrays.fill(DP, Integer.MAX_VALUE);
		
		graphs = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graphs[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graphs[a].add(new Node(b, c));
 		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		BFS(S);
		//System.out.println(Arrays.toString(DP));
		
		int theCost = DP[E];
		System.out.println(theCost); // 출발 도시에서 도착 도시까지 가는데 드는 최소 비용
		
		// 최소 비용을 갖는 경로를 방문하는 도시를 찾기
		//System.out.println(Arrays.toString(prevNode));
		path = new ArrayList<>();
		int a = E;
		while(a != S) {
			path.add(a);
			a = prevNode[a];
		}
		path.add(S);
		System.out.println(path.size());
		for(int i=path.size()-1; i>=0; i--) {
			System.out.print(path.get(i)+" ");
		}
	}
	
	static void BFS(int start) {
		Queue<Node> que = new PriorityQueue<>();
		que.add(new Node(start, 0));
		DP[start] = 0;
		
		while(!que.isEmpty()) {
			Node a = que.poll();
			int now = a.to;
			int nowCost = a.cost;
			
			if(nowCost > DP[now]) continue;
			
			for(Node b : graphs[now]) {
				int next = b.to;
				int nextCost = b.cost;
				
				if(!visited[next] && DP[next] > nextCost+nowCost) {
					DP[next] = nextCost+nowCost;
					prevNode[next] = now;
					que.add(new Node(next, nextCost+nowCost));
				}
			}
		}
	}

}