from collections import deque


def solution(order):
    answer = 0

    queue = deque()
    order = deque(order)

    max_val = len(order)

    for i in range(1, max_val + 1):
        if order[0] == i:
            answer += 1
            order.popleft()
        else:
            if not queue:
                queue.append(i)
            else:
                while queue:
                    if queue[-1] == order[0]:
                        answer += 1
                        queue.pop()
                        order.popleft()
                    else:
                        break
                queue.append(i)

    while queue:
        if order and queue[-1] == order[0]:
            answer += 1
            queue.pop()
            order.popleft()
        else:
            break

    return answer


"""
1부터 order의 길이만큼 숫자가 진행될 때
order 의 순서대로 넣을 수 있는 횟수를 리턴

현재 i가 order의 0번째 인덱스와 다르다면 스택에 들어간 마지막 숫자와 비교하여
order의 순서대로 넣을 수 있는지 확인

ex) order = [4,3,1,2,5]
cur = 1, order = 4, stack = []
- stack = [1]

cur = 2, order = 4, stack = [1]
- stack = [1,2]

cur = 3, order = 4, stack = [1,2]
- stack = [1,2,3]

cur = 4, order = 4, stack = [1,2,3]
- answer += 1

cur = 5, order = 3, stack = [1,2,3]
- answer += 1
- stack.pop()

cur = 5, order = 1, stack = [1,2]
- 불가능 종료

총 2번 가능
"""
