# %%
def solution(a):
    answer = len(a)
    index = 0
    temp = a[0]

    if len(a) <= 2:
        return len(a)

    for i in range(len(a)):
        if temp > a[i]:
            temp = a[i]
            index = i

    left = a[:index]
    right = a[index:][::-1]
    right.pop()

    left_min = left[0]
    for l in left[1:]:
        if left_min > l:
            left_min = l
        else:
            answer -= 1

    if right:
        right_min = right[0]
        for r in right[1:]:
            if right_min > r:
                right_min = r
            else:
                answer -= 1

    return answer


# %%
def solution(a):
    answer = 1  # 가장 작은 값이 항상 남게 되기 때문에 1
    M = min(a)

    for _ in range(2):
        m = a[0]
        i = 1
        while m != M:
            # 최소값 갱신이 되는 경우 전체 최소값을 터뜨리는 기회로 남을 수 있다.
            if m >= a[i]:
                m = a[i]
                answer += 1
            """
            반대로 최소값 갱신이 안되는 경우는 현재 값을 터뜨리기 위해
            한번에 기회를 사용하므로 전체 최소값을 터뜨릴 수 없기 때문에 무조건 터뜨려진다.
            """
            i += 1
        a.reverse()

    return answer


a = [-16, 27, 65, -2, 58, -92, -71, -68, -61, -33]
solution(a)
