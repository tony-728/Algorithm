# %%
from collections import deque


# 앞에서 찾아가는 것은 시간초과
def solution(numbers):
    answer = []

    numbers = deque(numbers)

    max_val = 1000000

    while numbers:
        val = numbers.popleft()

        if max_val <= val:
            answer.append(-1)
        else:
            for n in numbers:
                if val < n:
                    answer.append(n)
                    break
            else:
                max_val = val
                answer.append(-1)

    return answer


# %%
from collections import deque

# 스택을 사용하지 않고 풀면 시간초과가 발생한다.


def solution(numbers):
    answer = []

    len_numbers = len(numbers)

    stack = deque()

    # 스택 사용
    for i in range(len_numbers - 1, -1, -1):
        while stack and stack[-1] <= numbers[i]:
            stack.pop()
        else:
            if not stack:
                answer.append(-1)
            else:
                answer.append(stack[-1])

            stack.append(numbers[i])

    answer = answer[::-1]
    return answer
