# %%
from collections import deque


def solution(elements):
    answer = 0
    count = set()

    queue = deque()
    elements = deque(elements)

    len_elements = len(elements)

    check = 0
    point = 0

    for i in range(1, len_elements + 1):
        while check <= len_elements:
            queue.append(elements[point % len_elements])
            point += 1

            if len(queue) == i:
                # sum을 사용하여 시간이 오래걸림
                # deque를 사용하여 가독성을 높임
                count.add(sum(queue))
                queue.popleft()
                check += 1

        queue.clear()
        point = 0
        check = 0

    answer = len(count)

    return answer


# %%
def solution(elements):
    answer = 0
    count = set()

    len_elements = len(elements)

    check = 0
    point = 0
    sum_ele = 0

    for i in range(1, len_elements + 1):
        while check <= len_elements:
            sum_ele += elements[point % len_elements]
            point += 1

            if point >= i:
                # deque로 넣고 빼는 작업을 인덱스로 계산하여 sum에 들이는 시간을 줄임
                count.add(sum_ele)
                sum_ele -= elements[(point - i) % len_elements]
                check += 1

        check = 0
        point = 0
        sum_ele = 0

    answer = len(count)

    return answer
