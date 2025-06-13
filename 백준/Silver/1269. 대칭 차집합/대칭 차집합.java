import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Set<Integer> setA = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<A; i++){
            setA.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> ab = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<B; i++){
            int b = Integer.parseInt(st.nextToken());
            if(setA.contains(b)) ab.add(b);
        }

        int num = ab.size();
        System.out.println((A - num) + (B - num));

    }
}
