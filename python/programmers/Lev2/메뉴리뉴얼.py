from itertools import combinations
from collections import Counter


def solution(orders, course):
    answer = []

    for c in course:
        counter_dict = Counter()
        for order in orders:
            counter_dict += Counter(combinations(sorted(order), c))
        else:
            if counter_dict:
                counter_list = counter_dict.most_common()
                n = 0
                m_val = 0
                while True:
                    key, val = counter_list[n]

                    if val > 1 and val >= m_val:
                        answer.append("".join(key))
                    else:
                        break

                    n += 1
                    m_val = val

    answer.sort()

    return answer
