# %%
from collections import Counter


def solution(X, Y):
    answer = []
    x_list = list(X)
    y_list = list(Y)

    min_list = min(x_list, y_list)
    max_counter = Counter(max(x_list, y_list))

    for i in min_list:
        if i in max_counter.keys() and max_counter[i]:
            answer.append(i)
            max_counter[i] -= 1

    if answer:
        answer = str(int("".join(sorted(answer, reverse=True))))
    else:
        answer = "-1"

    return answer


solution("100", "2023045")

# %%

from collections import Counter


def solution(X, Y):
    answer = ""

    x_count = Counter(X)
    y_count = Counter(Y)

    zero_check = [False] * 10

    x_dict = dict(sorted(x_count.items(), reverse=True))

    for key in x_dict.keys():
        if key in y_count.keys():
            zero_check[int(key)] = True
            # x의 값과 y의 값을 비교해서 추가
            answer += key * min(x_count[key], y_count[key])

    if answer:
        zero = False
        non_zero = False
        for check in range(10):
            if check == 0 and zero_check[check]:
                zero = True
            elif zero_check[check]:
                non_zero = True
                break

        if non_zero:
            answer = "".join(answer)

        elif zero and not non_zero:
            answer = "0"
    else:
        answer = "-1"

    return answer


solution("100000000", "20200")


# %%

from collections import Counter


def solution(X, Y):
    answer = ""

    x_count = Counter(X)
    y_count = Counter(Y)

    zero_check = [False] * 10

    # 큰 숫자로 미리 정렬하여 이후에 정렬하는데 시간을 사용하지 않음
    # 여기서 정렬하면 좋은 점은 아무리 큰 숫자의 딕셔너리라도 10개를 넘지 않는다.
    x_dict = dict(sorted(x_count.items(), reverse=True))

    for key in x_dict.keys():
        if key in y_count.keys():
            zero_check[int(key)] = True
            # x의 값과 y의 값을 비교해서 추가
            answer += key * min(x_count[key], y_count[key])

    if answer:
        if len(answer) == answer.count("0"):
            answer = "0"

    else:
        answer = "-1"

    return answer


solution("100", "201")
