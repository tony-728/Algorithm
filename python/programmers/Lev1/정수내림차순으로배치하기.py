# %%
def solution(n):
    answer = sorted(list(str(n)), reverse=True)

    answer = int("".join(answer))

    return answer
