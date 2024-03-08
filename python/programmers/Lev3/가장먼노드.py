# %%
from collections import deque


def solution(n, edge):
    answer = 0

    graph = [[] for i in range(n + 1)]
    for s, e in edge:
        graph[s].append(e)
        graph[e].append(s)

    count = {i: 0 for i in range(1, n + 1)}
    visited = [False] * (n + 1)
    q = deque()
    q.append(1)
    count[1] = 0

    # bFS로 풀기
    while q:
        node = q.popleft()
        visited[node] = True

        for n in graph[node]:
            if not visited[n]:
                visited[n] = True
                q.append(n)
                count[n] = count[node] + 1

    max_node = max(count.values())
    for k, v in count.items():
        if v == max_node:
            answer += 1

    return answer


# %%
from collections import deque


def solution(n, edge):
    answer = 0

    graph = [[] for _ in range(n + 1)]
    for s, e in edge:
        graph[s].append(e)
        graph[e].append(s)

    count = [0] * (n + 1)
    count[1] = 0

    visited = [False] * (n + 1)
    visited[1] = True

    q = deque()
    q.append(1)

    # bFS로 풀기
    while q:
        node = q.popleft()

        for n in graph[node]:
            if not visited[n]:
                visited[n] = True
                q.append(n)
                count[n] = count[node] + 1

    answer = count.count(max(count))

    return answer


n = 6
vertex = [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]

print(solution(n, vertex))
