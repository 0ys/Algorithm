import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> ans = new PriorityQueue<>();
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < A; i++) {
            int a = Integer.parseInt(st.nextToken());
            setA.add(a);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < B; i++) {
            int b = Integer.parseInt(st.nextToken());
            setB.add(b);
        }

        for(int now : setA){
            if(!setB.contains(now)){ans.add(now);}
        }

        System.out.println(ans.toArray().length);
        while(!ans.isEmpty()){
            System.out.print(ans.poll()+" ");
        }
    }
}