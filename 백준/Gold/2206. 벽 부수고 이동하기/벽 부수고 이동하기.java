import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] maps;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][][] visited;
    static int[][] DP; //BFS 최단거리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[2][N][M];
        maps = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                maps[i][j] = input[j] - '0';
            }
        }
        DP = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(DP[i], Integer.MAX_VALUE);
        }

        BFS(0, 0);
        //printDP();

        if(DP[N-1][M-1] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(DP[N-1][M-1]);
    }
    static void BFS(int x, int y) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{x, y, 0}); // x, y, wall
        visited[0][x][y] = true;
        visited[1][x][y] = true;
        DP[x][y] = 1;

        while(!que.isEmpty()){
            int[] cur = que.poll();
            int nowX = cur[0];
            int nowY = cur[1];
            int wall = cur[2];

            for(int i = 0; i < 4; i++){
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(maps[nx][ny] == 0 && !visited[wall][nx][ny]){
                        visited[wall][nx][ny] = true;
                        DP[nx][ny] = DP[nowX][nowY]+1;
                        que.add(new int[]{nx, ny, wall});
                    }
                    else if(wall < 1 && !visited[1][nx][ny]){
                        visited[wall][nx][ny] = true;
                        DP[nx][ny] = DP[nowX][nowY]+1;
                        que.add(new int[]{nx, ny, wall+1});
                    }
                    if(nx == N-1 && ny == M-1) return;
                }
            }
        }
    }
    static void printDP() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(DP[i][j] == Integer.MAX_VALUE) System.out.print("# ");
                else System.out.print(DP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }
}
