import java.util.*;

public class Main {

    public static int solve(int N, int K, int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num);
        }
        String start = sb.toString();

        StringBuilder sortedSb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sortedSb.append(i);
        }
        String target = sortedSb.toString();

        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new State(start, 0));
        visited.add(start);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.permutation.equals(target)) {
                return current.depth;
            }

            for (int i = 0; i <= N - K; i++) {
                String next = reverse(current.permutation, i, i + K - 1);
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(new State(next, current.depth + 1));
                }
            }
        }

        return -1; // 불가능한 경우
    }

    // 부분 문자열 뒤집기
    private static String reverse(String s, int left, int right) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, left));
        StringBuilder rev = new StringBuilder(s.substring(left, right + 1));
        sb.append(rev.reverse());
        sb.append(s.substring(right + 1));
        return sb.toString();
    }

    static class State {
        String permutation;
        int depth;

        State(String permutation, int depth) {
            this.permutation = permutation;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int result = solve(N, K, arr);
        System.out.println(result);
    }
}
