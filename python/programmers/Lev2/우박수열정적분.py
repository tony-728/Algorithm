def solution(k, ranges):
    answer = []

    points = [k]

    while k > 1:
        if k % 2:
            k = k * 3 + 1
        else:
            k //= 2

        points.append(k)

    n = len(points) - 1

    for s, e in ranges:
        result = 0

        e = n + e

        if s > e:
            answer.append(-1)

        elif s > 0 and s == e:
            answer.append(0)

        else:
            for i in range(s, e):
                result += (points[i] + points[i + 1]) / 2

            answer.append(result)

    return answer


# %%
def solution(k, ranges):
    answer = []
    arr = [k]
    while k > 1:
        if not k % 2:
            k //= 2
        else:
            k = k * 3 + 1
        arr.append(k)
    area = [0]
    for i in range(len(arr) - 1):
        area.append(area[-1] + (arr[i] + arr[i + 1]) / 2)
    for a, b in ranges:
        if a >= len(area) or b - 1 < -len(area):
            answer.append(-1)
        elif area[b - 1] - area[a] < 0:
            answer.append(-1)
        else:
            answer.append(area[b - 1] - area[a])
    return answer
