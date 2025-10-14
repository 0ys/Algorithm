while True:
    line = input()
    if line == '#':
        break
    vowels = {'a', 'e', 'i', 'o', 'u'}
    count = sum(1 for ch in line.lower() if ch in vowels)
    print(count)
