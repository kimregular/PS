def solution(arr):
    lcm = arr[0]
    for i in arr[1:]:
        lcm = getLCM(lcm, i)
        
    return lcm

def getGCD(a, b):
    if b == 0: return a
    return getGCD(b, a % b)

def getLCM(a, b):
    return a * b // getGCD(a, b)