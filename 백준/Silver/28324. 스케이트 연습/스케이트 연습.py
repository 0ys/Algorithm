import sys

input = sys.stdin.readline

N = int(input())
V = list(map(int, input().split()))

a = [0]*N
a[-1] = min(V[-1], 1)

for i in range(N-2, -1, -1):
    a[i] = min(V[i], a[i+1] + 1)

print(sum(a))