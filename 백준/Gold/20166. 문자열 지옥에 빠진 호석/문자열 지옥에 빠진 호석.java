import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static Map<String, Integer> wordCount = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        //printMap();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                DFS(i, j, String.valueOf(map[i][j]), 1);
            }
        }

        for(int i=0; i<K; i++){
            String query = br.readLine();
            System.out.println(wordCount.getOrDefault(query, 0));
        }
    }

    static void DFS(int x, int y, String path, int depth) {
        wordCount.put(path, wordCount.getOrDefault(path, 0) + 1);

        if(depth == 5) return;

        for(int i=0; i<8; i++){
            int nx = (x + dx[i] +N) % N;
            int ny = (y + dy[i] +M) % M;
            DFS(nx, ny, path + map[nx][ny], depth + 1);
        }
    }

    static void printMap() {
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
