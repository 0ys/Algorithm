import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
			for(int r=1; r<=N; r++) {
				int[] initP = new int[N];
				for(int i=N-1; i>N-r-1; i--) {
					initP[i] = 1;
				}
				do {
					int kcal = 0;
					int score = 0;
					//System.out.println(Arrays.toString(initP));
					for(int i=0; i<N; i++) {
						if(initP[i] == 1) {
							kcal += inputs[i].kcal;
							score += inputs[i].score;
						}
					}
					if(kcal <= L) ans = Math.max(ans, score);
					
				} while(np(initP));
			}
			
			sb.append("#"+t+" "+ans+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static boolean np(int[] p) {
		int i = N-1;
		while(i>0 && p[i-1]>=p[i]) --i;
		if(i == 0) return false;
		
		int j = N-1;
		while(p[i-1] >= p[j]) --j;
		
		swap(p, i-1, j);
		
		int k = N-1;
		while(i<k) {
			swap(p, i++, k--);
		}
		
		return true;
	}
	
	static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}