n, limit = map(int, input().split())

disks = list(map(int, input().split()))

left = max(disks)
right = sum(disks)

answer = right

while left <= right:
    mid = (left + right) // 2
    cnt = 1
    total = 0

    for i in disks:
        if total + i > mid:
            cnt += 1
            total = i
        else:
            total += i
    
    if cnt <= limit:
        answer = mid
        right = mid - 1
    else:
        left = mid + 1


print(answer)

