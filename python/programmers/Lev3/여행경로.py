# 실패
from collections import defaultdict
import heapq


def dfs(airports, now, answer, check):
    answer.append(now)

    if len(answer) == check:
        return answer

    if airports[now]:
        now = heapq.heappop(airports[now])
        answer = dfs(airports, now, answer, check)
    else:
        airports[answer.pop()]


def solution(tickets):
    answer = []

    check = len(tickets) + 1

    airports = defaultdict(list)
    # 인천에서 출발할 수 있는 경우를 확인하고 알파벳순으로 정렬해서 찾아야겠다.

    for ticket in tickets:
        heapq.heappush(airports[ticket[0]], ticket[1])

    now = "ICN"

    answer = dfs(airports, now, answer, check)

    return answer


# tickets = [["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]
# tickets = [["ICN", "A"], ["ICN", "A"], ["ICN", "A"], ["A", "ICN"], ["A", "ICN"]]
tickets = [["ICN", "A"], ["A", "B"], ["A", "C"], ["C", "A"], ["B", "D"]]

print(solution(tickets))


# %%

# 초패스트 알고리즘

from collections import defaultdict


def solution(tickets):
    # 1. 그래프 생성
    routes = defaultdict(list)

    for start, end in tickets:
        routes[start].append(end)

    # 2. 시작점 - [끝점] 역순으로 정렬
    for r in routes.keys():
        routes[r].sort(reverse=True)

    # 3. DFS 알고리즘으로 path를 만들어줌.
    st = ["ICN"]
    path = []

    while st:
        top = st[-1]

        if top not in routes or len(routes[top]) == 0:
            path.append(st.pop())
        else:
            st.append(routes[top][-1])
            routes[top] = routes[top][:-1]

    answer = path[::-1]
    return answer


tickets = [["ICN", "A"], ["A", "B"], ["A", "C"], ["C", "A"], ["B", "D"]]

print(solution(tickets))


# %%

# 정석 DFS


def solution(tickets):
    answer = []

    visited = [False] * len(tickets)

    def dfs(airport, path):
        if len(path) == len(tickets) + 1:
            answer.append(path)
            return

        for idx, ticket in enumerate(tickets):
            if airport == ticket[0] and visited[idx] == False:
                visited[idx] = True
                dfs(ticket[1], path + [ticket[1]])
                visited[idx] = False

    dfs("ICN", ["ICN"])

    answer.sort()

    return answer[0]


tickets = [["ICN", "A"], ["A", "B"], ["A", "C"], ["C", "A"], ["B", "D"]]

print(solution(tickets))
