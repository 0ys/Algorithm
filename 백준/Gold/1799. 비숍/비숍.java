import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
    public static int N;
    public static int[][] chess;
    
    public static int[] dy = {-1, -1, 1, 1};
    public static int[] dx = {-1, 1, -1, 1};
    
    public static int black = 0;
    public static int white = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        
        chess = new int[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        black_search(new boolean[N][N], 0, 0, 0);
        white_search(new boolean[N][N], 0, 1, 0);
        
        System.out.println(black+white);
    }
 
    public static void black_search(boolean[][] visited, int row, int col, int cnt) {
    	black = Math.max(cnt, black);
    	
        if(col >= N) { // 다음 행으로 이동한다.
            row += 1;
            col = (row%2 == 0)?0:1; // 홀수일 경우 0, 짝수일 경우 1
        }
        
        if(row >= N) return;
        
        if(isAble(visited, row, col)) {
            visited[row][col] = true;
            black_search(visited, row, col+2, cnt+1);
            visited[row][col] = false;
        }
        
        // 비숍을 놓지 않았을 경우에 대한 탐색
        black_search(visited, row, col+2, cnt);
    }
    
    public static void white_search(boolean[][] visited, int row, int col, int cnt) {
        white = Math.max(cnt, white);
        
        if(col >= N) {
            row += 1;
            col = (row%2 == 0)?1:0;
        }
        
        if(row >= N) return;
        
        if(isAble(visited, row, col)) {
            visited[row][col] = true;
            white_search(visited, row, col+2, cnt+1);
            visited[row][col] = false;
        }
        
        white_search(visited, row, col+2, cnt);
    }
    
    
    public static boolean isAble(boolean[][] visited, int y, int x) {
        if(chess[y][x] == 0) return false; // 0이면 놓을 수 없다.
        
        for(int i=0; i < 4; i ++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            
            for(int j=0; j<N; j++) {
                if(ny >= 0 && nx >= 0 && ny < N && nx < N) {
                    if(visited[ny][nx]) return false;
                    
                    ny += dy[i];
                    nx += dx[i];
                }
            }
        }
        return true;
    }
}