from itertools import combinations


def solution(numbers):
    answer = []

    temp = list(combinations(numbers, 2))

    for x, y in temp:
        num = x + y

        if not num in answer:
            answer.append(num)

    answer.sort()
    return answer
