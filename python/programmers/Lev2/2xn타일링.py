# %%
def solution(n):
    d = [0] * 60000

    div = 1000000007

    d[1] = 1
    d[2] = 2
    for i in range(3, n + 1):
        d[i] = (d[i - 1] + d[i - 2]) % div
    return d[n]
