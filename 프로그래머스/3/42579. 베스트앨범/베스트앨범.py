def solution(genres, plays):
    hashmap = dict()
    cnt = []
    song = []
    
    idx = 0
    for i in range(len(genres)):
        genre = genres[i]
        play = plays[i]
        
        if genre not in hashmap:
            song.append([])
            cnt.append([genre, 0])
            hashmap[genre] = idx
            idx += 1
        
        song[hashmap[genre]].append((i, play))
        cnt[hashmap[genre]][1] += play
    
    cnt.sort(reverse=True, key=lambda x:x[1])
    
    answer = []
    for genre, c in cnt:
        temp = sorted(song[hashmap[genre]], key=lambda x:(-x[1], x[0]))
        for t in temp[:2]:
            answer.append(t[0])
    
    return answer