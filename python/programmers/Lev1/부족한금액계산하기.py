# %%
def solution(price, money, count):
    answer = 0

    for i in range(count):
        answer += price * (i + 1)

    return max(0, answer - money)


solution(3, 20, 4)

# %%


# 등차수열의 합 공식을 이용한 풀이
def solution(price, money, count):
    return max(0, price * (count + 1) * count // 2 - money)
