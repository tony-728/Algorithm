def to_int(x):
    h, m = x.split(":")
    
    return int(h)*60 + int(m)

def to_date(x):
    h = x // 60
    m = x % 60
    
    return f"{str(h):0>2}:{str(m):0>2}"

def solution(n, t, m, timetable):
    answer = ''
    
    timetable = list(map(to_int ,timetable))
    
    # 시간 순서로 정렬
    timetable.sort()
    
    start = 9 * 60
    idx = 0
    # 막차 전까지 탈 수 있는 승객처리
    for time in range(n-1):
        count = 0
        now = start + (time * t)
        while idx < len(timetable):
            if timetable[idx] <= now:
                count += 1
                idx += 1               
            
            else: 
                break
            
            # 최대 승객이면 다음차에 탈 수 있도록
            if count == m:
                break
                
    # 마지막 차를 타야함
    now = start + (n-1) * t
    count = 0
    for time in timetable[idx:]:
        if now >= time:
            count += 1
            
        if count == m:
            # 막차에 모두 탔기 때문에 마지막 탄 사람보다 일찍 와야함
            answer = to_date(time-1)
            break
    else:
        # 남은 대기인원이 탈인원보다 적으므로 막차에 탐
        answer = to_date(now)
        
    return answer