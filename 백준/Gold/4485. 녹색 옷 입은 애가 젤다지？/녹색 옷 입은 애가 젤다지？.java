import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, ans;
    static int N;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int cnt = 1;

        while(N != 0){
            map = new int[N][N];

            ans = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    ans[i][j] = Integer.MAX_VALUE;
                }
            }

            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            BFS();

            System.out.println("Problem "+cnt+": "+ans[N-1][N-1]);

            N = Integer.parseInt(br.readLine());
            cnt++;
        }

    }

    static void BFS() {
        ArrayDeque<Point> que = new ArrayDeque<>();
        que.add(new Point(0, 0));
        ans[0][0] = map[0][0];

        while(!que.isEmpty()){
            Point p = que.poll();
            for(int k=0;k<4;k++){
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];

                if(0<=nx && nx<N && 0<=ny && ny<N){
                    int now = ans[p.x][p.y] + map[nx][ny];
                    if(now < ans[nx][ny]){
                        ans[nx][ny] = now;
                        que.add(new Point(nx, ny));
                    }
                }
            }
        }

    }
}
