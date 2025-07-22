import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static long N, P, Q, X, Y;
    static HashMap<Long, Long> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        System.out.println(DFS(N));

    }

    static long DFS(long i) {
        if(i <= 0) return 1;
        if (memo.containsKey(i)) return memo.get(i);
        long value = DFS(i / P - X) + DFS(i / Q - Y);
        memo.put(i, value);
        return value;
    }
}
