def solution(s):
    answer = 0

    max_press = len(s) // 2

    result = s
    for i in range(1, max_press + 1):
        temp = ""
        press_string = ""
        count = 1

        for j in range(0, len(s), i):
            if not temp:
                temp = s[j : j + i]
            else:
                if temp == s[j : j + i]:
                    count += 1
                else:
                    if count == 1:
                        press_string += temp
                    else:
                        press_string += str(count) + temp

                    temp = s[j : j + i]
                    count = 1
        else:
            if count == 1:
                press_string += temp
            else:
                press_string += str(count) + temp

        result = min(result, press_string, key=len)

    answer = len(result)
    return answer
