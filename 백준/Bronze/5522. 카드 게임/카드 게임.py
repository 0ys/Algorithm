import sys

total_score = 0

for _ in range(5):
    score = int(sys.stdin.readline())
    total_score += score

print(total_score)