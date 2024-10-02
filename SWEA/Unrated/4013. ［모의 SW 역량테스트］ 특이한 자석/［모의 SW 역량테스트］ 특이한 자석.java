import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, K;
	static int[][] magnet;
	static int[] red; // 각 자석의 빨간색 화살표 위치 인덱스
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			K = Integer.parseInt(br.readLine());
			magnet = new int[5][8];
			
			for(int i=1; i<=4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken()); // N극=0, S극=1
				}
			}
			
			red = new int[5];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int numOfMagnet = Integer.parseInt(st.nextToken()); // 시계방향=1, 반시계방향=-1
				int direction = Integer.parseInt(st.nextToken());
				visited = new boolean[5];
				Rotate(numOfMagnet, direction);
			}
			int ans = magnet[1][red[1]] + 2*magnet[2][red[2]] + 4*magnet[3][red[3]] + 8*magnet[4][red[4]];
			
			sb.append("#"+tc+" "+ans+"\n");
		}
		
		System.out.println(sb.toString());
	}
	private static void Rotate(int num, int dir) {
		if(num == 1) {
			visited[1] = true;
			if(magnet[1][getIdx(red[1]+2)] != magnet[2][getIdx(red[2]-2)]) {
				if(!visited[2]) {
					//System.out.println("1 rotate 2");
					Rotate(2, -dir); 
				}
			}
			red[1] = getIdx(red[1]-dir);
		} else if(num == 2) {
			visited[2] = true;
			if(magnet[2][getIdx(red[2]-2)] != magnet[1][getIdx(red[1]+2)]) {
				if(!visited[1]) {
					//System.out.println("2 rotate 1");
					Rotate(1, -dir); 
				}
			} 
			if(magnet[2][getIdx(red[2]+2)] != magnet[3][getIdx(red[3]-2)]) {
				if(!visited[3]) {
					//System.out.println("2 rotate 3");
					Rotate(3, -dir); 
				}
			}
			red[2] = getIdx(red[2]-dir);
		} else if(num == 3) {
			visited[3] = true;
			if(magnet[3][getIdx(red[3]-2)] != magnet[2][getIdx(red[2]+2)]) {
				if(!visited[2]) {
					//System.out.println("3 rotate 2");
					Rotate(2, -dir); 
				}
			} 
			if(magnet[3][getIdx(red[3]+2)] != magnet[4][getIdx(red[4]-2)]) {
				if(!visited[4]) {
					//System.out.println("3 rotate 4");
					Rotate(4, -dir); 
				}
			}
			red[3] = getIdx(red[3]-dir);
		} else if(num == 4) {
			visited[4] = true;
			if(magnet[4][getIdx(red[4]-2)] != magnet[3][getIdx(red[3]+2)]) {
				if(!visited[3]) {
					//System.out.println("4 rotate 3");
					Rotate(3, -dir); 
				}
			}
			red[4] = getIdx(red[4]-dir);
		}
	}
	
	private static int getIdx(int idx) {
		if(idx > 7) return idx - 8;
		else if(idx < 0) return 8 + idx;
		return idx;
	}

}