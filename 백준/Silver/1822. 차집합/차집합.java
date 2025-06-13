import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> ans = new ArrayList<>();
        Set<Integer> setB = new HashSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] arrA = new int[A];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < A; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < B; i++) {
            setB.add(Integer.parseInt(st.nextToken()));
        }

        for(int num : arrA){
            if(!setB.contains(num)){ans.add(num);}
        }

        Collections.sort(ans);

        System.out.println(ans.size());
        if(!ans.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(int num : ans){
                sb.append(num).append(" ");
            }
            System.out.println(sb);
        }
    }
}
