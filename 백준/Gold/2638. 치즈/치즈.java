import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, maps[][], visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maps = new int[N][M];
		visited = new int[N][M];
		
		int total = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int a = Integer.parseInt(st.nextToken());
				maps[i][j] = a;
				
				if(a == 1) total++;
			}
		}
		
		// 가장자리는 치즈가 놓이지 않는다!
		int ans = 0;
		while(total > 0) {
			reset();
			findCheese(0, 0);
//			for(int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(maps[i]));
//			}
//			System.out.println("---------------------");
			
			total -= melt();
			ans++;
		}
		
		System.out.println(ans);
		
	}
	
	static int melt() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j] >= 2) {
					maps[i][j] = 0;
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void findCheese(int x, int y) {
		int[] dx = {1, 0, 0, -1};
		int[] dy = {0, 1, -1, 0};
		
		Queue<Point> que = new ArrayDeque<Point>();
		que.add(new Point(x, y));
		visited[x][y]++;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(0<=nx && nx<N && 0<=ny && ny<M) {
					if(maps[nx][ny] == 0 && visited[nx][ny] == 0) {
						visited[nx][ny]++;
						que.add(new Point(nx, ny));
					} else if(maps[nx][ny] == 1) {
						visited[nx][ny]++;
					}
				}
			}
		}
	}

	static void reset() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = 0;
			}
		}
	}

}