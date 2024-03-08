def solution(n):
    answer = 0

    temp = []

    while True:
        remainder = n % 3
        n = n // 3

        temp.append(remainder)

        if n == 0:
            break

    temp = temp[-1::-1]
    # print(temp)

    for idx, t in enumerate(temp):
        answer += (3**idx) * t

    return answer
