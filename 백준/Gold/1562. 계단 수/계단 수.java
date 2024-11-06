import java.util.Arrays;
import java.util.Scanner;
/*
45656처럼 인접한 모든 자리의 차이가 1인 수를 계단수라고 한다.
길이가 N이면서 0부터 9까지 숫자가 모두 등장하는 계단 수가 총 몇 개인가?
0으로 시작하는 수는 계단수가 아니다.
1 -> 0
2 -> 0
8 -> 0
9 -> 0
10 -> 1 ; 9876543210
11 -> 3
12 -> 14
13 -> 37
14 -> 117
15 -> 287
20 -> 23249
30 -> 49050151
40 -> 203722859
50 -> 928508497
60 -> 913419265
70 -> 169463574
80 -> 606166739
90 -> 384840782
97 -> 923402943
98 -> 431914107
99 -> 270442392
100 -> 670667793
*/

public class Main {
    static int N;
    static long[][][] dp; //dp[length][last_digit][bitmask]
    // length: 현재 자리 수의 길이
    // last_digit: 마지막 자리 숫자
    // bitmask: 0부터 9까지 숫자가 모두 포함되었는지
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 1<=N<=100
        System.out.println(countStaircaseNumbers(N));
    }

    public static int countStaircaseNumbers(int N) {
        // dp[length][last_digit][bitmask]
        int[][][] dp = new int[N + 1][10][1 << 10];

        // 초기 설정: 첫 자리는 1부터 9까지만 가능
        for (int digit = 1; digit <= 9; digit++) {
            dp[1][digit][1 << digit] = 1;
        }

        // 점화식 채우기
        for (int length = 2; length <= N; length++) {  // 자리 수를 2부터 N까지 늘려가면서
            for (int lastDigit = 0; lastDigit <= 9; lastDigit++) {  // 마지막 자리 숫자
                for (int bitmask = 0; bitmask < (1 << 10); bitmask++) {  // 각 비트마스크 상태에 대해
                    // 현재 자리 수에 따라 이전 자리 수를 찾아서 갱신
                    if (lastDigit > 0) {  // 이전 숫자가 lastDigit - 1일 경우
                        int newBitmask = bitmask | (1 << lastDigit);
                        dp[length][lastDigit][newBitmask] = (dp[length][lastDigit][newBitmask] + dp[length - 1][lastDigit - 1][bitmask]) % MOD;
                    }
                    if (lastDigit < 9) {  // 이전 숫자가 lastDigit + 1일 경우
                        int newBitmask = bitmask | (1 << lastDigit);
                        dp[length][lastDigit][newBitmask] = (dp[length][lastDigit][newBitmask] + dp[length - 1][lastDigit + 1][bitmask]) % MOD;
                    }
                }
            }
        }

        // 결과 계산: 길이가 N이고, 모든 숫자가 포함된 비트마스크 상태인 경우의 수
        int fullBitmask = (1 << 10) - 1;  // 0부터 9까지 모든 숫자가 포함된 상태
        int result = 0;
        for (int lastDigit = 0; lastDigit <= 9; lastDigit++) {
            result = (result + dp[N][lastDigit][fullBitmask]) % MOD;
        }

        return result;
    }
}