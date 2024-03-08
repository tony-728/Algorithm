def solution(money):
    answer = 0

    len_money = len(money)

    case1 = money[:-1]
    case2 = money[1:]

    if len_money == 1:
        answer = money[0]

    else:
        for i in range(1, len_money - 1):
            if i == 1:
                case1[i] = max(case1[i - 1], case1[i])
                case2[i] = max(case2[i - 1], case2[i])
            else:
                case1[i] = max(case1[i - 1], case1[i - 2] + case1[i])
                case2[i] = max(case2[i - 1], case2[i - 2] + case2[i])

        answer = max(case1[-1], case2[-1])

    return answer


# %%
def solution(a):
    x1, y1, z1 = a[0], a[1], a[0] + a[2]
    x2, y2, z2 = 0, a[1], a[2]
    for i in a[3:]:
        x1, y1, z1 = y1, z1, max(x1, y1) + i
        x2, y2, z2 = y2, z2, max(x2, y2) + i
    return max(x1, y1, y2, z2)
