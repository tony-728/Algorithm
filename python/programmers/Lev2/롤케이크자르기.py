def solution(topping):
    answer = 0

    a = [False] * 10001
    a_count = 0
    b = [0] * 10001
    b_count = 0

    for i in topping:
        if not b[i]:
            b_count += 1

        b[i] += 1

    while topping:
        val = topping.pop()

        if not a[val]:
            a[val] = True
            a_count += 1

        b[val] -= 1
        if b[val] == 0:
            b_count -= 1

        if a_count == b_count:
            answer += 1

    return answer
