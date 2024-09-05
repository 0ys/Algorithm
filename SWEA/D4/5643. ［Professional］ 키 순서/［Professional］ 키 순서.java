import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static int[] in; // 진입차수
	static int[] cnt; // 키를 비교해본 횟수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			in = new int[N+1];
			cnt = new int[N+1];
			graph = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				in[b]++;
			}
			
			for(int i=1; i<=N; i++) {
				Search(i);
			}
			
			//System.out.println(Arrays.toString(cnt));
			int ans = 0;
			for(int c : cnt) {
				if(c == N-1) ans++;
			}
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void Search(int x) {
		Queue<Integer> que = new ArrayDeque<>();
		que.add(x);
		boolean[] visited = new boolean[N+1];
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for(int next : graph[now]) {
				if(!visited[next]) {
					cnt[x]++;
					cnt[next]++;
					visited[next] = true;
					que.add(next);
				}
			}
		}
	}

}