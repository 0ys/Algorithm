import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, C, maps[][], sum[][];
	static class Honey implements Comparable<Honey>{
		int x, y, v;

		public Honey(int x, int y, int v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}

		@Override
		public int compareTo(Honey o) {
			return o.v - this.v; // 내림차순
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			maps = new int[N][N+1];
			sum = new int[N][N+1];
			
			// 합배열을 구함
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					int honey = Integer.parseInt(st.nextToken());
					maps[i][j] = honey;
					if(j==1) sum[i][j] = honey;
					else if(j!=0) sum[i][j] = sum[i][j-1]+honey;
				}
			}
			
			// 벌꿀 통의 가치를 구해 pq에 넣음
			PriorityQueue<Honey> pq = new PriorityQueue<>();
			for(int i=0; i<N; i++) {
				for(int j=M; j<=N; j++) {
					int v = sum[i][j] - sum[i][j-M];
					pq.add(new Honey(i, j, calValue(i, j, v))); // 최종 수익을 값으로 받음
				}
			}
			
			// 가장 큰 수익과 두 번째로 큰 수익을 찾아 정답을 구함
			Honey first = pq.poll();
			Honey second = pq.poll();
			// 겹치는 벌꿀 통일 경우, 다음 두번쨰 값을 찾음
			while(second.x == first.x && (second.y > first.y-M || second.y-M < first.y)) {
				second = pq.poll();
			}
			
			sb.append("#"+t+" "+(first.v+second.v)+"\n");
		}
		System.out.println(sb.toString());
	}
	// 부분집합을 위한 전역변수
	static boolean[] isSelected;
	static int window[], max;
	static int calValue(int x, int y, int v) {
		int cost = 0;
		if(v <= C) { // 만약 채취할 수 있는 양 보다 작을 경우, 그냥 수익을 구함
			for(int i=y; i>y-M; i--) {
				cost += Math.pow(maps[x][i], 2);
			}
		} else { // 전부 채취하지 못할 경우, 부분집합을 구해서 수익을 구함
			max = 0;
			window = new int[M];
			isSelected = new boolean[M];
			for(int i=y, j=0; i>y-M; i--) {
				window[j++] = maps[x][i];
			}
			subset(0, 0);
			cost = max;
		}
		
		return cost;
	}
	
	static void subset(int cnt, int sum) {
		if(cnt == M) {
			if(sum <= C) {
				int cost = 0;
				for(int i=0; i<M; i++) {
					if(isSelected[i]) cost += window[i]*window[i];
				}
				max = Math.max(max, cost);
			}
			return;
		}
		
		isSelected[cnt] = true;
		subset(cnt+1, sum+window[cnt]);
		
		isSelected[cnt] = false;
		subset(cnt+1, sum);
		
	}

}