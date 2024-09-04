import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, K, maps[][];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int peak = 0;
			maps = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int h = Integer.parseInt(st.nextToken());
					maps[i][j] = h;
					if(h > peak) {
						peak = h;
					}
				}
			}
			
			int max = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(maps[i][j] == peak) {
						//System.out.println("peak "+i+" "+j);
						maxDepth = 0;
						visited = new boolean[N][N];
						DFS(i, j, 1, false);
						//System.out.println(maxDepth);
						max = Math.max(max, maxDepth);
					}
				}
			}
			
			sb.append("#"+tc+" "+max+"\n");
		}
		System.out.println(sb.toString());
	}
	static int maxDepth;
	static boolean[][] visited;
	static void DFS(int x, int y, int cnt, boolean isSelect) {
		//System.out.println("dfs "+x+" "+y+" "+cnt);
		maxDepth = Math.max(maxDepth, cnt);
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i]; 
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
			
			if(!isSelect && !visited[nx][ny] && maps[nx][ny] >= maps[x][y]) {
				if(maps[nx][ny] - (maps[x][y]-1) <= K) {
					int temp = maps[nx][ny]; // 기존의 값 저장
					maps[nx][ny] = maps[x][y]-1; // 현재보다 하나 아래로 깎음
					DFS(nx, ny, cnt+1, true);
					maps[nx][ny] = temp; // 원상복구
				}
			}
			
			if(!visited[nx][ny] && maps[nx][ny] < maps[x][y]) {
				DFS(nx, ny, cnt+1, isSelect);
			}
		}
		visited[x][y] = false;
	}

}