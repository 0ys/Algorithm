import java.util.*;
import java.io.*;

/* 반례
10
9 8 7 6 5 1 2 3 4 5
8
1 3 5 7 9 6 5 4
*/
public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> A = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A.add(Integer.parseInt(st.nextToken())); // 1 9 7 3
        }
        M = Integer.parseInt(br.readLine());
        ArrayList<Integer> B = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            B.add(Integer.parseInt(st.nextToken())); // 1 8 7 5 3
        }

        boolean flag = true;
        List<Integer> ans = new ArrayList<>();
        int max_a = 0, a_idx = 0, max_b = 0, b_idx = 0;

        while (true) {
            // 각 수열의 최대 공통 값을 찾음
            while (true) {
                if (A.isEmpty() || B.isEmpty()) {
                    flag = false;
                    break;
                }

                // 최대값과 인덱스 찾기
                max_a = Collections.max(A);
                a_idx = A.indexOf(max_a);
                max_b = Collections.max(B);
                b_idx = B.indexOf(max_b);

                if (max_a == max_b) break;  // 공통된 최대값을 찾음
                else if (max_a > max_b) A.remove(a_idx);  // a에서 최대값 제거
                else B.remove(b_idx);  // b에서 최대값 제거
            }

            if (!flag) break;

            // 최댓값을 ans 리스트에 추가
            ans.add(max_a);

            // 최댓값보다 작은 인덱스의 값들을 제거 (두 배열 모두)
            ArrayList<Integer> newA = new ArrayList<>(A.subList(a_idx + 1, A.size()));
            A = newA;

            ArrayList<Integer> newB = new ArrayList<>(B.subList(b_idx + 1, B.size()));
            B = newB;
        }

        // 결과 출력
        if (!ans.isEmpty()) {
            System.out.println(ans.size());
            for (int v : ans) {
                System.out.print(v + " ");
            }
        } else {
            System.out.println(0);
        }

    }

}