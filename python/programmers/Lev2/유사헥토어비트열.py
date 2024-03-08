def count_one(n, pos):
    if n == 1:
        return "11011"[:pos].count("1")

    area, index = divmod(pos, 5 ** (n - 1))

    cnt = 0
    if area < 2:
        cnt = 4 ** (n - 1) * area + count_one(n - 1, index)
    elif area == 2:
        cnt = 2 * 4 ** (n - 1)
    elif area > 2:
        # area-1은 중간에 0구간이 있기 때문에 1을 빼줌
        cnt = 4 ** (n - 1) * (area - 1) + count_one(n - 1, index)
    return cnt


def solution(n, l, r):
    # f(n) = f(n-1)f(n-1)00000f(n-1)f(n-1)
    answer = count_one(n, r) - count_one(n, l - 1)
    return answer


n = 3
l, r = 6, 23
solution(n, l, r)
