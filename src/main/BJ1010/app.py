import math

n = int(input())

for _ in range(n):
    r, n = map(int, input().split())
    print(math.comb(n, r))