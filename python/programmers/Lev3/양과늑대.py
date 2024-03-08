from itertools import permutations


def solution(info, edges):
    answer = 0

    edges.sort(key=lambda x: (x[0], x[1]))

    len_info = len(info)

    reverse = [0] * len_info
    path = {}

    sheep = set()
    wolf = set()

    for p, c in edges:
        reverse[c] = p  # 각 노드의 부모노드가 무엇인지 파악

    # 양 노드인 경우 루트노드로 갈때까지의 늑대 노드를 파악
    for index, i in enumerate(info):
        if i == 0:
            path[index] = set()
            temp = index

            # 이 조건을 수정해야함
            # 0 - 1 - 0 - 0 인 경우 아무 것도 못먹지만 3번의 경우 먹을 수 있는 조건이 됨
            # 항상 루트 노드부터 시작함
            while reverse[temp] > 0:
                if info[reverse[temp]]:
                    path[index].add(reverse[temp])

                temp = reverse[temp]

            # 늑대가 없다면 루트노드에서 바로 이동할 수 있음
            # 기본적으로 소유할 수 있는 양
            if not path[index]:
                sheep.add(index)
                del path[index]

    # 녹대를 거쳐서 가야하는 양노드의 순서 조합
    for p in permutations(path.keys()):
        c_sheep = sheep.copy()
        c_wolf = wolf.copy()

        for n in p:
            if path[n]:
                if len(c_sheep) > len(c_wolf | path[n]):
                    c_sheep.add(n)
                    c_wolf = c_wolf | path[n]

        answer = max(answer, len(c_sheep))

    return answer


# %%
def solution(info, edges):
    visited = [False] * len(info)
    answer = 0

    def dfs(sheep, wolf):
        if sheep > wolf:
            answer = max(answer, sheep)
        else:
            return

        for p, c in edges:
            if visited[p] and not visited[c]:  # 부모노드는 방문, 자식노드는 방문 아님
                visited[c] = True  # 자식노드 방문처리
                if info[c] == 0:  # 자식노드가 양일 때
                    dfs(sheep + 1, wolf)
                else:
                    dfs(sheep, wolf + 1)
                visited[c] = False  # 자식노드 방문처리 취소

    # 루트 노드부터 시작
    visited[0] = True
    dfs(1, 0)

    return answer


info = [0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0]
edges = [
    [0, 1],
    [0, 2],
    [1, 3],
    [1, 4],
    [2, 5],
    [2, 6],
    [3, 7],
    [4, 8],
    [6, 9],
    [9, 10],
]

solution(info, edges)
