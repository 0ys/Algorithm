import sys
import copy

input = sys.stdin.readline

n, m = map(int, input().split())

cook = []
ans = []

for _ in range(n):
    query = input().split()
    
    if query[0] == 'order':
        num, time = query[1], query[2]
        cook.append((num, time))
    elif query[0] == 'sort':
        cook.sort(key=lambda x: (int(x[1]), int(x[0])))
    elif query[0] == 'complete':
        table = query[1]
        cook = [(c, t) for c, t in cook if c != table]
    
    if not cook:
        ans.append("sleep")
    else:
        ans.append(" ".join(c for c, _ in cook))

print("\n".join(ans))
    
    