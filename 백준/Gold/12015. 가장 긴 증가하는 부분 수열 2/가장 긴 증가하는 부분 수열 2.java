import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static int N, A[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

//        int[] LIS = new int[N];
//        for(int i = 0; i < N; i++){
//            LIS[i] = 1;
//            for(int j = 0; j < i; j++){
//                if(A[j] < A[i] && LIS[i] < LIS[j]+1){
//                    LIS[i] = LIS[j]+1;
//                }
//            }
//        }
//
//        int max = 0;
//        for(int a : LIS){
//            if(max < a) max = a;
//        }

        System.out.println(findLIS(A));
    }

    // LIS를 찾는 메서드
    public static int findLIS(int[] nums) {
        int[] lis = new int[nums.length]; // LIS를 저장할 배열
        int length = 0;  // LIS 배열의 실제 길이

        for (int num : nums) {
            // lis 배열에서 num이 들어갈 위치를 이진 탐색으로 찾음
            int pos = Arrays.binarySearch(lis, 0, length, num);

            // 이진 탐색 결과가 음수이면 삽입해야 할 위치를 나타내므로 (-pos - 1)로 변환
            if (pos < 0) {
                pos = -(pos + 1);
            }

            // 해당 위치에 num을 삽입하거나 기존 값을 대체
            lis[pos] = num;

            // 새로 추가된 경우 LIS 배열의 길이를 증가시킴
            if (pos == length) {
                length++;
            }
        }

        // LIS 배열의 실제 길이가 최대 증가 부분 수열의 길이
        return length;
    }
}