import java.io.*;
import java.util.*;

public class Main {
    static class Element implements Comparable<Element> {
        int value;
        int row;

        Element(int value, int row) {
            this.value = value;
            this.row = row;
        }

        public int compareTo(Element o) {
            return o.value - this.value; // MaxHeap처럼 작동하게
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Element> pq = new PriorityQueue<>();

        // 각 열의 마지막 행(가장 아래)에 있는 값들을 큐에 넣음
        for (int j = 0; j < N; j++) {
            pq.offer(new Element(map[N - 1][j], N - 1));
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            Element cur = pq.poll();
            answer = cur.value;

            // 현재 열의 위쪽 값이 존재한다면 넣어줌
            if (cur.row > 0) {
                pq.offer(new Element(map[cur.row - 1][getColumn(map, cur.value, cur.row)], cur.row - 1));
            }
        }

        System.out.println(answer);
    }

    // 열 번호 찾기용 함수 (cur.value가 어느 열에 있는지 찾기 위해)
    private static int getColumn(int[][] map, int value, int row) {
        for (int j = 0; j < map.length; j++) {
            if (map[row][j] == value) return j;
        }
        return -1;
    }
}
