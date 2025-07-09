import sys
input = sys.stdin.readline

num_of_friends = int(input())
num_of_edges = int(input())

graph = [[] for _ in range(num_of_friends + 1)]

for _ in range(num_of_edges):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)
ans = 0

from collections import deque

q = deque()
q.append([1, 0]) # node, depth
checks = [False] * (num_of_friends + 1)
checks[1] = True

while q :
    cur_node, depth = q.pop()

    for friend in graph[cur_node]:

        if checks[friend]: continue
        if depth >= 2: continue
        q.append([friend, depth + 1])
        checks[friend] = True
        ans += 1

print(ans)
    
