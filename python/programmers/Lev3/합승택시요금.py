# %%
from collections import deque, defaultdict


def solution(n, s, a, b, fares):
    answer = 0

    fares_dict = defaultdict(list)
    cost_dict = dict()

    for start, e, c in fares:
        x = min(start, e)
        y = max(start, e)

        fares_dict[x].append(y)
        fares_dict[y].append(x)

        cost_dict[str(x) + str(y)] = c

    """
    start에서 A, B까지의 각각 최단 거리를 찾고 겹치는 부분이 있으면 그 만큼을 제거 
    
    각각 최단 거리를 찾게 되면 안되고 공통으로 이동할 수 있는 최대 거리를 찾아야함
    1. A와 B를 최단거리로 이을 수 있는 거리를 찾고
    2-1 A와 B의 최단거리에 S가 포함되어 있으면 종류
    2-2 A와 B의 최단거리에 S가 포함되지 않았으면 S에서 AB에 닿을 수 있는 최단 거리를 찾는다.
    """

    # 1. A와 B의 최단거리를 찾는다. A부터 시작 DFS로 찾기
    visited = [False] * (n + 1)
    q = deque()
    q.append(a)
    cost = 0
    visited[a] = True

    best_costs = dict()
    ab_list = deque()
    ab_list.append(a)

    def find_best_ABpath(visited, q, cost, ab_list):
        node = q.popleft()

        for x in fares_dict[node]:
            if visited[x] == False:
                visited[x] = True
                ab_list.append(x)

                min_node = min(node, x)
                max_node = max(node, x)

                cost += cost_dict[str(min_node) + str(max_node)]

                if x == b:
                    best_costs[cost] = list(ab_list)
                    visited[x] = False
                    cost -= cost_dict[str(min_node) + str(max_node)]
                    ab_list.pop()

                else:
                    q.append(x)

                    find_best_ABpath(visited, q, cost, ab_list)

                    visited[x] = False
                    cost -= cost_dict[str(min_node) + str(max_node)]
                    ab_list.pop()

    find_best_ABpath(visited, q, cost, ab_list)
    best_ABcost = min(best_costs.keys())
    best_path = best_costs[best_ABcost]

    if s in best_path:
        answer = best_ABcost
    else:
        visited = [False] * (n + 1)

        q = deque()
        q.append(s)

        cost = 0
        visited[s] = True
        best_cost = []

        def dfs(visited, q, cost):
            node = q.popleft()

            for x in fares_dict[node]:
                if visited[x] == False:
                    visited[x] = True

                    min_node = min(node, x)
                    max_node = max(node, x)

                    cost += cost_dict[str(min_node) + str(max_node)]

                    if x in best_path:
                        best_cost.append(cost)
                        visited[x] = False
                        cost -= cost_dict[str(min_node) + str(max_node)]
                    else:
                        q.append(x)

                        dfs(visited, q, cost)

                        visited[x] = False
                        cost -= cost_dict[str(min_node) + str(max_node)]

        dfs(visited, q, cost)
        answer = best_ABcost + min(best_cost)

    return answer


# %%


# 플로이드-와샬 알고리즘
def solution(n, s, a, b, fares):
    INF = 10000000
    answer = INF
    graph = [[INF] * n for _ in range(n)]

    # 자기 자신으로 가는 간선은 0으로 처리
    for i in range(n):
        for j in range(n):
            if i == j:
                graph[i][j] = 0

    # 주어진 그래프 정보로 초기화
    for node1, node2, fee in fares:
        graph[node1 - 1][node2 - 1] = fee
        graph[node2 - 1][node1 - 1] = fee

    # 초기화된 그래프를 통해 노드에서 다른 노드로 이동할 수 있는 최단 거리를 구한다.
    for k in range(n):
        for i in range(n):
            for j in range(n):
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

    # 최단 거리를 구한 후 시작 노드에서 임의의 노드를 거쳐 A, B노드로 이동하는 최단 거리를 구한다.
    for t in range(n):
        temp = graph[s - 1][t] + graph[t][a - 1] + graph[t][b - 1]
        answer = min(temp, answer)

    return answer


n = 6
s = 4
a = 6
b = 2
fares = [
    [4, 1, 10],
    [3, 5, 24],
    [5, 6, 2],
    [3, 1, 41],
    [5, 1, 24],
    [4, 6, 50],
    [2, 4, 66],
    [2, 3, 22],
    [1, 6, 25],
]

print(solution(n, s, a, b, fares))
# %%

# 다익스트라 알고리즘 with heap
import heapq


def solution(n, s, a, b, fares):
    INF = 10000000
    answer = INF
    graph = [[] * (n + 1) for _ in range(n + 1)]

    for node1, node2, fee in fares:
        # node1 - > node2 가는 요금이 fee
        graph[node1].append((node2, fee))
        # node2 - > node1 가는 요금이 fee
        graph[node2].append((node1, fee))

    def dijkstra(s):
        q = []
        # 최단거리 테이블을 무한으로 초기화
        distance = [INF] * (n + 1)
        # 거리(금액), 노드번호 순서
        heapq.heappush(q, (0, s))
        # 시작노드로 가는 최단거리는 0
        distance[s] = 0

        while q:
            dist, now = heapq.heappop(q)
            # 현재 노드가 이미 처리된 노드면 무시
            if distance[now] < dist:
                continue

            for g in graph[now]:
                cost = dist + g[1]
                if cost < distance[g[0]]:
                    distance[g[0]] = cost
                    heapq.heappush(q, (cost, g[0]))
        return distance

    # dijkstra 알고리즘으로 각 노드에서 다른 노드로 이동할 때에 최단 거리 리스트를 모두 구한다.
    # 형태는 list of list로 바깥리스트의 인덱스가 시작 노드번호이고 내부 리스트가 시작 노드로부터 최단거리리스트이다.
    distance_list = [[]] + [dijkstra(i) for i in range(1, n + 1)]

    # (문제에서 주어진)시작 노드에서부터 임의의 노드를 지나 a, b로 이동하는 최단 거리를 찾는다.
    for i in range(1, n + 1):
        answer = min(
            distance_list[s][i] + distance_list[i][a] + distance_list[i][b], answer
        )

    return answer


n = 6
s = 4
a = 6
b = 2
fares = [
    [4, 1, 10],
    [3, 5, 24],
    [5, 6, 2],
    [3, 1, 41],
    [5, 1, 24],
    [4, 6, 50],
    [2, 4, 66],
    [2, 3, 22],
    [1, 6, 25],
]

print(solution(n, s, a, b, fares))
