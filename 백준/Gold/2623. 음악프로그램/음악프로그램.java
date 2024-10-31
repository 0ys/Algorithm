import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, indegree[];
	static ArrayList<Integer>[] graphs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graphs = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graphs[i] = new ArrayList<>();
		}
		
		indegree = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int from = 0;
			for(int j=0; j<cnt-1; j++) { // edge 개수만큼 받음
				if(from == 0) from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graphs[from].add(to);
				indegree[to]++;
				from = to;
			}
		}
		
		//System.out.println(Arrays.toString(indegree));
		
		Queue<Integer> que = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				que.add(i);
			}
		}
		
		ArrayList<Integer> ans = new ArrayList<>();
		while(!que.isEmpty()) {
			int now = que.poll();
			//System.out.print(now+" ");
			ans.add(now);
			
			for(int next:graphs[now]) {
				indegree[next]--;
				if(indegree[next] == 0) {
					que.add(next);
				}
			}
		}
		
		if(ans.size() != N) {
			System.out.println("0");
		} else {
			for(int a: ans) {
				System.out.println(a);
			}
		}

	}

}