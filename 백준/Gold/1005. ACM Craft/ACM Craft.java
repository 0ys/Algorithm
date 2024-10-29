import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, victory;
	static ArrayList<Integer>[] graphs;
	static int[] buildTime, indegree, DP;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 건물 개수
			K = Integer.parseInt(st.nextToken()); // 건설순서 규칙=엣지
			
			graphs = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				graphs[i] = new ArrayList<>();
			}
			
			buildTime = new int[N+1];
			indegree = new int[N+1];
			DP = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graphs[a].add(b);
				indegree[b]++;
			}
			
			victory = Integer.parseInt(br.readLine());
			//System.out.println(Arrays.toString(buildTime));
			//System.out.println(Arrays.toString(indegree));
			solve();
			//System.out.println(Arrays.toString(DP));
			
			sb.append(DP[victory]+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void solve() {
		Queue<Integer> que = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				que.add(i);
				DP[i] = buildTime[i];
			}
		}
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for(int next:graphs[now]) {
				indegree[next]--;
				
				if(DP[next] < DP[now]+buildTime[next]) {
					DP[next] = DP[now]+buildTime[next];
				}
				if(indegree[next] == 0) {
					que.add(next);
				}
			}
		}
	}

}