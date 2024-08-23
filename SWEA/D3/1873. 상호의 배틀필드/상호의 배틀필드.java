import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int H, W;
	static char[][] maps;
	static int[] tank; // x, y, 방향
	static int[] dx = {-1, 1, 0, 0}; // 위, 아래, 왼쪽, 오른쪽
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			maps = new char[H][W];
			for(int i=0; i<H; i++) {
				String input = br.readLine();
				for(int j=0; j<W; j++) {
					char c = input.charAt(j);
					if(c == '^') tank = new int[] {i, j, 0};
					if(c == 'v') tank = new int[] {i, j, 1};
					if(c == '<') tank = new int[] {i, j, 2};
					if(c == '>') tank = new int[] {i, j, 3};
					maps[i][j] = c;
				}
			}
			int N = Integer.parseInt(br.readLine());
			String order = br.readLine();
			for(int i=0; i<N; i++) {
				play(order.charAt(i));
			}
			
			sb.append("#"+t+" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(maps[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		
	}
	
	static void play(char now) {
		if(now == 'U') {
			tank[2] = 0;
			move();
			
		} else if(now == 'D') {
			tank[2] = 1;
			move();
			
		} else if(now == 'L') {
			tank[2] = 2;
			move();
			
		} else if(now == 'R') {
			tank[2] = 3;
			move();
			
		} else if(now == 'S') {
			shoot();
		}
	}
	
	static void move() {
		int x = tank[0];
		int y = tank[1];
		int dir = tank[2];
		
		if(dir == 0) maps[x][y] = '^';
		else if(dir == 1) maps[x][y] = 'v';
		else if(dir == 2) maps[x][y] = '<';
		else if(dir == 3) maps[x][y] = '>';
		
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		if(nx >=0 && nx < H && ny >=0 && ny < W && maps[nx][ny] == '.') {
			tank[0] = nx;
			tank[1] = ny;
			maps[nx][ny] = maps[x][y];
			maps[x][y] = '.';
		}
		
	}
	
	static void shoot() {
		//System.out.println("shoot "+tank[0]+" "+tank[1]+" "+tank[2]);
		int nx = tank[0] + dx[tank[2]];
		int ny = tank[1] + dy[tank[2]];
		
		while(nx >=0 && nx < H && ny >=0 && ny < W) {
			//System.out.println("포탄위치: "+nx+" "+ny);
			if(maps[nx][ny] == '*') {
				maps[nx][ny] = '.';
				break;
			} else if(maps[nx][ny] == '#') {
				break;
			}
			
			nx += dx[tank[2]];
			ny += dy[tank[2]];
		}
	}

}
