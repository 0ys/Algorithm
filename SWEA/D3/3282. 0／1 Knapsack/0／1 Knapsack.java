import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, K;
	static int[][] knapsack, inputs;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc <=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			knapsack = new int[N+1][K+1];
			inputs = new int[N+1][2];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				inputs[i][0] = Integer.parseInt(st.nextToken()); //v 부피
				inputs[i][1] = Integer.parseInt(st.nextToken()); //c 가치
				
			}
			
			init();
			
			for(int i=1; i<=N; i++) {
				for(int w=1; w<=K; w++) {
					int weight = inputs[i][0];
					int value = inputs[i][1];
					if(weight > w) knapsack[i][w] = knapsack[i-1][w];
					else knapsack[i][w] = Math.max(knapsack[i-1][w], knapsack[i-1][w-weight]+value);
				}
			}
			
			
			sb.append("#"+tc+" "+knapsack[N][K]+"\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void init() {
		for(int i=0; i<=N; i++) {
			knapsack[i][0] = 0;
		}
		for(int i=0; i<=K; i++) {
			knapsack[0][i] = 0;
		}
	}

}