def solution(n, left, right):
    answer = []

    l_count = 1
    r_count = 1
    while True:
        if n * l_count < left:
            l_count += 1

        if n * r_count < right:
            r_count += 1
        else:
            break

    l_index = left % n if l_count > 1 else left
    r_index = right % n if r_count > 1 else right

    for i in range(l_count, r_count + 1):
        if i == 1:
            x = [i for i in range(1, n + 1)]
        elif i == n:
            x = [i] * i
        else:
            x = [i] * i + [j for j in range(i + 1, n + 1)]

        answer.extend(x)

    return answer[l_index : (r_index + (r_count - l_count) * n) + 1]


# %%


def solution(n, left, right):
    answer = []
    for i in range(left, right + 1):
        answer.append(max(i // n, i % n) + 1)
    return answer
