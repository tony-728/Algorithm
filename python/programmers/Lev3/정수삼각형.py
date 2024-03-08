def solution(triangle):
    for t in range(1, len(triangle)):
        for i in range(t + 1):
            if i == 0:
                triangle[t][0] += triangle[t - 1][0]
            elif i == t:
                triangle[t][-1] += triangle[t - 1][-1]
            else:
                triangle[t][i] += max(triangle[t - 1][i - 1], triangle[t - 1][i])
    return max(triangle[-1])


# %%
def solution(triangle):
    answer = 0

    dp = [triangle[0]]

    for i in range(len(triangle) - 1):
        temp = []
        for j in range(len(triangle[i + 1])):
            if j == 0:
                x = dp[-1][j] + triangle[i + 1][j]
                temp.append(x)
            elif j == len(triangle[i + 1]) - 1:
                x = dp[-1][j - 1] + triangle[i + 1][j]
                temp.append(x)
            elif i > 0 and j > 0:
                x = dp[-1][j - 1] + triangle[i + 1][j]
                y = dp[-1][j] + triangle[i + 1][j]
                temp.append(max(x, y))

        dp.append(temp)

    answer = max(dp[-1])
    return answer
