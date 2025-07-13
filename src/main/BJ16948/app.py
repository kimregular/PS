import sys
from collections import deque

input = sys.stdin.readline

field_len = int(input())

visited = [[False] * field_len for _ in range(field_len)]

start_x, start_y, target_x, target_y = map(int, input().split())

ds = [(-2, -1), (-2, 1), (0, -2), (0, 2), (2, -1), (2, 1)]

q = deque()
q.append((start_x, start_y, 0))
visited[start_x][start_y] = True

while q:
    cx, cy, cs = q.popleft()

    if cx == target_x and cy == target_y:
        print(cs)
        break

    for dx, dy in ds:
        nx, ny = cx + dx, cy + dy

        if not (0 <= nx < len(visited) and 0 <= ny < len(visited)): continue
        if visited[nx][ny]: continue

        q.append((nx, ny, cs + 1))
        visited[nx][ny] = True
else:
    print(-1)
