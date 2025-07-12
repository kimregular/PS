import sys
from collections import deque

input = sys.stdin.readline

height, width = map(int, input().split())

field = [list(input().strip()) for _ in range(height)]
checked = [[False] * width for _ in range(height)]
ds = [(0, 1), (0, -1), (1, 0), (-1, 0)]

FENCE = "#"
WOLF = "v"
SHEEP = "o"

num_of_sheep = 0
num_of_wolves = 0

def bfs(x, y):
    sheep = wolves = 0

    q = deque()
    q.append((x, y))
    checked[x][y] = True

    while q:
        cx, cy = q.popleft()

        if field[cx][cy] == WOLF: wolves += 1
        elif field[cx][cy] == SHEEP: sheep += 1

        for dx, dy in ds:
            nx = cx + dx
            ny = cy + dy

            if not (0 <= nx < len(field) and 0 <= ny < len(field[nx])): continue
            if field[nx][ny] == FENCE: continue
            if checked[nx][ny]: continue

            q.append((nx, ny))
            checked[nx][ny] = True

    return (0, wolves) if wolves >= sheep else (sheep, 0)




for i in range(height):
    for j in range(width):
        if field[i][j] == FENCE: continue
        if checked[i][j]: continue
        sheep, wolves = bfs(i, j)
        num_of_sheep += sheep
        num_of_wolves += wolves


print(num_of_sheep, num_of_wolves)