# %%
from itertools import combinations


def solution(number):
    answer = 0

    answer = list(map(sum, list(combinations(number, 3)))).count(0)

    return answer


number = [-2, -3, 0, 1]

list(map(sum, list(combinations(number, 3)))).count(0)
