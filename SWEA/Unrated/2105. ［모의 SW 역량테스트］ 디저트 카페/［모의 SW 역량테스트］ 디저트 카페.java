import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static boolean[] isSelected = new boolean[101];
	static int N; //20
	static int[][] maps;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static Point root;
	static int ans;
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
			
			ans = -1;
			for (int x = 0; x < N; x++) {
				for (int y = 1; y < N-1; y++) {
					root = new Point(x, y);
					//isSelected[maps[x][y]] = true;
					//System.out.println("root "+maps[x][y]+": "+x+" "+y);
//					for(int i=0; i<4; i++) {						
//						DFS(x+dx[i], y+dy[i], (i+1)%4);
//					}
					//boolean[] isSelected = new boolean[101];
					//isSelected[maps[x][y]] = true;
					DFS(x, y, 0, 0);
					//isSelected[maps[x][y]] = false;
					
				}
			}
			
			sb.append("#"+t+" "+ans+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void DFS(int x, int y, int dir, int cnt) {
		if(x<0 || x>=N || y<0 || y>=N) return;
		if(dir == 3 && (root.x == x && root.y == y)) {
			int cafe = 0;
			for(int i=0; i<isSelected.length; i++) {
				if(isSelected[i]) {
					cafe++;
					//System.out.print(i+" ");
				}
			}
			//System.out.println();
			ans = Math.max(ans, cnt);
			return;
		}
		//System.out.println("f");
		//int nx = x + dx[dir];
		//int ny = y + dy[dir];
		int now = maps[x][y];
		if(isSelected[now]) return;
		isSelected[now] = true;
		
		DFS(x + dx[dir], y + dy[dir], dir, cnt+1);
		DFS(x + dx[dir], y + dy[dir], (dir+1)%4, cnt+1);
		
		isSelected[now] = false;
	}
}