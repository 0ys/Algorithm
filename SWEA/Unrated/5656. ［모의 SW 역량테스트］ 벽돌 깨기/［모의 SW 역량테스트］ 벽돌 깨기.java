import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, W, H, ans;
	static int[][] input;
	static boolean[][] visited;
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, 1, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			ans = Integer.MAX_VALUE;
			input = new int[H][W];
			visited = new boolean[H][W];
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 벽돌 N개만큼 반복
			dropBricks(0, copyMap(input));
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dropBricks(int cnt, int[][] map) {
		if(cnt == N || countBricks(map) == 0) {
			ans = Math.min(ans, countBricks(map));
			//printMap(map);
			return;
		}
		//printMap(map);
		
		for(int col=0; col<W; col++) {
			for(int row=0; row<H; row++) {
				if(map[row][col] != 0) {
					dropBricks(cnt+1, Bomb(new Point(row, col), copyMap(map)));
					//printMap(map);
					break;
				}
			}
		}
	}
	
	static int[][] copyMap(int[][] map) {
		int[][] newMap = new int[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}
	
	static int[][] Bomb(Point start, int[][] map) {
		Queue<Point> que = new ArrayDeque<>();
		que.add(start);
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			int len = map[now.x][now.y];
			map[now.x][now.y]= 0; 
			
			for(int l=1; l<len; l++) {
				for(int i=0; i<4; i++) {
					int nx = now.x+dx[i]*l;
					int ny = now.y+dy[i]*l;
					
					if(isRange(nx, ny) && map[nx][ny] != 0) {
						if(map[nx][ny] == 1) map[nx][ny] = 0;
						else que.add(new Point(nx, ny));
					}
				}
			}
		}
		
		return applyGravity(map);
	}
	
	// 중력 적용 메서드
    public static int[][] applyGravity(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // 각 열에 대해 중력 적용
        for (int col = 0; col < cols; col++) {
            int[] tempCol = new int[rows];
            int index = rows - 1; // 아래에서부터 채우기 시작

            for (int row = rows - 1; row >= 0; row--) {
                if (grid[row][col] != 0) {
                    tempCol[index] = grid[row][col];
                    index--;
                }
            }

            // 결과를 다시 grid에 적용
            for (int row = 0; row < rows; row++) {
                grid[row][col] = tempCol[row];
            }
        }
        
        return grid;
    }
	
	static int countBricks(int[][] map) {
		int cnt = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j] != 0) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	static boolean isRange(int x, int y) {
		return 0<=x && x<H && 0<=y && y<W;
	}
	
	static void printMap(int[][] map) {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("--------------------");
	}

}