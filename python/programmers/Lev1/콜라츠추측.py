# %%
def solution(num):
    answer = 0

    while num != 1:
        if num % 2:  # 홀수
            num *= 3
            num += 1
            answer += 1
        else:  # 짝수
            num /= 2
            answer += 1

        if answer == 500:
            answer = -1
            break

    return answer


solution(626331)
