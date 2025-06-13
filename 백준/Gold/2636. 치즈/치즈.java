import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, total;
    static boolean[][] maps;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int a = Integer.parseInt(st.nextToken());
                if(a == 1) {
                    total++;
                    maps[i][j] = true;
                }
            }
        }

        int time = 0;
        int before = 0;
        while(total != 0){
            // printMap();
            before = total;
            melting();
            time++;
        }

        System.out.println(time);
        System.out.println(before);
    }

    static void melting() {
        Queue<Point> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        que.add(new Point(0, 0));
        visited[0][0] = true;

        while(!que.isEmpty()){
            Point now = que.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(0<=nx && nx<N && 0<=ny && ny<M && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    if(maps[nx][ny]){
                        maps[nx][ny] = false;
                        total--;
                    } else {
                        que.add(new Point(nx, ny));
                    }
                }
            }
        }

    }

    static void printMap() {
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(maps[i][j]) System.out.print(1+" ");
                else System.out.print(0+" ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }
}
