from collections import deque


def solution(order):
    answer = 0

    queue = deque()
    order = deque(order)
    len_order = len(order)

    for i in range(1, len_order + 1):
        if i == order[0]:
            answer += 1
            order.popleft()
        else:
            while queue and queue[-1] == order[0]:
                queue.pop()
                order.popleft()
                answer += 1
            else:
                queue.append(i)
    else:
        while queue and queue[-1] == order[0]:
            queue.pop()
            order.popleft()
            answer += 1

    return answer
