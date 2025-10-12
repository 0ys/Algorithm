N = int(input())

if N == 0:
    print(1)
else:
    rep = 1      # '1'
    k = 1
    while rep * 10 + 1 <= N:
        rep = rep * 10 + 1
        k += 1
    print(k)
