def solution(N, road, K):
    answer = [0] * N

    graph = {i: [10001 if j != i - 1 else 1 for j in range(N)] for i in range(1, N + 1)}

    for r in road:
        s, e, c = r
        graph[s][e - 1] = min(graph[s][e - 1], c)
        graph[e][s - 1] = graph[s][e - 1]

    visit = [False] * N

    # DFS
    def dfs(i, road, k):
        visit[i] = True

        if k > K:
            return
        else:
            answer[i] = 1
            for i, e in enumerate(road):
                if visit[i] == False and e < 10001:
                    dfs(i, graph[i + 1], k + e)
                    visit[i] = False

    for i, e in enumerate(graph[1]):
        if i == 0:
            answer[i] = 1
            visit[i] = True
        elif e < 10001:
            dfs(i, graph[i + 1], e)
            visit[i] = False

    return sum(answer)


N = 6
road = [[1, 2, 1], [1, 3, 2], [2, 3, 2], [3, 4, 3], [3, 5, 2], [3, 5, 3], [5, 6, 1]]
K = 4
solution(N, road, K)

# %%
import heapq


def dijkstra(dist, adj):
    # 출발노드를 기준으로 각 노드들의 최소비용 탐색
    heap = []
    heapq.heappush(heap, [0, 1])  # 거리,노드
    while heap:
        cost, node = heapq.heappop(heap)
        for c, n in adj[node]:
            if cost + c < dist[n]:
                dist[n] = cost + c
                heapq.heappush(heap, [cost + c, n])


def solution(N, road, K):
    dist = [float("inf")] * (N + 1)  # dist 배열 만들고 최소거리 갱신할거임
    dist[1] = 0  # 1번은 자기자신이니까 거리 0
    adj = [[] for _ in range(N + 1)]  # 인접노드&거리 기록할 배열
    for r in road:
        adj[r[0]].append([r[2], r[1]])
        adj[r[1]].append([r[2], r[0]])
    dijkstra(dist, adj)
    return len([i for i in dist if i <= K])


N = 6
road = [[1, 2, 1], [1, 3, 2], [2, 3, 2], [3, 4, 3], [3, 5, 2], [3, 5, 3], [5, 6, 1]]
K = 4
solution(N, road, K)
