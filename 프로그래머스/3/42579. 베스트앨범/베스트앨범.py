from collections import defaultdict

def solution(genres, plays):
    songs = defaultdict(list)
    for i, (g, p) in enumerate(zip(genres, plays)):
        songs[g].append((i, p))               # 장르 g에 (고유번호, 재생수)

    # 장르 총 재생수 내림차순으로 장르 순서 정하기
    genre_rank = sorted(songs, key=lambda g: sum(p for _, p in songs[g]), reverse=True)

    answer = []
    for g in genre_rank:
        # 장르 안에서는 재생수 내림차순, 같으면 고유번호 오름차순
        best = sorted(songs[g], key=lambda x: (-x[1], x[0]))
        answer.extend([i for i, _ in best[:2]])
    return answer
