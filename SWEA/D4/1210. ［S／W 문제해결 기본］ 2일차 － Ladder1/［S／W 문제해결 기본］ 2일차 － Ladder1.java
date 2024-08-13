import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] maps;
	static int N = 100;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int t=0; t<10; t++) {
			int tc = Integer.parseInt(br.readLine());
			
			maps = new int[N][N];
			visited = new boolean[N][N];
			
			int end = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int a = Integer.parseInt(st.nextToken());
					maps[i][j] = a;
					
					if(a == 2) {
						end = j; // 도착지점을 저장
					}
				}
			}
			
			// 도착지점부터 DFS 탐색을 수행
			bw.write("#"+tc+" "+Ladder2(99, end)+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	// BFS방식
	static int Ladder(int x, int y) {
		int[] dx = {0, 0, -1};
		int[] dy = {1, -1, 0};
		
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] {x, y});
		visited[x][y] = true;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			
			for(int i=0; i<3; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(nx == 0 && maps[nx][ny] == 1) return ny;
				
				if(maps[nx][ny] == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					que.add(new int[] {nx, ny});
					break;
				}
			}
		}
		return -1;
	}
	// DFS 방식
	static int Ladder2(int x, int y) {
		if(x == 0) return y;
		visited[x][y] = true;
		
		int[] dx = {0, 0, -1};
		int[] dy = {1, -1, 0};
		
		for(int i=0; i<3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
			
			if(maps[nx][ny] == 1 && !visited[nx][ny]) {
				int result = Ladder(nx, ny);
				if(result != -1) return result;
			}
		}
		return -1;
	}

}