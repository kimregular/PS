import sys

input = sys.stdin.readline

n = int(input())
list_a = list(map(int, input().split()))
list_b = list(map(int, input().split()))

list_a.sort()
list_b.sort(reverse=True)

result = sum(a * b for a, b in zip(list_a, list_b))
print(result)