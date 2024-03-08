# 시간초과 발생하는 풀이
def func(box, lst, pos):
    temp = pos

    while box > 0 and any(lst):
        if lst[temp]:
            if lst[temp] <= box:
                box -= lst[temp]
                lst[temp] = 0
                temp -= 1

            else:  # 가장 먼 곳 배달이 최대보다 많은 경우
                lst[temp] -= box
                box = 0
        else:
            temp -= 1

    return temp


def solution(cap, n, deliveries, pickups):
    answer = 0

    pos = n - 1
    d_pos = pos
    p_pos = pos

    # 먼 곳부터 처리
    # 배달을 한 후에 수거처리
    while any(deliveries) or any(pickups):
        box = cap

        # 배달 혹은 수거든 가장 먼곳에 일이 있으면 가야함
        if deliveries[pos] or pickups[pos]:
            # 가장 먼 곳에 배달할 물량이 있는지 확인
            # - 최대 개수보다 적으면 다음 먼 곳에 배달할 물량이 있는지 확인
            # 최대 개수만큼 채워지면 가장 먼 곳에 배달물량 처리
            # 배달 물량을 모두 처리할 때까지 다음 먼 곳으로 물량 처리
            d_pos = func(box, deliveries, d_pos)

            # 배달 물량을 모두 처리했으면
            # 가장 먼곳에 회수 물량이 있는지 확인
            # 회수 물량 처리
            # 최대 개수보다 적으면 다음 먼 곳 회수 물량이 있는지 확인
            # 회수 물량 처리
            p_pos = func(box, pickups, p_pos)

            answer += pos + 1

            pos = max(d_pos, p_pos)

        else:
            pos -= 1

    # 총 이동 거리는 이동거리 * 2
    return answer * 2


# # %%
def solution(cap, n, deliveries, pickups):
    answer = 0

    d = 0
    p = 0

    for i in range(n - 1, -1, -1):
        cnt = 0

        d -= deliveries[i]
        p -= pickups[i]

        while d < 0 or p < 0:
            d += cap
            p += cap
            cnt += 1

        answer += (i + 1) * 2 * cnt

    return answer
