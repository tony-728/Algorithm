# %%
def solution(k, m, score):
    answer = 0
    score_len = len(score)
    score = sorted(score)

    while score_len >= m:
        answer += score[-m] * m
        del score[-m:]
        score_len -= m

    return answer


# %%
def solution(k, m, score):
    answer = 0
    score = sorted(score)

    answer = sum(score[len(score) % m :: m]) * m

    return answer
