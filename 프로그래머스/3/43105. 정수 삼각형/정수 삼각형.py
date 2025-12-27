def solution(triangle):
    n = len(triangle)
    dp = [[0] * n for _ in range(n)]
    
    for layer in range(n):
        for idx in range(len(triangle[layer])):
            dp[layer][idx] = max(dp[layer-1][idx], dp[layer-1][idx-1]) + triangle[layer][idx]
                
    answer = max(dp[n-1])
    return answer