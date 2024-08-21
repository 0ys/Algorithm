import java.io.*;
import java.util.*;

public class Main {
	static int id, sccCnt; // id : 방문순서, sccCnt: scc의 개수
	static ArrayList<Integer>[] graphs;
	static int[] scc; // 각 정점의 scc 번호
	static int[] visited; // DFS 방문 순서
	static ArrayDeque<Integer> stack = new ArrayDeque<>();
	static ArrayList<Queue<Integer>> result = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			scc = new int[V+1]; // 각 정점의 scc 번호
			visited = new int[V+1]; // DFS 방문순서
			graphs = new ArrayList[V+1];
			for(int i=1; i<=V; i++) {
				graphs[i] = new ArrayList<>();
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graphs[a].add(b);
			}
			
			sccCnt = 0;
			for(int i=1; i<=V; i++) {
				if(visited[i] == 0) { // 한번도 방문하지 않은 노드 탐색
					SCC(i);
				}
			}
			
			//System.out.println("scc "+Arrays.toString(scc));
			//System.out.println("vst "+Arrays.toString(visited));
			
			// scc 배열에 대해 진입차수를 구함
			int ans = 0;
			int[] indegree = new int[sccCnt+1];
			for(int now=1; now<=V; now++) {
				for(int next : graphs[now]) {
					if(scc[now] != scc[next]) indegree[scc[next]]++;
				}
				
			}
			//System.out.println(Arrays.toString(indegree));
			for(int i=1; i<=sccCnt; i++) {
				if(indegree[i] == 0) ++ans;
			}
			sb.append(ans+"\n");
		}
		
		
		
		System.out.println(sb.toString());
		
	}
	// 스택에는 노드의 정점 번호인 now가 관리된다.
	// visited에는 DFS 방문 순서인 id가 순서대로 들어간다.
	// sccCnt에는 sccCnt를 세서 각 scc의 넘버가 들어간다.
	static int SCC(int now) {
		visited[now] = ++id;
		stack.addLast(now);
		int root = visited[now]; // root = 가장 먼저 방문된 노드
		//System.out.println("stack add "+now+", now root "+root);
		
		for(int next : graphs[now]) {
			if(visited[next] == 0) { // 방문하지 않은 노드는 DFS 탐색
				root = Math.min(root, SCC(next));
			} else if(scc[next] == 0) {
				// 이미 방문한 노드라면 해당 DFS의 루트와 현재 now 노드의 루트를 비교함
				root = Math.min(root, visited[next]);
			}
		}
		//System.out.println("root "+root+", v "+visited[now]+", now "+now);
		if(root == visited[now]) {
			Queue<Integer> pq = new PriorityQueue<>();
			++sccCnt;
			while(true) {
				int top = stack.pollLast();
				//System.out.print(top+" ");
				pq.add(top);
				scc[top] = sccCnt;
				if(top == now) break;
			}
			//System.out.println();
			result.add(pq);
		}
		
		return root;
	}
}