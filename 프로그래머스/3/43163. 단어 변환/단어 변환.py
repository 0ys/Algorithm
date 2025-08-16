from collections import deque

def solution(begin, target, words):
    answer = 0
    
    def word_diff(s1, s2):
        if len(s1) == len(s2):
            diff = sum(1 for a, b in zip(s1, s2) if a != b)
            return diff == 1
    
    def bfs():
        que = deque()
        que.append((begin, 0))
        visited = []
        visited.append(begin)

        while que:
            now, cnt = que.popleft()

            if now == target:
                return cnt

            for nw in words:
                if nw not in visited and word_diff(now, nw):
                    visited.append(nw)
                    que.append((nw, cnt+1))
        return 0
    
    answer = bfs()
    
    return answer