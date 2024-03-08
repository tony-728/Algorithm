# %%
import math


def change(n):
    if n <= 1:
        return [str(n), "0"]
    else:
        result = []
        while n >= 1:
            x = n % 2
            n //= 2
            result.append(str(x))
        else:
            result.append("0")

        return result


def solution(numbers):
    answer = []

    for number in numbers:
        if number > 0 and math.log2(number) % 1 == 0:
            answer.append(number + 1)
        else:
            i = 1
            while True:
                temp = number + i
                count = number
                count = bin(number ^ temp).count("1")
                if count == 2 or count == 1:
                    answer.append(temp)
                    break
                else:
                    i += 1

    return answer


# %%
def solution(numbers):
    answer = []

    for number in numbers:
        if number % 2 == 0:
            answer.append(number + 1)
        else:
            temp = "0" + bin(number)[2:]
            zero_index = temp.rindex("0")

            x = temp[:zero_index] + "1" + "0" + temp[zero_index + 2 :]
            answer.append(int(x, 2))

    return answer
