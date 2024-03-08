def solution(n, costs):
    answer = 0

    costs = sorted(costs, key=lambda x: x[-1])

    link = set([costs[0][0]])

    while len(link) != n:
        for s, e, cost in costs:
            if s in link and e in link:  # 사이클 형성이 안되도록 함
                continue
            if s in link or e in link:
                link.update([s, e])  # 집합이므로 update해도 중복된 값은 추가되지 않는다.
                answer += cost
                break  # for문을 멈추는 이유는 업데이트된 set로 다시 확인하기 위함, break를 하지 않으면 기존 링크와 연결이 안되는 링크를 재확인할 수 없다.

    return answer


# %%
def solution(n, costs):
    # 현재 노드에 연결된 부모노드를 찾음
    def find_parents(parent, n):
        if parent[n] != n:
            # parent가 변경되어도 변경된 parent로 갱신한다.
            parent[n] = find_parents(parent, parent[n])

        return parent[n]

    # 두 노드가 연결됨을 확인하여 연결된 노드들의 공통된 부모노드로 변경
    def union_parent(parent, a, b):
        a = find_parents(parent, a)
        b = find_parents(parent, b)
        if a > b:
            parent[a] = b
        else:
            parent[b] = a

    answer = 0
    costs.sort(key=lambda x: x[-1])
    parent = [i for i in range(n)]

    for s, e, cost in costs:
        if find_parents(parent, s) != find_parents(parent, e):
            union_parent(parent, s, e)
            answer += cost

    return answer


n = 6
costs = [
    [1, 4, 1],
    [0, 3, 2],
    [1, 2, 2],
    [0, 4, 3],
    [2, 5, 3],
    [4, 5, 4],
    [0, 1, 5],
    [3, 4, 10],
]

print(solution(n, costs))
