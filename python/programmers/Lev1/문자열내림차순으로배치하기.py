# %%
def solution(s):
    answer = ""

    answer = "".join(sorted(s, reverse=True))
    return answer


# %%
# 소문자 먼저 내림차순으로 정렬 후 대문자 내림차순 정렬

s = "AVZbcdefg"

upper_s = []
lower_s = []


"".join(sorted(s, reverse=True))
