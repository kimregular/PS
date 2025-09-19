import sys
from collections import deque

input = sys.stdin.readline

height, width = map(int, input().split())
field = [list(input().rstrip()) for _ in range(height)]
checked = [[False] * width for _ in range(height)]

def explore(i, j):
    q = deque()
    ds = ((0, 1), (0, -1), (1, 0), (-1, 0))
    q.append((i, j))
    checked[i][j] = True

    while q:
        cx, cy = q.popleft()
        for dx, dy in ds:
            nx = cx + dx
            ny = cy + dy
            if nx < 0 or nx >= len(field) or ny < 0 or ny >= len(field[nx]): continue
            if field[nx][ny] != '#' : continue
            if checked[nx][ny]: continue

            q.append((nx, ny))
            checked[nx][ny] = True



ans = 0
for i in range(len(field)):
    for j in range(len(field[i])):
        if(checked[i][j]): continue
        if(field[i][j] != '#'): continue
        ans += 1
        explore(i, j)


print(ans)