import sys

input = sys.stdin.readline

N, P = map(int, input().split())
num = set()
ans = set()

temp = N * N % P
while temp not in ans:
    if temp in num:
        ans.add(temp)
    num.add(temp)
    temp = temp * N % P

print(len(ans))