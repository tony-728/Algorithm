# %%
def solution(n, m, section):
    answer = 0

    start = section[0]
    answer += 1

    for s in section[1:]:
        if s < start + m:
            continue
        else:
            start = s
            answer += 1

    return answer


n = 4
m = 1
section = [1, 2, 3, 4]

solution(n, m, section)
