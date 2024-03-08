# %%
def solution(dartResult):
    answer_list = []

    temp = 0
    digit_check = False

    for i in dartResult:
        if i.isdigit():
            if temp and not digit_check:
                answer_list.append(temp)
            elif digit_check:
                i = "10"
            digit_check = True
            temp = int(i)

        elif i == "S":
            digit_check = False
        elif i == "D":
            digit_check = False
            temp = temp**2
        elif i == "T":
            digit_check = False
            temp = temp**3
        elif i == "*":
            digit_check = False
            if answer_list:
                answer_list[-1] = answer_list[-1] * 2
            temp *= 2
        elif i == "#":
            digit_check = False
            temp *= -1
    else:
        answer_list.append(temp)

    return sum(answer_list)


# dartResult = "1S2D*3T"
dartResult = "1D2S#10S"

solution(dartResult)


# %%
# 정규표현식
import re


def solution(dartResult):
    bonus = {"S": 1, "D": 2, "T": 3}
    option = {"": 1, "*": 2, "#": -1}
    p = re.compile("(\d+)([SDT])([*#]?)")
    dart = p.findall(dartResult)
    for i in range(len(dart)):
        if dart[i][2] == "*" and i > 0:
            dart[i - 1] *= 2
        dart[i] = int(dart[i][0]) ** bonus[dart[i][1]] * option[dart[i][2]]

    answer = sum(dart)
    return answer
