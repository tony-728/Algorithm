# %%
def solution(N, stages):
    answer = []
    sorted_stages = sorted(stages)
    user = len(sorted_stages)

    check = sorted_stages[0]
    count = 1

    false_rate = {i: 0 for i in range(1, N + 1)}

    for stage in sorted_stages[1:]:
        if check == stage:
            count += 1
        else:
            false_rate[check] = count / user
            check = stage
            user -= count
            count = 1
    else:
        if check <= N and user:
            false_rate[check] = count / user

    answer = sorted(false_rate, key=lambda x: false_rate[x], reverse=True)
    return answer


# N = 5
# stages = [2, 1, 2, 6, 2, 4, 3, 3]


# N = 4
# stages = [4,4,4,4,4]

N = 5
stages = [1, 2, 3, 4, 5, 6]

solution(N, stages)
