# %%
def solution(n):
    if n % 2:
        return 0

    d = [0] * 5001
    d[2] = 3
    d[4] = 11
    for i in range(6, n + 1, 2):
        d[i] = (3 * d[i - 2] + 2 * sum(d[1 : i - 2]) + 2) % 1000000007

    return d[n]


solution(6)
