import sys
from collections import deque

input = sys.stdin.readline

n, m, v = map(int, input().split())
graph = [[] for _ in range(n + 1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for edges in graph:
    edges.sort()

def bfs(start):
    visited = [0] * (n + 1)   # 0이면 미방문, 1 이상이면 방문순서
    queue = deque([start])
    order = 1
    visited[start] = order

    while queue:
        cur_node = queue.popleft()
        for neighbor in graph[cur_node]:
            if visited[neighbor] == 0:
                order += 1
                visited[neighbor] = order
                queue.append(neighbor)
    return visited

bfs_order = bfs(v)
print('\n'.join(map(str, bfs_order[1:])))
