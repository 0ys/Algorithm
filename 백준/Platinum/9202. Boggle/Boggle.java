import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;
public class Main {
	static int N, B;
	static TreeSet<String> words;
	static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	// initBoard
	static char[][] boards;
	static int maxScore;
	static int maxLength;
	static String longestWord;
	static int cnt;
	// DFS is better -> DFS 알고리즘을 더 공부하자 
	static boolean[][] visited;
	static String target;
	static boolean find;
	// BFS
	// you can only visit once!
	// ex. PROGRAMM is failed!
//	static class Pair {
//		int x, y, index;
//
//		public Pair(int x, int y, int index) {
//			this.x = x;
//			this.y = y;
//			this.index = index;
//		}
//	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());

		words = new TreeSet<>();
		for(int i=0; i<N; i++) {
			words.add(br.readLine());
		}
		
		br.readLine();
		
		B = Integer.parseInt(br.readLine());
		for(int b=0; b<B; b++) {
			initBoard();
			
			for(int i=0; i<4; i++) {
				String line = br.readLine();
				for(int j=0; j<4; j++) {
					boards[i][j] = line.charAt(j);
				}
			}
			
			if(b != B-1) br.readLine();
			//System.out.println("board #"+(b+1)+": ");
			// Boggle
			for(String word : words) {
				target = word;
				if(Boggle()) {
					int nowLength = word.length();
					maxScore += getScore(nowLength);
					cnt++;
					//System.out.println(word+" ");
					if(nowLength > maxLength) {
						longestWord = word;
						maxLength = nowLength;
					}
				}
			}
			//System.out.println();
			bw.write(maxScore+" "+longestWord+" "+cnt+"\n");
			
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static boolean Boggle() {
		//boolean flag = false;
		find = false;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(find) break;
				if(boards[i][j] == target.charAt(0)) {
					//flag = BFS(i, j, target);
					DFS(i, j, 0);
					//System.out.println();
				}
			}
		}
		
		return find;
	}
	
	static void DFS(int x, int y, int depth) {
		//System.out.print(boards[x][y]+" ");
		if(depth == target.length()-1) {
			if(boards[x][y] == target.charAt(target.length()-1)) find = true;
			return;
		}
		
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=4 || ny<0 || ny>=4) continue;
			
			char next = boards[nx][ny];
			if(next == target.charAt(depth+1) && !visited[nx][ny]) {
				visited[x][y] = true;
				DFS(nx, ny, depth+1);
				visited[x][y] = false;
			}	
		}
	}
	
	static void initBoard() {
		boards = new char[4][4];
		visited = new boolean[4][4];
		maxScore = 0;
		maxLength = 0;
		longestWord = null;
		cnt = 0;
	}
	
	static int getScore(int len) {
		int score = 0;
		switch(len) {
			case 1:
			case 2: score = 0; break;
			case 3:
			case 4: score = 1; break;
			case 5: score = 2; break;
			case 6: score = 3; break;
			case 7: score = 5; break;
			case 8: score = 11; break;
			default: score = 0; break;
		}
		
		return score;
	}

}
