import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] tree;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            if(!st.hasMoreTokens()) continue;

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[] number = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                number[i] = getSign(Integer.parseInt(st.nextToken()));
            }

            // init
            size = 1;
            while(size < N) size <<= 1;
            tree = new int[size*2];

            for(int i=1; i<=N; i++){
                tree[size+i-1] = number[i];
            }

            for(int i=size-1; i>=1; i--){
                tree[i] = tree[i*2] * tree[i*2+1];
            }

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                String order = st.nextToken();
                if(order.equals("C")){
                    int idx = Integer.parseInt(st.nextToken());
                    int value = getSign(Integer.parseInt(st.nextToken()));
                    update(idx, value);
                } else if(order.equals("P")){
                    int start = Integer.parseInt(st.nextToken());
                    int end = Integer.parseInt(st.nextToken());
                    int result = query(start, end);

                    if (result > 0) sb.append("+");
                    else if (result < 0) sb.append("-");
                    else sb.append("0");
                }
            }

            System.out.println(sb.toString());
        }
    }

    static int getSign(int x) {
        if (x > 0) return 1;
        if (x < 0) return -1;
        return 0;
    }

    static void update(int idx, int value){
        int treeIdx = size+idx-1;
        tree[treeIdx] = value;

        while(treeIdx > 1){
            treeIdx /= 2;
            tree[treeIdx] = tree[treeIdx*2] * tree[treeIdx*2+1];
        }
    }

    static int query(int start, int end){
        int l = size+start-1;
        int r = size+end-1;
        int result = 1;

        while(l <= r) {
            if(l%2 == 1) result *= tree[l++];
            if(r%2 == 0) result *= tree[r--];
            l /= 2;
            r /= 2;
        }
        return result;
    }
}
