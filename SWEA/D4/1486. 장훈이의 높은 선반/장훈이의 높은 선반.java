import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	static int N, B, S;
	static int[] H;
	static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			H = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(H);
			isSelected = new boolean[N];
			S=Integer.MAX_VALUE;
			SubSet(0, 0);
			
			sb.append("#"+tc+" "+(S-B)+"\n");
			
		}
		
		System.out.println(sb.toString());
	}
	
	static void SubSet(int cnt, int sum) {
		if(cnt == N) {
			if(sum >= B) S = Math.min(S, sum);
			return;
		}
		
		isSelected[cnt] = true;
		SubSet(cnt+1, sum+H[cnt]);
		
		isSelected[cnt] = false;
		SubSet(cnt+1, sum);
		
	}
}