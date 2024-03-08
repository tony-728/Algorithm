# %%
def solution(n):
    answer = 0

    for i in range(1, n):
        if n % i == 0:
            answer += i
    else:
        answer += n
    return answer


n = 12


# %%
def solution(n):
    answer = 0
    check = []
    for i in range(1, n // 2 + 1):
        if n % i == 0:
            check.append(i)
            answer += i
    else:
        check.append(n)
        answer += n

    print(check)
    return answer


solution(12)
