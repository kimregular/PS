from collections import deque
import sys

input = sys.stdin.readline

def topology_sort():
    num_of_buildings, orders = map(int, input().split())
    graph = [[] for _ in range(num_of_buildings + 1)]
    indegrees = [0] * (num_of_buildings + 1)
    dp = [0] * (num_of_buildings + 1)
    time_requisites = [0] + list(map(int, input().split()))

    for _ in range(orders):
        pre, post = map(int, input().split())
        graph[pre].append(post)
        indegrees[post] += 1
    
    target = int(input())
    q = deque()

    for i in range(1, num_of_buildings + 1):
        if indegrees[i] == 0:
            dp[i] = time_requisites[i]
            q.append(i)
    
    while q:
        cur = q.popleft()
        for nxt in graph[cur]:
            indegrees[nxt] -= 1
            dp[nxt] = max(dp[nxt], dp[cur] + time_requisites[nxt])
            if(indegrees[nxt] == 0):
                q.append(nxt)
    
    return dp[target]


def solution():
    test_cases = int(input())
    for _ in range(test_cases):
        print(topology_sort())

solution()
