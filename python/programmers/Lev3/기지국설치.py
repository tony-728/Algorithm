import math


def solution(n, stations, w):
    answer = 0
    start = 0
    r = w * 2 + 1

    for station in stations:
        temp = station - w - 1
        end = station + w

        if start < temp:
            answer += math.ceil((temp - start) / r)

        start = end
    else:
        if start < n:
            answer += math.ceil((n - start) / r)

    return answer
