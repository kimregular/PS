import sys

input = sys.stdin.readline

test_cases = int(input())



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
