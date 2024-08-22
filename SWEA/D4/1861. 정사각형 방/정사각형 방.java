import java.io.*;
import java.util.*;

public class Solution {
	static int N, maps[][];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			
			maps = new int[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
				if(o1[1] == o2[1]) return o1[0] - o2[0];
				return o2[1] - o1[1];
			});
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int cnt = move(i, j, 1);
					//System.out.println(maps[i][j]+" "+cnt);
					pq.add(new int[] {maps[i][j], cnt});
				}
			}
			sb.append("#"+t+" "+pq.peek()[0]+" "+pq.peek()[1]+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int move(int x, int y, int cnt) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
			
			if(maps[nx][ny] - 1 == maps[x][y]) return move(nx, ny, cnt+1);
		}
		return cnt;
	}

}