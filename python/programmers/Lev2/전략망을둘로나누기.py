from collections import deque, defaultdict


def make_tree(wires):
    tree = defaultdict(list)

    for wire in wires:
        s, e = wire
        tree[s].append(e)
        tree[e].append(s)

    return tree


def bfs(n, tree):
    queue = deque()

    visited = [False] * (n + 1)

    tree_key = list(tree.keys())

    s = tree_key[0]

    visited[s] = True

    for node in tree[s]:
        queue.append(node)

    while queue:
        x = queue.popleft()

        if not visited[x]:
            visited[x] = True

            for node in tree[x]:
                queue.append(node)

    v = visited.count(True)
    return abs(n - v - v)


def solution(n, wires):
    answer = 1e9
    wires = deque(wires)

    len_wires = len(wires)

    for _ in range(len_wires):
        # 간선을 하나씩 제거하면서 트리를 만드는 과정
        temp = wires.popleft()
        tree = make_tree(wires)

        # 쪼개진 네트워크의 노드수 비교
        answer = min(bfs(n, tree), answer)

        wires.append(temp)

    return answer
