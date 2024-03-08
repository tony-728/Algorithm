def solution(sticker):
    answer = 0

    len_sticker = len(sticker)

    case1 = sticker[:-1]
    case2 = sticker[1:]

    # 위에서 만든 점화식은 주어진 스티커의 길이가 1보다 클 때 적용할 수 있다.
    if len_sticker == 1:
        answer = sticker[0]

    else:
        for i in range(1, len_sticker - 1):
            if i == 1:
                case1[i] = max(case1[i - 1], case1[i])
                case2[i] = max(case2[i - 1], case2[i])
            else:
                case1[i] = max(case1[i - 1], case1[i - 2] + case1[i])
                case2[i] = max(case2[i - 1], case2[i - 2] + case2[i])

        answer = max(case1[-1], case2[-1])

    return answer
