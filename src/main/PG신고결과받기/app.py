def solution(id_list, report, k):
    id_map = {}
    id_link = {}
    for id in id_list:
        if not (id  in id_map):
            id_map[id] = 0
            id_link[id] = []
    
    for users in report:
        reporter, abuser = users.split()
        if not (reporter in id_link[abuser]):
            id_link[abuser].append(reporter)
    
    answer = []
    
    for id in id_list:
        if len(id_link[id]) >= k:
            for user in id_link[id]:
                id_map[user] += 1
    
    for id in id_map:
        answer.append(id_map[id])
    return answer