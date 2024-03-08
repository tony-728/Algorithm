# %%
# 안되는 풀이
# 모든 조합을 만들어서 k번쨰를 찾으려고 하면 안된다.
from itertools import permutations


def solution(n, k):
    def factorial(n):
        a = []
        x = 1
        for i in range(1, n + 1):
            x *= i
            a.append(x)

        return a

    if n <= 2:
        p = permutations(range(1, n + 1), n)
        return list(p)[k - 1]

    total = factorial(n)

    group = total[-1] // n

    fir = k // group
    n_range = list(range(1, n + 1))

    check = [n_range[fir]]
    del n_range[fir]

    div = k % group

    if len(total) > 3:
        group = total[-2] // (n - 1)

        snd = div // group

        check.append(n_range[snd])
        del n_range[snd]

        div = div % group

    p = permutations(n_range, n - 1 if len(total) <= 3 else n - 2)

    return check + list(list(p)[div - 1])


n = 2
k = 1
solution(n, k)


# %%
# 되는 풀이
def solution(n, k):
    answer = []

    def factorial(n):
        a = []
        x = 1
        for i in range(1, n + 1):
            x *= i
            a.append(x)

        return a

    n_range = list(range(1, n + 1))

    # n이 1, 2일 땐 조합이 적으므로 빠르게 처리하도록 한다.
    if n == 1:
        answer = [1]

    elif n == 2:
        answer = [[1, 2], [2, 1]]
        answer = answer[k - 1]

    else:
        # 1~n 숫자의 조합의 갯수를 만든다.
        total = factorial(n)
        while n > 2:
            # 가장 앞자리 숫자를 찾기 위한 작업
            t = total.pop()

            # 현재 자릿수에서 가능한 조합의 갯수를 찾는다.
            group = t // n
            n -= 1

            # 그룹에서 k가 위치하는 자리수를 찾는다.
            fir = k // group
            # k번째 위치를 다시 찾는다.
            k %= group

            answer.append(n_range[fir if k else fir - 1])
            del n_range[fir if k else fir - 1]

            if n == 2:
                answer.append(n_range[k - 1])
                del n_range[k - 1]
                answer.append(n_range[-1])

    return answer


n = 4
k = 17
solution(n, k)
