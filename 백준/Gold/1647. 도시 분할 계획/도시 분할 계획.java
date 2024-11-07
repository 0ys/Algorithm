import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int N, M;
    static Queue<Edge> que = new PriorityQueue<>();
    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            que.add(new Edge(a, b, c));
        }
        //System.out.println(que.size());
        int usedEdge = 0;
        int maxEdge = 0;
        int totalCost = 0;
        while(usedEdge < N-1) {
            Edge edge = que.poll();
            int rootA = find(edge.from), rootB = find(edge.to);
            int cost = edge.cost;
            if(rootA != rootB){
                usedEdge++;
                parents[rootB] = rootA;
                totalCost += cost;
                maxEdge = Math.max(maxEdge, cost);
            }
        }
        System.out.println(totalCost - maxEdge);
    }

    private static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
}