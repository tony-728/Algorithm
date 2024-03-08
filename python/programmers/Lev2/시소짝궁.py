# %%

# 문제의 weights 길이가 10만이므로 이중반복문을 사용하면 시간초과가 발생한다.
from collections import defaultdict


def solution(weights):
    answer = 0

    weight_dict = defaultdict(set)

    for weight in weights:
        for i in [2, 3, 4]:
            weight_dict[weight].add(weight * i)

    temp = weights.pop()

    while weights:
        for key in weights:
            if key == temp:
                answer += 1
            else:
                answer += len(weight_dict[temp].intersection(weight_dict[key]))

        temp = weights.pop()

    return answer


# %%
from collections import Counter


def solution(weights):
    answer = 0
    count = Counter(weights)

    # 같은 weight가 복수로 존재할 때
    for k, v in count.items():
        if v > 1:
            # 조합 vC2만큼 만들 수 있다.
            answer += v * (v - 1) // 2

    weights = set(weights)
    for item in weights:
        for check in (3 / 4, 2 / 3, 2 / 4):  # 비율
            if item * check in weights:
                answer += count[item] * count[item * check]

    return answer
