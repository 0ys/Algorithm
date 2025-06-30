import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] bitStr = br.readLine().toCharArray();

        int cnt = 0;
        for(char c : bitStr){
            if(c == '1') cnt++;
        }
        System.out.println(cnt);
    }
}