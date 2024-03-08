# %%
def solution(s, skip, index):
    answer = ""
    alpha = set([chr(i) for i in range(97, 123)])

    new_alpha = sorted(alpha - set(skip))
    new_alpha_len = len(new_alpha)

    new_alpha

    for i in s:
        answer += new_alpha[(new_alpha.index(i) + index) % new_alpha_len]

    return answer


s = "aukks"  # 모두 소문자
skip = "wbqd"
index = 5
