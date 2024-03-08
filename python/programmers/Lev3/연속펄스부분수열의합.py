def solution(sequence):
    answer = 0
    temp = None

    for i in range(len(sequence)):
        if i % 2:
            sequence[i] = sequence[i]
        else:
            sequence[i] = -sequence[i]

        if temp == None:
            temp = sequence[i]
        else:
            temp += sequence[i]
            answer = max(answer, temp, key=abs)

    temp = answer

    for s in sequence:
        temp -= s
        answer = max(temp, answer, key=abs)
    else:
        answer = abs(answer)

    return answer
