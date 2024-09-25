import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 첫 번째 배열 입력
        int n = sc.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        // 두 번째 배열 입력
        int m = sc.nextInt();
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            b.add(sc.nextInt());
        }

        boolean flag = true;
        List<Integer> ans = new ArrayList<>();
        int max_a = 0, a_idx = 0, max_b = 0, b_idx = 0;

        while (true) {
            // 각 수열의 최대 공통 값을 찾음
            while (true) {
                if (a.isEmpty() || b.isEmpty()) {
                    flag = false;
                    break;
                }

                // 최대값과 인덱스 찾기
                max_a = Collections.max(a);
                a_idx = a.indexOf(max_a);
                max_b = Collections.max(b);
                b_idx = b.indexOf(max_b);

                if (max_a == max_b) break;  // 공통된 최대값을 찾음
                else if (max_a > max_b) a.remove(a_idx);  // a에서 최대값 제거
                else b.remove(b_idx);  // b에서 최대값 제거
            }

            if (!flag) break;

            // 최댓값을 ans 리스트에 추가
            ans.add(max_a);

            // 최댓값보다 작은 인덱스의 값들을 제거 (두 배열 모두)
            List<Integer> newA = new ArrayList<>(a.subList(a_idx + 1, a.size()));
            a = newA;

            List<Integer> newB = new ArrayList<>(b.subList(b_idx + 1, b.size()));
            b = newB;
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