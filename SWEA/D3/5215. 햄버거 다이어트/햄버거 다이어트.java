import java.io.*;
import java.util.*;

public class Solution {
	static int N, L;
	static Element[] inputs;
	static class Element {
		int score, kcal;

		public Element(int score, int kcal) {
			this.score = score;
			this.kcal = kcal;
		}
	}
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			inputs = new Element[N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int kcal = Integer.parseInt(st.nextToken());
				
				inputs[i] = new Element(score, kcal);
			}
			
			ans = 0;
			Hamburger(0, 0, 0);
			
			sb.append("#"+t+" "+ans+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void Hamburger(int cnt, int score, int kcal) {
		if(kcal > L) return;
		if(cnt == N) {
			ans = Math.max(ans, score);
			return;
		}
		
		Hamburger(cnt+1, score+inputs[cnt].score, kcal+inputs[cnt].kcal);
		Hamburger(cnt+1, score, kcal);
	}
}