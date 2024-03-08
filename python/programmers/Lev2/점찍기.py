# %%
from itertools import product


def solution(k, d):
    answer = 1

    a = []

    x = 1
    while True:
        val = x * k
        if val <= d:
            answer += 2
            a.append(val)
        else:
            break

        if x == 1:
            if (val**2 + val**2) ** 0.5 <= d:
                answer += 1
        else:
            if (val**2 + val**2) ** 0.5 <= d:
                answer += x + x - 1
            else:
                len_a = x - 2
                while len_a >= 0:
                    if (a[len_a] ** 2 + val**2) ** 0.5 <= d:
                        answer += (len_a + 1) * 2
                        break

                    len_a -= 1

        x += 1

    return answer


# %%
def eq(k, d):
    return (d * d - k * k) ** 0.5


def solution(k, d):
    answer = 0
    # 원의 방정식
    for i in range(0, d + 1, k):
        y = int(eq(i, d))
        answer += y // k + 1

    return answer
