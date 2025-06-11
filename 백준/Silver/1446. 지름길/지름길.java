import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, D;
    static Road[] road;
    static int[] ans;

    static class Road {
        int start, end, cost;

        Road(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        road = new Road[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            road[i] = new Road(start, end, cost);
        }

        ans = new int[10001];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[0] = 0;

        for(int i=0; i<=D; i++){
            if(i+1 <= D) {
                ans[i+1] = Math.min(ans[i+1], ans[i] + 1);
            }

            for(int j=0; j<N; j++){
                Road r = road[j];
                if(r.start == i && r.end <= D && r.end - r.start > r.cost) {
                    ans[r.end] = Math.min(ans[r.end], ans[i] + r.cost);
                }
            }
        }

        System.out.println(ans[D]);
    }
}
