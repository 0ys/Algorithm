import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 10,000
	static boolean[] isLeaf, visited;
	static ArrayList<Node>[] graph;
	static class Node implements Comparable<Node>{
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return o.cost - this.cost;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		isLeaf = new boolean[N+1];
		graph = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=1; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(a, b, c));
			graph[b].add(new Node(b, a, c));
			isLeaf[a] = false;
			isLeaf[b] = true;
		}
		
		int ans = 0;
		for(int i=1; i<=N; i++) {
			if(isLeaf[i] == true) {
				//System.out.print(i+" ");
				int[] a = BFS(i);
				int[] b = BFS(a[1]);
				ans = b[0];
				break;
			}
		}
		
		System.out.println(ans);
		
	}
	
	private static int[] BFS(int i) {
		Queue<Node> que = new PriorityQueue<>();
		que.add(new Node(0, i, 0));
		Arrays.fill(visited, false);
		visited[i] = true;
		
		int[] max = new int[2];
		while(!que.isEmpty()) {
			Node now = que.poll();
			//System.out.println(now.to+"--"+now.cost);
			if(max[0] < now.cost) {
				max[0] = now.cost;
				max[1] = now.to;
			}
			
			for(Node next : graph[now.to]) {
				if(!visited[next.to]) {
					visited[next.to] = true;
					
					que.add(new Node(0, next.to, now.cost+next.cost));
				}
			}
		}
		//System.out.println("max "+max[0]+" "+max[1]);
		return max;
	}

}