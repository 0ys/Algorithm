import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, X;
    static int[][] maps;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            maps = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = 0;
            for(int i = 0; i < N; i++) {
                if(checkRow(i)) result++;
                if(checkCol(i)) result++;
            }
            sb.append("#"+tc+" "+result+"\n");
        }
        System.out.println(sb.toString());
        
    }
    static boolean checkRow(int row) {
        boolean[] slope = new boolean[N];
        for(int i = 0; i < N-1; i++) {
            int diff = maps[row][i] - maps[row][i+1]; // 높이 차이를 확인

            if(diff == 0) continue;
            else if(diff == 1) { // 다음 위치에 내리막 경사로 설치
                for(int j = 1; j <= X; j++){
                    if(i + j >= N || slope[i+j]) return false;
                    if(maps[row][i] - 1 != maps[row][i+j]) return false;
                    slope[i+j] = true;
                }
            } else if(diff == -1) { // 지금 위치에 오르막 경사로 설치
                for(int j = 0; j < X; j++){
                    if(i - j < 0 || slope[i-j]) return false;
                    if(maps[row][i] != maps[row][i-j]) return false;
                    slope[i-j] = true;
                }
            } else return false; // 높이 차이가 1이상이면 false
        }
        return true;
    }
    static boolean checkCol(int col) {
        boolean[] slope = new boolean[N];
        for(int i = 0; i < N-1; i++) {
            int diff = maps[i][col] - maps[i+1][col];

            if(diff == 0) continue;
            else if(diff == 1) {
                for(int j = 1; j <= X; j++){
                    if(i + j >= N || slope[i+j]) return false;
                    if(maps[i][col] - 1 != maps[i+j][col]) return false;
                    slope[i+j] = true;
                }
            } else if(diff == -1) {
                for(int j = 0; j < X; j++){
                    if(i - j < 0 || slope[i-j]) return false;
                    if(maps[i][col] != maps[i-j][col]) return false;
                    slope[i-j] = true;
                }
            } else return false;
        }
        return true;
    }

}