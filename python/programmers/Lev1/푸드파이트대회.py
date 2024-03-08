# %%
def solution(food):
    answer = ""

    for i, ele in enumerate(food[1:], 1):
        if ele % 2 and ele > 1:  # 홀수, 1보다 큰 홀수
            answer += ele // 2 * i
        else:  # 짝수
            answer += ele // 2 * i

    answer += "0" + answer[::-1]

    return answer


food = [1, 3, 4, 6]

# %%

a = "1234"

a[::-1]
