from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    
    n = 102
    maps = [[0]*n for _ in range(n)]
    
    rectangle = [[x1*2, y1*2, x2*2, y2*2] for x1, y1, x2, y2 in rectangle]
    characterX *= 2; characterY *= 2
    itemX *= 2; itemY *= 2
    
    for lx, ly, rx, ry in rectangle:
        for x in range(lx, rx+1):
            for y in range(ly, ry+1):
                maps[x][y] = 1
    for lx, ly, rx, ry in rectangle:
        for x in range(lx+1, rx):
            for y in range(ly+1, ry):
                maps[x][y] = 0

        
    def bfs():
        que = deque()
        que.append((characterX, characterY, 0))
        visited = [[False]*n for _ in range(n)]
        visited[characterX][characterY] = True
        
        while que:
            x, y, c = que.popleft()
            
            if x == itemX and y == itemY:
                return c // 2
            
            for i in range(4):
                nx, ny = x+dx[i], y+dy[i]
                if 0 <= nx < 102 and 0 <= ny < 102:
                    if not visited[nx][ny] and maps[nx][ny] == 1:
                        visited[nx][ny] = True
                        que.append((nx, ny, c+1))
        
        return 0
    
    
    answer = bfs()
    return answer