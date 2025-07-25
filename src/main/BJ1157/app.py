
target = input()

cnt = {}

for c in target:
    t = c.lower()
    if t in cnt:
        cnt[t]+=1
    else:
        cnt[t] = 1

# 최댓값 계산
max_count = max(cnt.values())

# 최댓값을 가진 알파벳 찾기
max_chars = [char for char, count in cnt.items() if count == max_count]

# 결과 출력
if len(max_chars) > 1:
    print('?')
else:
    print(max_chars[0].upper())
