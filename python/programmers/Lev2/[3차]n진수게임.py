def n_number(x, n):
    # n진법으로 x가 어떻게 표현되는지 구해야함

    if x == 0:
        return ["0"]

    result = []

    over_ten = {10: "A", 11: "B", 12: "C", 13: "D", 14: "E", 15: "F"}

    while x >= 1:
        r = x % n
        result.append(str(r) if r < 10 else over_ten[r])
        x //= n

    result = result[::-1]

    return result


def solution(n, t, m, p):
    # n: 진법, t: 미리 구할 숫자의 갯수, m: 게임에 참여하는 인원, p: 자신의 순서

    answer = ""
    x = 0
    n_x = [-1]

    while True:
        n_x.extend(n_number(x, n))

        x += 1

        if len(n_x) > t * m:
            break

    if m == p:
        p = 0

    for i, x in enumerate(n_x):
        if i == 0:
            continue

        if i % m == p:
            answer += x
            t -= 1

        if t == 0:
            break

    return answer
