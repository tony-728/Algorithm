from collections import deque


def list_sum(arr1, arr2):
    temp = 0
    for x, y in zip(arr1, arr2):
        temp += x * y

    return temp


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    answer = 0
    N, M = map(int, input().split())

    n_list = deque(map(int, input().split()))
    m_list = deque(map(int, input().split()))

    loop = abs(N - M) + 1

    if N < M:
        n_list.extend([0] * loop)

        for _ in range(loop):
            answer = max(answer, list_sum(n_list, m_list))
            n_list.pop()
            n_list.appendleft(0)

    else:
        m_list.extend([0] * loop)

        for _ in range(loop):
            answer = max(answer, list_sum(n_list, m_list))
            m_list.pop()
            m_list.appendleft(0)

    print(f"#{test_case} {answer}")
