import sys

def main():
    input = sys.stdin.readline

    n, nc = map(int, input().split())
    text = input().strip()

    if n > len(text):
        print(0)
        return
    
    diff = { text[i:i+n] for i in range(len(text) - n + 1)}
    print(len(diff))
    
if __name__ == "__main__":
    main()