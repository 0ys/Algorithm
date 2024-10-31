import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, maps[][], water[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		maps = new int[N][N];
		water = new int[N][N];
		
		int maxH = 0, minH = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int a = Integer.parseInt(st.nextToken());
				maps[i][j] = a;
				if(maxH < a) maxH = a;
				if(minH > a) minH = a;
			}
		}
		
		//System.out.println(maxH+" "+minH);
		int ans = 0;
		if(maxH == minH) ans = 1;
		for(int h=minH; h<=maxH; h++) {
			init(); //물에 잠긴 지역 water 초기화
			//System.out.print(h);
			rainy(h); //비가 와서 해당 높이 이하가 잠김
			
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(water[i][j] == 0) {
						safeArea(i, j);
						cnt++;
					}
				}
			}
			//System.out.println(" cnt: "+cnt);
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
		
		
		
		
	}
	
	static int safeArea(int x, int y) {
		int[] dx = {1, 0, 0, -1};
		int[] dy = {0, 1, -1, 0};
		
		Queue<Point> que = new ArrayDeque<>();
		que.add(new Point(x, y));
		water[x][y] = 2;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(0<=nx && nx<N && 0<=ny && ny<N) {
					if(water[nx][ny] == 0) {
						water[nx][ny] = 2;
						que.add(new Point(nx, ny));
					}
				}
			}
		}
		return 0;
	}
	
	static void init() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//System.out.print(water[i][j]);
				water[i][j] = 0;
			}
			//System.out.println();
		}
		//System.out.println("---------");
	}
	
	static void rainy(int height) {
		int[] dx = {1, 0, 0, -1};
		int[] dy = {0, 1, -1, 0};
		
		Queue<Point> que = new ArrayDeque<>();
		que.add(new Point(0, 0)); //무조건 처음부터 찾음
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		if(maps[0][0] <= height) {
			water[0][0] = 1;
		}
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny]) {
					visited[nx][ny] = true;
					que.add(new Point(nx, ny));
					if(maps[nx][ny] <= height) {
						water[nx][ny] = 1;
					}
				}
			}
		}
	}
}