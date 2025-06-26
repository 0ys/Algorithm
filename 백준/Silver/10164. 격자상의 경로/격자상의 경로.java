import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;

    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = 0;

        if (K == 0) {
            result = getCount(0, 0, N - 1, M - 1);
        } else {
            // K번 칸의 위치
            int kx = (K - 1) / M;
            int ky = (K - 1) % M;

            int a = getCount(0, 0, kx, ky);
            int b = getCount(kx, ky, N - 1, M - 1);

            result = a * b;
        }

        System.out.println(result);
    }

    private static int getCount(int sx, int sy, int ex, int ey) {
        int[][] dp = new int[16][16]; // N, M 최대 15
        dp[sx][sy] = 1;

        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                if (i > sx) dp[i][j] += dp[i - 1][j];
                if (j > sy) dp[i][j] += dp[i][j - 1];
            }
        }

        return dp[ex][ey];
    }


}
