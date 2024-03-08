from collections import deque


def solution(people, limit):
    answer = 0

    people = deque(sorted(people))  # 정렬한 후 deque로 자료구조를 변경
    weight = 0

    while people:
        # 가장 큰 숫자를 pop
        person = people.pop()
        weight += person

        # 가장 큰 숫자와 앞에서 부터 작은 숫자를 더하면서 limit보다 작은 최대 수를 구함
        while True:
            if people and weight + people[0] <= limit:
                p = people.popleft()
                weight += p
            else:
                answer += 1
                weight = 0
                break

    return answer
