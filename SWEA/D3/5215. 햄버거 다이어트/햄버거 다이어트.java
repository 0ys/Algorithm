import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N, L;
	static Ingredient[] hamburger;
	static Ingredient[] inputs;
	static int maxScore;
	static class Ingredient {
		int score, kcal;

		public Ingredient(int score, int kcal) {
			this.score = score;
			this.kcal = kcal;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			hamburger = new Ingredient[N];
			inputs = new Ingredient[N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int kcal = Integer.parseInt(st.nextToken());
				inputs[i] = new Ingredient(score, kcal);
			}
			
			for(int R=1; R<=N; R++) {
				combination(0, 0, R);
			}
			
			bw.write("#"+t+" "+maxScore+"\n");
			maxScore = 0;
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static private void combination(int cnt, int start, int R) {
		if(cnt == R) {
			int totalS = 0;
			int totalK = 0;
			for(int idx=0; idx<R; idx++) {
				totalS += hamburger[idx].score;
				totalK += hamburger[idx].kcal;
			}
			
			if(totalK <= L) maxScore = Math.max(maxScore, totalS);
			return;
		}
		for(int i=start; i<N; i++) {
			hamburger[cnt] = inputs[i];
			combination(cnt+1, i+1, R);
		}
	}
}