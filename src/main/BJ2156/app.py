import sys

input = sys.stdin.readline

n = int(input())
wine = [0] * (n + 1)
dp = [0] * (n + 1) # dp[i] = i번째 잔까지 마셨을 때의 최댓값

for i in range(1, n + 1):
    wine[i] = int(input())
    if i == 1:
        dp[i] = wine[i]
    elif i == 2:
        dp[i] = wine[i] + wine[i - 1]
    else:
        dp[i] = max(dp[i - 1], dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i])

print(dp[n])