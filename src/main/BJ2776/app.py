import sys

input = sys.stdin.readline

test_cases = int(input())

answer = []
for _ in range(test_cases):
    n = int(input())
    nums1 = set(map(int, input().split()))
    m = int(input())
    nums2 = list(map(int, input().split()))

    for n in nums2:
        answer.append("1\n" if n in nums1 else "0\n")


sys.stdout.write("".join(answer))
