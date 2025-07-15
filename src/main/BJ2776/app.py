import sys

input = sys.stdin.readline

test_cases = int(input())

<<<<<<< HEAD
answer = []
for _ in range(test_cases):
    n = int(input())
    nums1 = set(map(int, input().split()))
    m = int(input())
    nums2 = list(map(int, input().split()))

    for n in nums2:
        answer.append("1\n" if n in nums1 else "0\n")


sys.stdout.write("".join(answer))
=======


def solution():
    input()
    lst = list(map(int, input().split()))
    input()
    comp_list = list(map(int, input().split()))
    result = ""

    freq = {}

    for i in lst:
        if i not in freq:
            freq[i] = 1
    for i in comp_list:
        if i in freq:
            result += "1"
        else:
            result += "0"
        result += "\n"
    return result
    
answer = ""

for _ in range(test_cases):
    answer += solution()
    answer += "\n"


print(answer)
>>>>>>> 3f340b3 (PG : https://school.programmers.co.kr/learn/courses/30/lessons/12973)
