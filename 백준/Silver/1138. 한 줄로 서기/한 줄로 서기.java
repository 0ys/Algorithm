import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int[] input;
    static ArrayDeque<Integer> ans = new ArrayDeque<>();
    static ArrayDeque<Integer> left = new ArrayDeque<>();
    static ArrayDeque<Integer> right = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        input = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=N-1; i>=0; i--){
            int cnt = 0;
            boolean flag = false;
            while(!ans.isEmpty()){
                int a = ans.pollFirst();
                if(cnt == input[i]){
                    flag = true;
                } else if(a > i) cnt++;

                if(flag) right.add(a);
                else left.add(a);
            }

            while(!left.isEmpty()){
                ans.add(left.poll());
            }
            ans.add(i);
            while(!right.isEmpty()){
                ans.add(right.poll());
            }
        }

        for(int a: ans){
            System.out.print((a+1)+" ");
        }
    }

}
