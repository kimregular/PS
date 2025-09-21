import sys
from queue import PriorityQueue

input = sys.stdin.readline

num_of_books, num_of_infos = map(int, input().split())
graph = [[] for _ in range(num_of_books + 1)]
indegrees = [0] * (num_of_books + 1)

for i in range(num_of_infos):
    pre, post = map(int, input().split())
    graph[pre].append(post)
    indegrees[post] += 1

q = PriorityQueue()
result = []
for i in range(1, len(indegrees)):
    if indegrees[i] == 0:
        q.put(i)

while not q.empty():
    cur = q.get()
    result.append(cur)

    for nxt in graph[cur]:
        indegrees[nxt] -= 1
        if indegrees[nxt] == 0:
            q.put(nxt)

print(*result)