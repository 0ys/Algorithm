import java.util.Arrays;
import java.util.Scanner;

/*
45656처럼 인접한 모든 자리의 차이가 1인 수를 계단수라고 한다.
길이가 N인 계단 수가 총 몇 개인가?
0으로 시작하는 수는 계단수가 아니다.
 */
public class Main {
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 1<=N<=100
        System.out.println(countStaircaseNumbers(N));
    }

    //O(N*10)
    private static int countStaircaseNumbers(int N) {
        // dp[length][last_digit]
        // length: 현재 자리 수의 길이
        // last_digit: 마지막 숫자가 last_digit인 계단수의 개수
        int[][] dp = new int[N + 1][10];

        // 초기설정: 첫 자리는 1부터 9까지 가능
        for(int digit=1; digit<=9; digit++){
            dp[1][digit] = 1;
        }

        for(int length=2; length<=N; length++){
            for(int lastDigit=0; lastDigit<=9; lastDigit++){
                if (lastDigit > 0) {  // 이전 숫자가 lastDigit - 1일 경우
                    dp[length][lastDigit] = (dp[length][lastDigit] + dp[length - 1][lastDigit - 1]) % MOD;
                }
                if (lastDigit < 9) {  // 이전 숫자가 lastDigit + 1일 경우
                    dp[length][lastDigit] = (dp[length][lastDigit] + dp[length - 1][lastDigit + 1]) % MOD;
                }
            }
        }

//        for(int i=0; i<=N; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }

        // 결과 계산: 길이가 N인 모든 경우의 계단수를 합산
        int result = 0;
        for (int digit = 0; digit <= 9; digit++) {
            result = (result + dp[N][digit]) % MOD;
        }

        return result;
    }
}
/*
[00, 01, 02, 03, 04, 05, 06, 07, 08, 09]
----------------------------------------
[00, 01, 01, 01, 01, 01, 01, 01, 01, 01]
[01, 01, 02, 02, 02, 02, 02, 02, 02, 01]
[01, 03, 03, 04, 04, 04, 04, 04, 03, 02]
[03, 04, 07, 07, 08, 08, 08, 07, 06, 03]
[04, 10, 11, 15, 15, 16, 15, 14, 10, 06]
[10, 15, 25, 26, 31, 30, 30, 25, 20, 10]
[15, 35, 41, 56, 56, 61, 55, 50, 35, 20]
[35, 56, 91, 97, 117, 111, 111, 90, 70, 35]
[56, 126, 153, 208, 208, 228, 201, 181, 125, 70]
[126, 209, 334, 361, 436, 409, 409, 326, 251, 125]

10 => 2986
 */