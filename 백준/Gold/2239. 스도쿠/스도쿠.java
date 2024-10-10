import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N = 9;
	static int[][] board = new int[N][N];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				board[i][j] = line.charAt(j) - '0';
			}
		}
		
		solveSudoku(board);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// 스도쿠 풀이 메소드
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) { // 비어 있는 칸을 찾음
                    for (int num = 1; num <= 9; num++) { // 1~9 숫자를 시도
                        if (isValid(board, row, col, num)) { // 숫자가 유효한지 확인
                            board[row][col] = num;

                            // 다음으로 진행하거나 해답을 찾았으면 true 반환
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // 유효하지 않으면 숫자를 지우고 백트래킹
                            board[row][col] = 0;
                        }
                    }
                    return false; // 1~9 모두 실패하면 false 반환
                }
            }
        }
        return true; // 모든 칸이 채워졌다면 true 반환
    }

    // 주어진 숫자가 유효한지 검사하는 메소드
    public static boolean isValid(int[][] board, int row, int col, int num) {
        // 행, 열, 3x3 박스 검사
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num ||
                board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

}