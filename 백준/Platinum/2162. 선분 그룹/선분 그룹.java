import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parents;
    static Line[] lines;
    static class Line {
        long x1, y1, x2, y2;
        public Line(long x1, long y1, long x2, long y2) {
            // A
            this.x1 = x1;
            this.y1 = y1;
            // B
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        lines = new Line[N];
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = -1;
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());

            lines[i] = new Line(x1, y1, x2, y2);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(Find(i) == Find(j)) continue;

                Line AB = lines[i];
                Line CD = lines[j];

                int ABC = CCW(AB.x1, AB.y1, AB.x2, AB.y2, CD.x1, CD.y1);
                int ABD = CCW(AB.x1, AB.y1, AB.x2, AB.y2, CD.x2, CD.y2);
                int CDA = CCW(CD.x1, CD.y1, CD.x2, CD.y2, AB.x1, AB.y1);
                int CDB = CCW(CD.x1, CD.y1, CD.x2, CD.y2, AB.x2, AB.y2);

                boolean meet = false;
                if(ABC*ABD == 0 && CDA*CDB == 0) { // 일직선 상에서 만남
                    meet = isOverlab(AB, CD);
                } else if(ABC*ABD <= 0 && CDA*CDB <= 0) { // 두 선분이 만남
                    meet = true;
                }

                if(meet) {Union(i, j);}
            }
        }

        int group = 0; // 그룹의 수
        int cnt = 0; // 가장 크기가 큰 그룹에 속한 선분의 개수
        for(int i = 0; i < N; i++){
            if(parents[i] < 0) {
                group++; // 음수이면 대표 노드임
                cnt = Math.min(cnt, parents[i]); //음수의 절댓값이 그룹의 선분 개수
            }
        }
        System.out.println(group);
        System.out.println(-cnt);
    }

    static int CCW (long x1, long y1, long x2, long y2, long x3, long y3) {
        long ccw = (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3);
        if(ccw < 0) return -1; // 왼쪽
        else if(ccw > 0) return 1; // 오른쪽
        else return 0;
    }

    static boolean isOverlab(Line A, Line B) {
        if(Math.min(A.x1, A.x2) <= Math.max(B.x1, B.x2) && Math.min(A.y1, A.y2) <= Math.max(B.y1, B.y2)
            && Math.min(B.x1, B.x2) <= Math.max(A.x1, A.x2) && Math.min(B.y1, B.y2) <= Math.max(A.y1, A.y2)) {
            return true;
        }
        return false;
    }

    static void Union(int a, int b) {
        int aRoot = Find(a);
        int bRoot = Find(b);
        if(aRoot != bRoot){
            parents[aRoot] += parents[bRoot];
            parents[bRoot] = aRoot;
        }
    }

    static int Find(int x) {
        if(parents[x] < 0) return x;
        return parents[x] = Find(parents[x]);
    }
}
