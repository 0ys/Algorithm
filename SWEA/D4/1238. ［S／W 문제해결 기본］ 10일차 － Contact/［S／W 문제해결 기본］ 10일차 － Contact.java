import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static ArrayList<Integer>[] graphs;
	static int[] depth;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = 10;
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			depth = new int[101];
			graphs = new ArrayList[101];
			for(int i=1; i<=100; i++) {
				graphs[i] = new ArrayList<>();
			}
			
			N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graphs[from].add(to);
			}

			Queue<Integer> que = new LinkedList<>();
			que.offer(start);
			depth[start] = 1;
			
			while(!que.isEmpty()) {
				int now = que.poll();
				for(int next : graphs[now]) {
					if(depth[next] == 0) {
						depth[next] = depth[now]+1;
						que.offer(next);
					}
				}
			}
			
			int d = 0;
			for(int i=0; i<101; i++) {
				d = Math.max(d, depth[i]);
			}
			
			int max = 0;
			for(int i=0; i<101; i++) {
				if(depth[i] == d) max = i;
			}
			
			
			sb.append("#"+t+" "+max+"\n");
		}
		
		System.out.println(sb.toString());
	}

}