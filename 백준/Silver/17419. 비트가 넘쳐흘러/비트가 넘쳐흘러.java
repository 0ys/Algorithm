import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String bitStr = br.readLine();
        int bit = Integer.parseInt(bitStr, 2);

        int cnt = 0;
        while(bit != 0){
            bit = bit-(bit&((~bit)+1));
            cnt++;
        }

//        System.out.println(bit);
        System.out.println(cnt);
    }
}
