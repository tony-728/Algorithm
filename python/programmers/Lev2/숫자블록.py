# %%
def is_prime(n):
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    else:
        return True


def div(n):
    d = []
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            d.append(i)
            if n // i <= 10000000:
                return n // i

    if len(d) >= 1:
        return d[-1]


def solution(begin, end):
    answer = []

    for i in range(begin, end + 1):
        if i == 1:
            answer.append(0)
        else:
            # 소수이면 1을 넣음
            if is_prime(i):
                answer.append(1)
            # 소수가 아니면 무조건 약수가 존재
            else:
                d = div(i)
                answer.append(d)

    return answer


begin = 100000000
end = 1000000000

solution(begin, end)
