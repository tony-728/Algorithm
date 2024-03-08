"""
하나의 노드에서 다른 노드들로의 최단거리를 구하는 다익스트라 알고리즘을 이용한 풀이

"""

import heapq


def solution(n, roads, sources, destination):
    answer = []

    graph = [[] * (n + 1) for _ in range(n + 1)]

    for s, e in roads:
        graph[s].append((e, 1))
        graph[e].append((s, 1))

    def dijkstra(s):
        q = []
        distance = [1e9] * (n + 1)

        heapq.heappush(q, (0, s))

        distance[s] = 0

        while q:
            dist, now = heapq.heappop(q)
            if distance[now] < dist:
                continue

            for g in graph[now]:
                cost = dist + g[1]
                if cost < distance[g[0]]:
                    distance[g[0]] = cost
                    heapq.heappush(q, (cost, g[0]))

        return distance

    distance_list = dijkstra(destination)

    for s in sources:
        cost = distance_list[s]
        if cost == 1e9:
            answer.append(-1)
        else:
            answer.append(cost)

    return answer


# %%
"""
큐를 이용한 풀이 
모든 노드로부터의 최단 거리를 구하는 것이 아닌 하나의 도착지점으로부터 다른 노드로의 최단 거리를 구하는 문제이고
간선의 비용이 모두 1로 통일되어 있기 때문에 큐를 이용한 풀이가 가능하다.
"""

from collections import deque


def solution(n, roads, sources, destination):
    paths = [[] for _ in range(n + 1)]
    for s, t in roads:
        paths[s].append(t)
        paths[t].append(s)

    dist = [-1 for _ in range(n + 1)]
    dist[destination] = 0
    s = deque([destination])

    while s:
        v = s.popleft()
        d = dist[v]

        for u in paths[v]:
            if dist[u] == -1:
                dist[u] = d + 1
                s.append(u)

    return [dist[i] for i in sources]
