import java.io.IOException;
import java.util.Scanner;

public class Solution {
	static int[] H;
	static int N;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			
			H = new int[N];
			for(int i=0; i<N; i++) {
				H[i] = sc.nextInt();
			}
			
			int ans = 0;
			for(int i=1; i<N; i++) {
				if(i+1 < N && H[i-1] < H[i] && H[i] > H[i+1]) {
					//System.out.print(H[i]+" ");
					int left = i - Left(i);
					int right = Right(i) - i;
					ans += left*right;
				}
			}
			sb.append("#"+t+" "+ans+"\n");
			
		}
		
		System.out.println(sb.toString());
		
	}
	static int Left(int x) {
		while(x>0 && H[x-1] < H[x]) --x;
		return x;
	}
	
	static int Right(int x) {
		while(x<N-1 && H[x] > H[x+1]) ++x;
		return x;
	}

}