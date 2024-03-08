# %%
def solution(t, p):
    answer = 0

    p_len = len(p)

    for i in range(len(t) - p_len + 1):
        if t[i : p_len + i] <= p:
            answer += 1

    return answer


solution("10203", "15")

# %%
"1234"[0:6]
