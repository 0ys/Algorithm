import sys
from collections import deque

input = sys.stdin.readline

def bfs(start, graph, n):
    visited = [False] * (n + 1)
    q = deque([start])
    visited[start] = True
    cnt = 1

    while q:
        cur = q.popleft()
        for nxt in graph[cur]:
            if not visited[nxt]:
                visited[nxt] = True
                q.append(nxt)
                cnt += 1
    return cnt


def main():
    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]

    for _ in range(m):
        a, b = map(int, input().split())
        # A가 B를 신뢰 => B 해킹하면 A도 가능 => B → A
        graph[b].append(a)

    max_cnt = 0
    result = []

    for i in range(1, n+1):
        cnt = bfs(i, graph, n)
        if cnt > max_cnt:
            max_cnt = cnt
            result = [i]
        elif cnt == max_cnt:
            result.append(i)

    print(*result)


if __name__ == "__main__":
    main()
