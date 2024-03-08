# %%
# DFS
def dfs(n, computer, visitied):
    visitied[n] = True

    for i in range(len(computer[n])):
        if computer[n][i] == 1 and visitied[i] == False:
            dfs(i, computer, visitied)


def solution(n, computer):
    answer = 0

    visited = [False for _ in range(n)]

    for i in range(n):
        if visited[i] == False:
            dfs(i, computer, visited)
            answer += 1

    return answer


n = 3
computer = [[1, 1, 0], [1, 1, 0], [0, 0, 1]]

"""
컴퓨터가 연결되어 있으면 1로 표현이 된다.
computer[i][i] 은 항상 1이다.

DFS나 BFS로 해결할 수 있다.
"""


solution(n, computer)

# %%
# BFS
from collections import deque


def solution(n, computer):
    answer = 0

    queue = deque()

    visited = [False for _ in range(n)]

    for i in range(n):
        if visited[i] == False:
            queue.append(i)
            while queue:
                c = queue.popleft()
                visited[c] = True

                for j in range(len(computer[c])):
                    if visited[j] == False and computer[c][j] == 1:
                        queue.append(j)

            answer += 1

    return answer


n = 3
# computer = [[1, 1, 0], [1, 1, 0], [0, 0, 1]]
computer = [[1, 1, 0], [1, 1, 1], [0, 1, 1]]


solution(n, computer)


# %%
def solution(n, computers):
    visited = [False for i in range(n)]
    answer = 0

    def dfs(i):
        visited[i] = True
        for a in range(n):
            if computers[i][a] and not visited[a]:
                dfs(a)

    for i in range(n):
        if not visited[i]:
            dfs(i)
            answer += 1

    return answer
