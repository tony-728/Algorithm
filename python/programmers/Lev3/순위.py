from collections import deque


def dfs(graph, index, nodes):
    q = deque()

    for i in graph[index]:
        if i not in nodes:
            nodes.append(i)
            q.append(i)

            while q:
                new_node = q.popleft()

                for j in graph[new_node]:
                    if j not in nodes:
                        nodes.append(j)
                        q.append(j)

    return nodes


def solution(n, results):
    answer = 0

    parents = [[] for _ in range(n)]
    children = [[] for _ in range(n)]

    for s, e in results:
        parents[e - 1].append(s - 1)
        children[s - 1].append(e - 1)

    for i in range(n):
        nodes = []

        # 부모노드 갯수 확인
        if parents[i]:
            nodes = dfs(parents, i, nodes)

        # 자식노드 갯수 확인
        if children[i]:
            nodes = dfs(children, i, nodes)

        if len(nodes) == n - 1:
            answer += 1

    return answer
