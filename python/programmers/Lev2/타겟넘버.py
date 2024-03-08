# %%
def dfs(numbers, target, index, n):
    result = 0

    if index == len(numbers):
        if n == target:
            return 1
        else:
            return 0

    result += dfs(numbers, target, index + 1, n + numbers[index])
    result += dfs(numbers, target, index + 1, n - numbers[index])

    return result


def solution(numbers, target):
    answer = dfs(numbers, target, 0, 0)

    return answer


# %%
from collections import deque


def solution(numbers, target):
    answer = 0

    queue = deque()
    n = len(numbers)

    queue.append((numbers[0], 0))
    queue.append((-numbers[0], 0))

    while queue:
        number, idx = queue.popleft()
        idx += 1

        if idx == n:
            if number == target:
                answer += 1

        else:
            queue.append((number + numbers[idx], idx))
            queue.append((number - numbers[idx], idx))

    return answer


solution([1, 1, 1, 1, 1], 3)
