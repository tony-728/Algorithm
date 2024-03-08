# %%
def solution(land):
    answer = 0

    answer = land[0]

    for row in land[1:]:
        check = []
        for index, val in enumerate(row):
            temp = list(map(lambda x: x + val, answer))
            del temp[index]
            check.append(max(temp))
        else:
            answer = check

    answer = max(answer)

    return answer


land = [[1, 2, 3, 5], [5, 6, 7, 8], [4, 3, 2, 1]]
solution(land)

# DP
