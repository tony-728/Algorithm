from collections import deque

"""
잘못 생각한 풀이
음수에 대한 생각을 하지 못해서 뺄셈과 나눗셈처럼 순서가 중요한 사칙연산을 처리할 수 없다.
"""


def solution(N, number):
    answer = 0

    dp = [1e9] * 32001

    count = 1
    X = N
    N_list = []
    while X < 32001:
        N_list.append(X)
        dp[X] = count
        X += 10**count * N
        count += 1

    q = deque(N_list)
    q.append(N)

    while q:
        x = q.popleft()

        for n in N_list:
            for i in range(4):
                if i == 0:
                    temp = x + n
                elif i == 1:
                    temp = x - n
                elif i == 2:
                    temp = x // n
                elif i == 3:
                    temp = x * n

                if 32000 >= temp > 0:
                    if dp[temp] > dp[x] + dp[n]:
                        dp[temp] = dp[x] + dp[n]
                        q.append(temp)

    answer = dp[number] if dp[number] <= 8 else -1
    return answer


N = 1
number = 121

solution(N, number)

# %%
from collections import defaultdict


def solution(N, number):
    # dp[i]: N을 i번 사용해서 만들 수 있는 수들의 집합
    dp = defaultdict(set)

    # 최소값이 8이므로 8번만 반복
    for i in range(1, 9):
        dp[i].add(int(str(N) * i))  # NNN...N
        for j in range(1, i):
            for n1 in dp[j]:
                for n2 in dp[i - j]:
                    dp[i].add(n1 + n2)
                    dp[i].add(n1 - n2)
                    dp[i].add(n1 * n2)
                    if n2 != 0:
                        dp[i].add(n1 // n2)  # 0으로 나눌 수 없음

        if number in dp[i]:
            return i  # N을 i번 사용해서 number를 만들 수 있음

    return -1  # 최솟값이 8번보다 클 경우
