def solution(n, computers):
    parent = [i for i in range(n)]
    def find(x):
        while parent[x] != x:
            parent[x] = parent[parent[x]]
            x = parent[x]
        return x

    def union(a, b):
        a = find(a)
        b = find(b)
        if a != b: parent[b] = a
        
    for i in range(n):
        for j in range(n):
            if computers[i][j] == 1 and i != j: 
                union(i, j)
    
    answer = len(set(find(i) for i in range(n)))
    return answer