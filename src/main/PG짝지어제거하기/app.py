def solution(s):
    stck = []
    for c in s:
        if stck and stck[-1] == c:
            stck.pop()
        else:
            stck.append(c)
    return 0 if stck else 1

if __name__=="__main__":
    import sys
    print(solution(sys.stdin.readline().strip()))