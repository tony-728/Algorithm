# %%
def isPrime(n):
    if n == 2:
        return True
    else:
        for i in range(2, int(n**0.5) + 1):
            if n % i == 0:
                return False
            else:
                continue

        return True


def solution(n):
    answer = 0
    for i in range(2, n + 1):
        if isPrime(i):
            answer += 1
    return answer


solution(5)
