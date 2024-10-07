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
    /*
    #### 정리
           08, 02, 04, 03, 06, 11, 07, 10, 14, 05
       --------------------------------------
    C[1] : 08, 02, 02, 02, 02, 02, 02, 02, 02, 02
    C[2] :         04, 03, 03, 03, 03, 03, 03, 03
    C[3] :                 06, 06, 06, 06, 06, 05
    C[4] :                         07, 07, 07, 07
    C[5] :                             10, 10, 10
    C[6] :                                 14, 14
    - 숫자가 기존 수열의 마지막보다 크면 그대로 추가.
    - 숫자가 작으면 기존 수열에서 그 숫자보다 큰 값 중 가장 작은 값을 바꿔줌. (이진 탐색으로 빠르게 바꿀 위치를 찾아줌)
     */
    public static int findLIS(int[] nums) {
        int[] lis = new int[nums.length]; // LIS를 저장할 배열
        int length = 0;  // LIS 배열의 실제 길이

        for (int num : nums) {
            // lis 배열에서 num이 들어갈 위치를 이진 탐색으로 찾음
            int index = binarySearch(lis, 0, length, num);

            // 해당 위치에 num을 삽입하거나 기존 값을 대체
            lis[index] = num;

            // 새로 추가된 경우 LIS 배열의 길이를 증가시킴
            if(index == length) length++;
        }

        // LIS 배열의 실제 길이가 최대 증가 부분 수열의 길이
        return length;
    }

    static int binarySearch(int[] lis, int l, int r, int now) {
        int mid;
        while(l < r){
            mid = (l+r) / 2;
            if(lis[mid] < now) l = mid+1;
            else r = mid;
        }
        return l;
    }
}