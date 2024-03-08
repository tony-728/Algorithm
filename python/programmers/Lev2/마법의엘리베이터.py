import math


def solution(storey):
    answer = 0

    while storey:
        temp = round(storey * 0.1) * 10

        answer += abs(temp - storey)

        storey = temp // 10

    return answer
