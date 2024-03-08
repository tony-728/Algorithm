# %%
def solution(x, y, n):
    memorize = [1e9] * (y + 1)
    memorize[x] = 0
    for i in range(x, y + 1):
        if memorize[i] == 1e9:
            continue
        if i + n <= y:
            memorize[i + n] = min(memorize[i + n], memorize[i] + 1)
        if i * 2 <= y:
            memorize[i * 2] = min(memorize[i * 2], memorize[i] + 1)
        if i * 3 <= y:
            memorize[i * 3] = min(memorize[i * 3], memorize[i] + 1)

    if memorize[y] == 1e9:
        return -1
    return memorize[y]


# %%
def solution(x, y, n):
    answer = 0
    s = set()
    s.add(x)

    while s:
        if y in s:
            return answer

        nxt = set()
        for i in s:
            if i + n <= y:
                nxt.add(i + n)
            if i * 2 <= y:
                nxt.add(i * 2)
            if i * 3 <= y:
                nxt.add(i * 3)
        s = nxt
        answer += 1

    return -1


x = 10
y = 40
n = 5

solution(x, y, n)
