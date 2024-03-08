def solution(plans):
    answer = []

    stop_working = []
    rest_time = {}

    # 시간 순서에 맞게 정렬 & 시간단위를 분으로 수정한 후 정수형으로 변경
    # plans.sort(key=lambda x: x[1])

    # for plan in plans:
    #     h, m = plan[1].split(":")
    #     plan[1] = int(h) * 60 + int(m)
    #     plan[2] = int(plan[2])

    # 위 코드를 파이썬스럽게 바꿈
    plans = sorted(
        map(lambda x: [x[0], int(x[1][:2]) * 60 + int(x[1][3:]), int(x[2])], plans),
        key=lambda x: x[1],
    )

    now_working, start_time, play_time = plans[0]

    for name, st, pt in plans[1:]:
        diff = st - start_time
        if play_time > diff:  # 정해진 시간안에 일을 못함
            stop_working.append(now_working)
            rest_time[now_working] = play_time - diff

        else:  # 정해진 시간안에 일을 함
            answer.append(now_working)

            add_time = diff - play_time  # 추가로 일할 수 있는 시간

            # 추가로 일할 수 있는 시간이 있거나 멈추고 있는 일이 있을 때
            while add_time != 0 and stop_working:
                stop_work = stop_working.pop()
                stop_work_time = rest_time[stop_work]

                if add_time >= stop_work_time:  # 추가시간동안 멈춘 일 하나를 처리가능
                    answer.append(stop_work)
                    add_time -= stop_work_time
                else:
                    # 추가시간동안 멈춘 일 하나를 가능한 만큼 수행 후 다시 멈춤
                    rest_time[stop_work] = stop_work_time - add_time
                    stop_working.append(stop_work)
                    add_time = 0

        now_working = name
        start_time = st
        play_time = pt

    else:
        answer.append(name)
        if stop_working:
            stop_working.reverse()
            answer.extend(stop_working)

    return answer


plans = [["aaa", "12:00", "20"], ["bbb", "12:10", "30"], ["ccc", "12:40", "10"]]

print(solution(plans))
