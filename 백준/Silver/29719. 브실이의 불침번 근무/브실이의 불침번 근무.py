MOD = 1000000007

def modpow(a, b):
    result = 1
    while b > 0:
        if b % 2 == 1:
            result = result * a % MOD
        a = a * a % MOD
        b //= 2
    return result

N, M = map(int, input().split())

total = modpow(M, N)
no_bsil = modpow(M-1, N)

print((total - no_bsil + MOD) % MOD)
