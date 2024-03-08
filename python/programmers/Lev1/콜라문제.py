# %%
def solution(a, b, n):
    answer = 0
    etc = 0
    while n >= a:
        if n % a:  # a로 나누어 떨어지지 않을 때
            etc = n % a
            n = (n // a) * b
            answer += n
            n += etc
        else:  # a로 나누어 떨어질 때
            n = (n // a) * b
            answer += n

    return answer


solution(3, 2, 10)
