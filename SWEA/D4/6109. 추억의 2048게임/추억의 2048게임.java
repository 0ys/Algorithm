import java.io.*;
import java.util.*;

public class Solution {
	static int N, board[][];
	static String S;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = st.nextToken();
			
			board = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(S.equals("left")) Left();
			else if(S.equals("right")) Right();
			else if(S.equals("up")) Up();
			else if(S.equals("down")) Down();
			
			
			sb.append("#"+t+"\n");
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					sb.append(board[i][j]+" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static void Left() {
		for(int x=0; x<N; x++) {
			Queue<Integer> que = new LinkedList<>();
			for(int y=0; y<N; y++) {
				if(board[x][y] != 0) que.add(board[x][y]);
				board[x][y] = 0;
			}
			
			int now, next;
			int y = 0;
			while(!que.isEmpty()) {
				now = que.poll();
				if(!que.isEmpty()) {
					next = que.peek();
					if(now == next) {
						board[x][y++] = now+next;
						que.poll(); // next 제거
						continue;
					}
				}
				board[x][y++] = now;
			}
		}
	}
	
	static void Right() {
		for(int x=0; x<N; x++) {
			Queue<Integer> que = new LinkedList<>();
			for(int y=N-1; y>=0; y--) {
				if(board[x][y] != 0) que.add(board[x][y]);
				board[x][y] = 0;
			}
			
			int now, next;
			int y = N-1;
			while(!que.isEmpty()) {
				now = que.poll();
				if(!que.isEmpty()) {
					next = que.peek();
					if(now == next) {
						board[x][y--] = now+next;
						que.poll(); // next 제거
						continue;
					}
				}
				board[x][y--] = now;
			}
		}
	}
	
	static void Up() {
		for(int y=0; y<N; y++) {
			Queue<Integer> que = new LinkedList<>();
			for(int x=0; x<N; x++) {
				if(board[x][y] != 0) que.add(board[x][y]);
				board[x][y] = 0;
			}
			
			int now, next;
			int x = 0;
			while(!que.isEmpty()) {
				now = que.poll();
				if(!que.isEmpty()) {
					next = que.peek();
					if(now == next) {
						board[x++][y] = now+next;
						que.poll(); // next 제거
						continue;
					}
				}
				board[x++][y] = now;
			}
		}
	}
	
	static void Down() {
		for(int y=0; y<N; y++) {
			Queue<Integer> que = new LinkedList<>();
			for(int x=N-1; x>=0; x--) {
				if(board[x][y] != 0) que.add(board[x][y]);
				board[x][y] = 0;
			}
			
			int now, next;
			int x = N-1;
			while(!que.isEmpty()) {
				now = que.poll();
				if(!que.isEmpty()) {
					next = que.peek();
					if(now == next) {
						board[x--][y] = now+next;
						que.poll(); // next 제거
						continue;
					}
				}
				board[x--][y] = now;
			}
		}
	}
}
