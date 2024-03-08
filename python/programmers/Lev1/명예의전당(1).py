# %%
def solution(k, score):
    answer = []
    honor_list = []
    honor_len = 0

    for i in score:
        if honor_len < k:
            honor_list.append(i)
            honor_len += 1

        else:
            honor_list = sorted(honor_list, reverse=True)
            if honor_list[-1] < i:
                honor_list.pop()
                honor_list.append(i)

        answer.append(min(honor_list))

    return answer


solution(4, [0, 300, 40, 300, 20, 70, 150, 50, 500, 1000])


# %%
from collections import deque


def solution(k, score):
    answer = []
    honor_list = []
    honor_len = 0

    for i in score:
        if honor_len < k:
            honor_list.append(i)
            honor_len += 1

        else:
            min_val = min(honor_list)
            if min_val < i:
                honor_list.remove(min_val)
                honor_list.append(i)

        answer.append(min(honor_list))

    return answer
