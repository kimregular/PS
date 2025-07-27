n = int(input())
liquid = sorted(list(map(int, input().split())))

left = 0
right = len(liquid) - 1
prev = 2_000_000_001

result_left = -1
result_right = -1

while left < right:
    
    total = liquid[left] + liquid[right]
    total_abs = abs(total)

    if total_abs < prev:
        result_left, result_right = liquid[left], liquid[right]
        prev = total_abs
        
    if total < 0:
        left += 1
    else:
        right -= 1

print(result_left, result_right)

