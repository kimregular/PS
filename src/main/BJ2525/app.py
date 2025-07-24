hour, minute = input().split()
hour, minute = int(hour), int(minute)
plus_minute = int(input())

minute += plus_minute

hour += minute // 60
minute %= 60
hour %= 24

print(hour, minute)