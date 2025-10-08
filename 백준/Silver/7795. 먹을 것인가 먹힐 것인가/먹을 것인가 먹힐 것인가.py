import sys
from bisect import bisect_left

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    n, m = map(int, input().split())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    
    a.sort()
    b.sort()
    
    ans = 0
    for anum in a:
        ans += bisect_left(b, anum)
            
    print(ans)