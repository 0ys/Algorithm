import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			int[] inputs = new int[N];
			
			for(int i=0; i<N; i++) {
				inputs[i] = sc.nextInt();
			}
			
			int[] LIS = new int[N];
			for(int i=0; i<N; i++) {
				LIS[i] = 1;
				for(int j=0; j<i; j++) {
					if(inputs[j] < inputs[i] && LIS[i] < LIS[j]+1) {
						LIS[i] = LIS[j]+1;
					}
				}
			}
			
			int max = 0;
			for(int a : LIS) {
				if(max < a) max = a;
			}
			
			System.out.println("#"+tc+" "+max);
		}
	}

}