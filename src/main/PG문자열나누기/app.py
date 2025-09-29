def solution(s):
    answer = 0
    
    while s:
        cur_target = s[0]
        other_count = 0
        target_count = 0
        
        for i in range(len(s)):
            if s[i] != cur_target:
                other_count += 1
            else:
                target_count+= 1
            
            if other_count == target_count:
                answer += 1
                s = s[i+1:]
                break
        else:
            answer += 1
            break
            
    return answer