# %%
def solution(x):
    answer = True

    sum_x = sum(map(int, list(str(x))))

    if x % sum_x:
        answer = False

    return answer


solution(11)
