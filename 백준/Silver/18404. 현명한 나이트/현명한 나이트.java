import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    static Point knight;
    static ArrayList<Point> target = new ArrayList<>();
    static int[][] map;

    static class Knight {
        int x, y, cnt;

        Knight(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        knight = new Point(X, Y);
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            target.add(new Point(x, y));
        }

        BFS();

        for(Point knight : target){
            System.out.print(map[knight.x][knight.y]+" ");
        }
    }

    static void BFS(){
        ArrayDeque<Knight> que = new ArrayDeque<>();
        que.add(new Knight(knight.x, knight.y, 0));
        map[knight.x][knight.y] = 0;

        while(!que.isEmpty()){
            Knight now = que.poll();

            for(int i=0; i<8; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(0<nx && nx<=N && 0<ny && ny<=N){
                    if(now.cnt+1 < map[nx][ny]){
                        map[nx][ny] = now.cnt + 1;
                        que.add(new Knight(nx, ny, now.cnt + 1));
                    }
                }
            }
            //printMap();
        }
    }

    static void printMap() {
        for(int i=0; i<=N; i++){
            for(int j=0; j<=N; j++){
                if (map[i][j] == Integer.MAX_VALUE) {
                    System.out.print("X ");
                } else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("=================");
    }
}
