import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();

        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = '.';
            }
        }

        // 방향: U, D, L, R
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        char[] dirType = {'U', 'D', 'L', 'R'};

        int x = 0;
        int y = 0;

        for (char c : input) {
            int dir = -1;
            for (int i = 0; i < 4; i++) {
                if (c == dirType[i]) {
                    dir = i;
                    break;
                }
            }

            if (dir == -1) continue; // 잘못된 명령

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위 밖이면 무시
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            // 수직 이동
            if (dir == 0 || dir == 1) {
                board[x][y] = update(board[x][y], '|');
                board[nx][ny] = update(board[nx][ny], '|');
            }
            // 수평 이동
            else {
                board[x][y] = update(board[x][y], '-');
                board[nx][ny] = update(board[nx][ny], '-');
            }

            // 위치 이동
            x = nx;
            y = ny;
        }

        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static char update(char cur, char newChar) {
        if (cur == '.') return newChar;
        if (cur == newChar) return cur;
        return '+';
    }
}
