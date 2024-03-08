import math


def solution(r1, r2):
    answer = 0

    for x in range(1, r2 + 1):
        # r2원과의 교점,y 보다 작거나 같은 정수
        r2_inter = int((r2**2 - x**2) ** 0.5)

        # r1원과의 교점,y 보다 크거나 같은 정수
        # x가 r1보다 큰 경우 y=0
        r1_inter = math.ceil((r1**2 - x**2) ** 0.5) if x < r1 else 0

        answer += r2_inter - r1_inter + 1

    answer *= 4

    return answer
