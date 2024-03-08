def solution(targets):
    answer = 0

    targets.sort(key=lambda x: (x[1], x[0]))

    line = 0
    now = 0

    for s, e in targets:
        # 구간에서 발사되는 요격미사일의 좌표를 고려함
        if line == 0:
            line = (s + e) / 2
            now = e
            answer += 1

        else:
            if s < now and now <= e:
                if not (s < line and line < e):
                    line = (s + now) / 2
            else:
                line = (s + e) / 2
                now = e
                answer += 1

    return answer


# %%
def solution(targets):
    answer = 0

    targets.sort(key=lambda x: (x[1], x[0]))

    now = 0

    for s, e in targets:
        # 요격미사일의 좌표를 고려하지 않아도 시작점이 기준점보다 작은 값이면 무조건 포함된다.
        if s < now:
            continue
        else:
            answer += 1
            now = e

    return answer
