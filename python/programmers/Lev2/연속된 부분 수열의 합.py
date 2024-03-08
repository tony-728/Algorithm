from collections import deque


def solution(sequence, k):
    answer = []

    queue = deque()

    start = 0
    end = 0
    temp = 0
    for i in range(len(sequence)):
        queue.append(sequence[i])
        temp += sequence[i]

        end = i

        if temp == k:
            answer.append([start, end])
            temp -= queue.popleft()
            start += 1

        elif temp > k:
            while temp > k:
                temp -= queue.popleft()
                start += 1

                if temp == k:
                    answer.append([start, end])
                    temp -= queue.popleft()
                    start += 1
                    break

    answer = sorted(answer, key=lambda x: x[1] - x[0])

    return answer[0]
