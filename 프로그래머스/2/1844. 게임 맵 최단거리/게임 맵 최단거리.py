from collections import deque

def solution(maps):
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    
    n = len(maps)
    m = len(maps[0])
    
    def bfs(x, y):
        que = deque()
        que.append((x, y, 1))
        visited = [[False]*m for _ in range(n)]
        visited[x][y] = True
        
        while que:
            x, y, cnt = que.popleft()
            
            if x == n-1 and y == m-1:
                return cnt
            
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                
                if 0<= nx < n and 0<= ny < m and maps[nx][ny] == 1 and not visited[nx][ny]:
                    visited[nx][ny] = True
                    que.append((nx, ny, cnt+1))
        
        return -1
        
    answer = bfs(0, 0)
    return answer