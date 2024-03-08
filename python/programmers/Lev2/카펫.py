# %%
# 약수 찾기
def div(n):
    result = []
    for i in range(1, int(n**0.5) + 1):
        if n % i == 0:
            result.append(i)
            result.append(n // i)  # 자기 자신도 반복해서 넣어줌

    return result


def solution(brown, yellow):
    answer = []

    yellow_divs = div(yellow)

    # 1, 2 케이스는 바로 처리
    if yellow == 1:
        answer = [3, 3]

    elif len(yellow_divs) == 2:
        answer = [yellow_divs[1] + 2, yellow_divs[0] + 2]

    # 약수를 확인하면서 brown의 갯수와 맞는 값을 찾는다.
    else:
        for i in range(0, len(yellow_divs) - 1, 2):
            width = max(yellow_divs[i], yellow_divs[i + 1])
            height = min(yellow_divs[i], yellow_divs[i + 1])
            if (width + height) * 2 + 4 == brown:
                answer = [width + 2, height + 2]
                break

    return answer


# %%


# 가로 * 세로 = 격자 합, 둘레의 합(가로*2 + 세로*2 - 겹치는부분4) = 갈색 둘레
def solution(brown, red):
    for i in range(1, int(red ** (1 / 2)) + 1):
        if red % i == 0:
            if 2 * (i + red // i) == brown - 4:
                return [red // i + 2, i + 2]
