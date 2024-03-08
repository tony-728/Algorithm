# %%
from collections import deque


def solution(gems):
    answer = [1, 1]

    q = deque(gems)

    r = True

    index = len(q)

    while q:
        if r:
            x = q.pop()

        else:
            x = q.popleft()

        if x in q:
            index -= 1
        else:
            if index > 0:
                answer[1] = index
                index = -1
                r = False
            else:
                answer[0] = -index
                break

    return answer


# %%
def solution(gems):
    answer = []

    all_gems = set(gems)

    if len(all_gems) == 1:
        return [1, 1]

    gems_dict = {gem: 0 for gem in all_gems}

    diff = len(gems)

    for i, gem in enumerate(gems, 1):
        gems_dict[gem] = i

        if all(gems_dict.values()):
            min_index = min(gems_dict.values())
            max_index = i

            if max_index - min_index < diff:
                answer.append([min_index, max_index])
                diff = max_index - min_index

    return answer[-1]


# %%
def solution(gems):
    answer = []

    all_gems = set(gems)

    if len(all_gems) == 1:
        return [1, 1]

    gems_dict = {gem: 0 for gem in all_gems}

    diff = len(gems)

    flag = False

    for i, gem in enumerate(gems, 1):
        gems_dict[gem] = i

        if not flag:
            if all(gems_dict.values()):
                flag = True
                min_index = min(gems_dict.values())
                min_gem = gems[min_index - 1]

        if flag:
            if min_gem == gem:
                min_index = min(gems_dict.values())
                min_gem = gems[min_index - 1]

            max_index = i

            if max_index - min_index < diff:
                answer = [min_index, max_index]
                diff = max_index - min_index

    return answer
