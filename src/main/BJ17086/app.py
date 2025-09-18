import sys
from collections import deque

input = sys.stdin.readline

height, width = map(int, input().split())

field = [list(map(int, input().split())) for _ in range(height)]
checked = [[False] * width for _ in range(height)]
ds = ((0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1))
q = deque()
answer = -1
for i in range(len(field)):
    for j in range(len(field[i])):
        if(field[i][j] == 0): continue
        q.append((i, j, 0))
        checked[i][j] = True

while q:
    cur = q.popleft()

    answer = answer if answer >= cur[2] else cur[2]

    for x, y in ds:
        nx = cur[0] + x
        ny = cur[1] + y

        if nx < 0 or nx >= len(field) or ny < 0 or ny >= len(field[nx]): continue
        if checked[nx][ny]: continue
        q.append((nx, ny, cur[2] + 1))
        checked[nx][ny] = True

print(answer)