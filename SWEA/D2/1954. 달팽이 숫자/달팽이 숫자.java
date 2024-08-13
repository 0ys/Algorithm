import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {			
			int N = sc.nextInt();
			
			System.out.println("#"+tc);
			SnailNumber(N);
		}
	}
	
	static void SnailNumber(int N) {
		// 오른쪽, 아래, 왼쪽, 위
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		boolean[][] visited = new boolean[N][N];
		int[][] number = new int[N][N];
		
		int direction = 0;
		int x = 0;
		int y = -1;
		int num = 1;
		while(num<=N*N) {
			// 이동 방향에 따라 다음 x, y를 구함
			x += dx[direction];
			y += dy[direction];
			if(0<=x && x<N && 0<=y && y<N && !visited[x][y]) {
				number[x][y] = num++;
				visited[x][y] = true;
			} else {
				// 범위를 벗어나면 다시 이전 위치로 돌아감
				x -= dx[direction];
				y -= dy[direction];
				// 이동 방향을 수정함
				direction = (direction+1) % 4;
			}
		}
		
		// 달팽이 숫자 출력
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(number[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}

}