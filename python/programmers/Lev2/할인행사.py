from collections import defaultdict


def check(discount_number, want_number):
    for k in want_number.keys():
        if want_number[k] != discount_number.get(k):
            break
    else:
        return True

    return False


def solution(want, number, discount):
    answer = 0

    want_number = dict()
    discount_number = defaultdict(int)
    discount_len = len(discount)

    for w, n in zip(want, number):
        want_number[w] = n

    for d in discount[:10]:
        discount_number[d] += 1

    if check(discount_number, want_number):
        answer += 1

    for i in range(discount_len - 10):
        discount_number[discount[i]] -= 1
        discount_number[discount[i + 10]] += 1

        if check(discount_number, want_number):
            answer += 1

    return answer


# %%
# 카운터 객체로 만들어서 비교해도 시간초과에 걸리지 않는다.
# 다만 위에 코드보다 3배정도 느리다.

from collections import Counter


def solution(want, number, discount):
    answer = 0
    dic = {}
    for i in range(len(want)):
        dic[want[i]] = number[i]

    for i in range(len(discount) - 9):
        if dic == Counter(discount[i : i + 10]):
            answer += 1

    return answer
