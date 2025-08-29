n = int(input())

# m 찾기: T_m <= n < T_(m+1)
m = 0
while (m+1) * (m+2) // 2 <= n:
    m += 1

# m+1 번째 턴에서 게임이 끝나므로, 그 턴 플레이어가 짐
if (m+1) % 2 == 0:
    # 친구가 지는 상황 → 이미 푸앙이 승리
    print(0)
else:
    # 푸앙이가 지는 상황 → 다음 턴(짝수 턴)까지 버텨야 함
    next_tri = (m+1) * (m+2) // 2
    print(next_tri - n)
