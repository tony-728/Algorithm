from collections import deque
import copy


def solution(maps):
    def bfs(point, visited, stop):
        q = deque()
        q.append(point)

        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        while q:
            x, y = q.popleft()

            for i in range(4):
                new_x = x + dx[i]
                new_y = y + dy[i]

                if -1 < new_x < n and -1 < new_y < m:
                    if (
                        not maps[new_x][new_y] == "X"
                        and visited[new_x][new_y] > visited[x][y] + 1
                    ):
                        visited[new_x][new_y] = visited[x][y] + 1
                        q.append((new_x, new_y))

        return

    answer = -1

    n = len(maps)
    m = len(maps[0])

    visited = [[1e9 for _ in range(m)] for _ in range(n)]

    for i in range(n):
        if "S" in maps[i]:
            start_point = (i, maps[i].index("S"))

        if "L" in maps[i]:
            lever_point = (i, maps[i].index("L"))

        if "E" in maps[i]:
            end_point = (i, maps[i].index("E"))

    st_visited = copy.deepcopy(visited)
    st_visited[start_point[0]][start_point[1]] = 0
    bfs(start_point, st_visited, "L")

    if st_visited[lever_point[0]][lever_point[1]] < 1e9:
        visited[lever_point[0]][lever_point[1]] = 0
        bfs(lever_point, visited, "E")

        if visited[end_point[0]][end_point[1]] < 1e9:
            answer = (
                st_visited[lever_point[0]][lever_point[1]]
                + visited[end_point[0]][end_point[1]]
            )

    return answer
