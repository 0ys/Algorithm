def solution(participant, completion):
    hash = dict()
    for p in participant:
        if p in hash:
            hash[p] += 1
        else:
            hash[p] = 1
    
    for c in completion:
        hash[c] -= 1
    
    answer = ''
    for k, v in hash.items():
        if v == 1:
            answer = k
    
    return answer