# %%
def solution(left, right):
    answer = 0

    for i in range(left, right + 1):
        if check_div(i):
            answer += i
        else:
            answer -= i

    return answer


def check_div(num):
    count = 0
    for i in range(1, num // 2 + 1):
        if num % i == 0:
            count += 1
    else:
        count += 1
    return False if count % 2 else True


solution(13, 17)
