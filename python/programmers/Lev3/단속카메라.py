def solution(routes):
    answer = 0

    routes.sort(key=lambda x: x[1])

    camera = -30001

    for s, e in routes:
        if camera < s:
            camera = e
            answer += 1

    return answer
