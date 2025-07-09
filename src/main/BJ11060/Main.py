# BJ11060

import sys

input = sys.stdin.readline

field_len = int(input())

field = list(map(int, input().split()))
visited = [False] * field_len

from collections import deque

q = deque()
q.append([0, 0]) # location, step
visited[0] = True

while q:
    location, step = q.popleft()

    if location is len(field) - 1:
        print(step)
        break
    
    for i in range(1, field[location] + 1):
        next_location = location + i
        if next_location >= len(field): continue
        if visited[next_location]: continue
        q.append([next_location, step + 1])
        visited[next_location] = True

else:
    print(-1)
    