from collections import deque


def solution(queue1, queue2):
    answer = -1
    count = 0

    queue1 = deque(queue1)
    queue2 = deque(queue2)

    sum_1 = sum(queue1)
    sum_2 = sum(queue2)

    """
    모든 큐에 합을 구한 후 반씩 각 큐에 넣어서 마무리해야함
    중요.
    - 조건문마다 큐의 합을 구하면 오래걸림 
    - 큐의 합을 미리 구한 후에 더하고 빼는 과정을 해야함

    """

    while True:
        if sum_1 > sum_2:
            num = queue1.popleft()
            queue2.append(num)
            sum_1 -= num
            sum_2 += num
            count += 1

        if sum_1 < sum_2:
            num = queue2.popleft()
            queue1.append(num)
            sum_1 += num
            sum_2 -= num
            count += 1

        if sum_1 == sum_2:
            return count

        if len(queue1) == 0 or len(queue2) == 0:
            return answer


#%%
from collections import deque


def solution(queue1, queue2):
    qu_1 = deque(queue1)
    qu_2 = deque(queue2)
    sum_1 = sum(qu_1)
    sum_2 = sum(qu_2)

    for i in range(len(queue1) * 3):
        if sum_1 == sum_2:
            return i
        if sum_1 > sum_2:
            num = qu_1.popleft()
            qu_2.append(num)
            sum_1 -= num
            sum_2 += num
        else:
            num = qu_2.popleft()
            qu_1.append(num)
            sum_2 -= num
            sum_1 += num
    return -1
