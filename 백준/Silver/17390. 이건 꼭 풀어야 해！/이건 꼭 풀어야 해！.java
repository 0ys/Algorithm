import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] input = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);
        int[] sum = new int[N+1];
        sum[0] = 0;
        sum[1] = input[0];
        for(int i=2; i<=N; i++){
            sum[i] = sum[i-1] + input[i-1];
        }

//        for(int s: sum){
//            System.out.print(s+" ");
//        }

        for(int i=0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            System.out.println(sum[e] - sum[s-1]);
        }
    }
}
