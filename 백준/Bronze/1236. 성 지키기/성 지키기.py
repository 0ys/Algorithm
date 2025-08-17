import sys

input = sys.stdin.readline

n, m = map(int, input().split())
castle = [input().strip() for _ in range(n)]

row = sum(1 for i in range(n) if 'X' not in castle[i])

col = 0
for j in range(m):
    if all(castle[i][j] == '.' for i in range(n)):
        col += 1

print(max(row, col))