from collections import deque


# 2차원 배열로 구현
def solution(board):
    answer = 0

    def bfs(start):
        N = len(board)
        # 우, 하, 좌, 상
        direction = [[0, 1, 0], [1, 0, 1], [0, -1, 2], [-1, 0, 3]]

        visited = [[1e9] * N for _ in range(N)]
        q = deque([start])

        while q:
            x, y, cost, d = q.popleft()
            for i in range(4):
                new_x = x + direction[i][0]
                new_y = y + direction[i][1]

                # board 안에 있고, 벽이 아닌지 확인
                if -1 < new_x < N and -1 < new_y < N and board[new_x][new_y] == 0:
                    new_cost = cost + 100

                    if not d == i:
                        new_cost += 500

                    if new_cost < visited[new_x][new_y]:
                        visited[new_x][new_y] = new_cost
                        q.append([new_x, new_y, new_cost, i])

        return visited[-1][-1]

    answer = min(bfs((0, 0, 0, 0)), bfs((0, 0, 0, 1)))

    return answer


# %%
from collections import deque


# 3차원 배열로 구현
def solution(board):
    answer = 1e9
    N = len(board)
    # 우, 하, 좌, 상
    direction = [[0, 1, 0], [1, 0, 1], [0, -1, 2], [-1, 0, 3]]

    # [방향][가로][세로]
    visited = [[[1e9] * N for _ in range(N)] for _ in range(4)]

    q = deque()
    q.append([0, 0, 0, 0])
    q.append([0, 0, 0, 1])

    while q:
        x, y, cost, d = q.popleft()
        for i in range(4):
            new_x = x + direction[i][0]
            new_y = y + direction[i][1]

            if -1 < new_x < N and -1 < new_y < N and board[new_x][new_y] == 0:
                new_cost = cost + 100

                if not d == i:
                    new_cost += 500

                if new_cost < visited[direction[i][2]][new_x][new_y]:
                    visited[direction[i][2]][new_x][new_y] = new_cost
                    q.append([new_x, new_y, new_cost, i])

    for i in range(4):
        answer = min(answer, visited[i][-1][-1])

    return answer
