def solution(s):
    answer = []

    for x in s:
        result = ""
        temp = []
        count = 0

        for i in range(len(x)):
            temp.append(x[i])

            if len(temp) > 2:
                if "".join(temp[-3:]) == "110":
                    for _ in range(3):
                        temp.pop()

                    count += 1

        s = "".join(temp)
        for i in range(len(temp) - 1, -1, -1):
            if temp[i] == "0":
                result = s[: i + 1] + "110" * count + s[i + 1 :]
                break
        else:
            result = "110" * count + s

        answer.append(result)

    return answer
