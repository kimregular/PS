

def can_install(distance, locations, routers):
    count = 1
    last = locations[0]

    for i in range(1, len(locations)):
        if locations[i] - last >= distance:
            count += 1
            last = locations[i]
    return count >= routers


if __name__ == '__main__':
    num_of_houses, routers = map(int, input().split())
    
    locations = sorted([int(input()) for _ in range(num_of_houses)])

    left = 1
    right = locations[-1] - locations[0]
    result = 0

    while left <= right:
        mid = (left + right) // 2

        if can_install(mid, locations, routers):
            result = mid
            left = mid + 1
        else:
            right = mid - 1

    print(result)


