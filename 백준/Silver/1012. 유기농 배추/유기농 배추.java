import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, K, maps[][];
	static int earthworm;
	static boolean[][] visited;
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, 1, -1, 0};
	public static void main(String[] args) {
		// 상하좌우로 인접한 배추는 지렁이가 한마리만 있으면 된다.
		// 총 몇마리의 지렁이가 필요한가.
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			earthworm = 0;
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			
			maps = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<K; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				maps[x][y] = 1; // 배추를 심음
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(maps[i][j] == 1 && !visited[i][j]) {
						BFS(new Point(i, j));
						earthworm++;
					}
				}
			}
			
			System.out.println(earthworm);
		}
		
	}
	
	static void BFS(Point start) {
		Queue<Point> que = new ArrayDeque<>();
		que.add(start);
		visited[start.x][start.y] = true;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny] && maps[nx][ny] == 1) {
					visited[nx][ny] = true;
					que.add(new Point(nx, ny));
				}
			}
		}
	}

}