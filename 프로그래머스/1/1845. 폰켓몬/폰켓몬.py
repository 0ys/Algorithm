def solution(nums):
    r = len(nums) / 2
    pool = set(nums)
    
    answer = 0
    if len(pool) >= r: answer = r
    else: answer = len(pool)
    
    return answer