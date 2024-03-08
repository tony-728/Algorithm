# %%
def solution(s):
    answer = []

    alphabat_check = {}
    for i, a in enumerate(s):
        if a in alphabat_check:
            answer.append(i - alphabat_check[a])
            alphabat_check[a] = i
        else:
            alphabat_check[a] = i
            answer.append(-1)

    return answer


solution("foobar")
