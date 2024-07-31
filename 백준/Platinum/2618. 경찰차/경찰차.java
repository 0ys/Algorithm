import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 두 경찰차로부터 같은 거리인 사건 (4,3)
6
4
4 3 
5 5
3 5
2 3
 */
public class Main {
	static int N, W;
	static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[][] DP;
	// DP[x][y] : 첫 번째 경찰차의 위치가 x번째 사건이고 두 번째 경찰차의 위치가 y번째 사건에 있을 때,
	// 현재 위치에서 사건을 끝까지 해결할 때까지 이동하는 거리의 합의 최솟값!!
	static Pair[] event;
	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringBuilder sb = new StringBuilder();
		 StringTokenizer st;
		 
		 N = Integer.parseInt(br.readLine());
		 W = Integer.parseInt(br.readLine());
		 
		 DP = new int[W+2][W+2];

		 event = new Pair[W+2];
		 event[0] = new Pair(1, 1); // car1
		 event[W+1] = new Pair(N, N); // car2
		 for(int i=1; i<=W; i++) {
			 st = new StringTokenizer(br.readLine());
			 int x = Integer.parseInt(st.nextToken());
			 int y = Integer.parseInt(st.nextToken());
			 
			 event[i] = new Pair(x, y);
		 }
		 
		 Move(0, W+1, 1);
		 sb.append(DP[0][W+1]+"\n");
		 
		 // 백트래킹으로 사건을 맡은 경찰차를 찾음
		 int car1 = 0;
		 int car2 = W+1;
		 for(int i=1; i<=W; i++) {
			 int nowD = distance(event[car1], event[i]);
			 
			 if(DP[car1][car2] == DP[i][car2] + nowD) {
				 car1 = i;
				 sb.append("1\n");
			 } else {
				 car2 = i;
				 sb.append("2\n");
			 }
		 }
		 
		 System.out.println(sb.toString());
	}
	
	static int Move(int car1, int car2, int eventIdx) { // 1~N번째 사건 순서대로 움직임
		if(eventIdx > W) return 0;
		
		if(DP[car1][car2] != 0) return DP[car1][car2];
		
		int d1 = Move(eventIdx, car2, eventIdx+1) + distance(event[eventIdx], event[car1]);
		int d2 = Move(car1, eventIdx, eventIdx+1) + distance(event[eventIdx], event[car2]);
		
		return DP[car1][car2] = Math.min(d1, d2);
	}
	
	static int distance(Pair one, Pair two) {
		return Math.abs(one.x - two.x)+Math.abs(one.y - two.y);
	}
	
}
