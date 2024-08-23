import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, min;
	static int[][] points;
	static int[] numbers;
	static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			points = new int[N+2][2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 회사
			points[0][0] = Integer.parseInt(st.nextToken());
			points[0][1] = Integer.parseInt(st.nextToken());
			// 집
			points[N+1][0] = Integer.parseInt(st.nextToken());
			points[N+1][1] = Integer.parseInt(st.nextToken());
			for(int i=1; i<N+1; i++) {
				points[i][0] = Integer.parseInt(st.nextToken());
				points[i][1] = Integer.parseInt(st.nextToken());
			}
			
			isSelected = new boolean[N+2];
			numbers = new int[N+2];
			numbers[0] = 0; // 회사 기록
			numbers[N+1] = N+1; // 집 기록
			min = Integer.MAX_VALUE;
			perm(1); // 고객의 집 방문 순서를 순열로 구함
			sb.append("#"+t+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	static void perm(int cnt) {
		if(cnt == N+1) {
			int total = 0;
			for(int i=0; i<N+1; i++) {
				int d = distance(numbers[i], numbers[i+1]);
				total += distance(numbers[i], numbers[i+1]);
			}
			min = Math.min(min, total);
			return;
		}
		for(int i=1; i<N+1; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			
			numbers[cnt] = i;
			perm(cnt+1);
			isSelected[i] = false;
		}
	}
	
	static int distance(int a, int b) {
		return Math.abs(points[a][0]-points[b][0]) + Math.abs(points[a][1]-points[b][1]);
	}

}