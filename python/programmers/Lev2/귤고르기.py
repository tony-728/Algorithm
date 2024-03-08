# %%
from collections import Counter


def solution(k, tangerine):
    answer = 0

    count_tangerine = Counter(tangerine)

    for ct in count_tangerine.most_common():
        answer += 1
        k -= ct[-1]

        if k <= 0:
            break

    return answer


k = 6
tangerine = [1, 3, 2, 5, 4, 5, 2, 3]
