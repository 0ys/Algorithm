import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, maps[][];
	static int size=2, eat=0, ans=0;
	static Point shark, fish;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static Queue<Fish> fishPath;
	static class Fish implements Comparable<Fish> {
		int x, y, len;
		
		public Fish(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
		
		public int compareTo(Fish o) {
			if(this.len == o.len) {
				if(this.x == o.x) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.len - o.len;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		maps = new int[N][N]; //9=아기상어
		// 자신보다 작은 물고기를 먹음, 크기가 같은 물고기는 지나갈 수 있음, 큰 물고기가 있는 칸은 지나갈 수 없음
		// 가까운 가장 위, 왼쪽 물고기를 먼저 먹는다.
		// 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if(input == 0) continue;
				else if(input == 9) {
					shark = new Point(i, j);
				} else {
					M++;
					maps[i][j] = input;
				}
			}
		}
		
		while(M>0) {
			fishPath = new PriorityQueue<Fish>();
			BFS(shark);
			if(fishPath.size() > 0) {
				Fish fish = fishPath.poll();
				eat++;
				if(eat == size) {
					eat = 0;
					size++;
				}
				M--;
				ans += fish.len;
				maps[fish.x][fish.y] = 0;
				shark = new Point(fish.x, fish.y);
			} else {
				break;
			}
			//print();
			//System.out.println("남은 물고기:"+M+" 상어크기: "+size+" 먹은 물고기: "+eat+" 이동거리: "+ans);
		}
		
		System.out.println(ans);
	}
	
	private static void BFS(Point start) {
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] {start.x, start.y, 0});
		boolean[][] visited = new boolean[N][N];
		visited[start.x][start.y] = true;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			
			//if(M == 0) return false;
			if(maps[now[0]][now[1]] != 0 && maps[now[0]][now[1]]<size) {
				//len = now[2];
				//fish = new Point(now[0], now[1]);
				fishPath.add(new Fish(now[0], now[1], now[2]));
				//return true;
			}
			
			for(int i=0; i<4; i++) {
				int nx = now[0]+dx[i];
				int ny = now[1]+dy[i];
				
				if(0<=nx && nx<N && 0<=ny && ny<N && maps[nx][ny]<=size && !visited[nx][ny]) {
					visited[nx][ny] = true;
					que.add(new int[] {nx, ny, now[2]+1});
				}
			}
		}
		
		//return false;
	}
	
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(maps[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---------");
	}

}