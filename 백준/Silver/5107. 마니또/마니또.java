import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int[] manitto;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int caseNum = 1;
        while(N != 0){
            manitto = new int[N];
            visited = new boolean[N];
            HashMap<String, Integer> person = new HashMap<>();

            int idx = -1;
            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String A = st.nextToken();
                String B = st.nextToken();

                if (!person.containsKey(A)) {
                    person.put(A, ++idx);
                }
                if (!person.containsKey(B)) {
                    person.put(B, ++idx);
                }

                int idxA = person.get(A);
                int idxB = person.get(B);
                manitto[idxA] = idxB;
            }

            System.out.println(caseNum+" "+Find(N));

            N = Integer.parseInt(br.readLine());
            caseNum++;
        }

    }

    static int Find(int N){
        int cnt = 0;

        for(int i=0; i<N; i++){
            if(!visited[i]){
                int now = i;
                while(!visited[now]){
                    visited[now] = true;
                    now = manitto[now];
                }
                cnt++;
            }
        }

        return cnt;
    }

}
