import sys
from collections import deque

input = sys.stdin.readline

width, height = map(int, input().split())

field = []
for i in range(0, height):
    field.append(list(input().strip()))

checked = [[False] * width for _ in range(height)]

white_power = 0
blue_power = 0

def bfs(x, y):
    ds = [[0, 1], [1, 0], [-1, 0], [0, -1]]
    team = field[x][y]
    result = 1
    q = deque()
    q.append([x, y])
    checked[x][y] = True

    while q:
        i, j = q.popleft()

        for dx, dy in ds:
            nx, ny = i + dx, j + dy

            if(len(field) <= nx or nx < 0 or len(field[nx]) <= ny or ny < 0): continue
            if(field[nx][ny] != team): continue
            if(checked[nx][ny]): continue

            result+=1
            q.append([nx, ny])
            checked[nx][ny] = True
    return result

for i in range(height):
    for j in range(width):
        if checked[i][j]: continue
        num_of_people = bfs(i, j)
        if(field[i][j] == 'W'): white_power += num_of_people**2
        else : blue_power += num_of_people**2


print(white_power, blue_power, end=" ")

