# %%
def solution(n):
    answer = 0
    while n > 0:
        if n % 2:  # add
            n -= 1
            answer += 1
        n = n // 2

    return answer


"""
홀수 일때 와 짝수 일때 경우가 나누어진다.

N이 홀수 일때는 N-1까지 최소한의 거리로 움직여야하고

N이 짝수 일때는 N/2까지 최소한의 거리오 움직여야 한다.
"""
N = 5000
solution(N)


# %%
def solution(n):
    return bin(n).count("1")


bin(7)
