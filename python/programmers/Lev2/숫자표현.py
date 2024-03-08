# %%
def solution(n):
    answer = 1
    if n > 1:
        for i in range(1, n):
            a = i + 1
            while i < n:
                i += a
                a += 1
            if i == n:
                answer += 1

    return answer


n = 15

solution(n)


# %%
# 등차수열의 합공식을 이용한 풀이
def solution(n):
    return len([i for i in range(1, n + 1, 2) if n % i == 0])


solution(9)
