import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
	static long N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Long.parseLong(br.readLine());
			
			sb.append("#"+t+" "+play()+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int play() {
		int cnt = 0;
		while(N != 2) {
			long sqrt = (long) Math.sqrt(N);
			
			if(sqrt * sqrt == N) {
				N = sqrt;
				cnt++;
			}
			else {
				sqrt++;
				cnt += sqrt*sqrt - N + 1;
				N = sqrt;
			}
		}
		
		return cnt;
	}
	

}