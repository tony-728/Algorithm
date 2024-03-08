from collections import deque


def solution(board):
    answer = -1

    board_v = len(board)  # 세로
    board_h = len(board[0])  # 가로

    direction = [(0, 1), (0, -1), (1, 0), (-1, 0)]

    visited = [[False] * board_h for _ in range(board_v)]

    bfs = deque()

    # 시작위치 찾기
    for i in board_v:
        if "R" in board[i]:
            bfs.append((i, board[i].index("R"), 0))
            break

    while bfs:
        x, y, count = bfs.popleft()

        if board[x][y] == "G":
            answer = count

        for dx, dy in direction:
            scope = 1

            while True:
                nx = x + dx * scope
                ny = y + dy * scope

                if (
                    nx < 0
                    or nx >= board_v
                    or ny < 0
                    or ny >= board_h
                    or board[nx][ny] == "D"
                ):
                    if visited[nx - dx][ny - dy] == False:
                        visited[nx - dx][ny - dy] = True
                        bfs.append((nx - dx, ny - dy, count + 1))

                    break

                scope += 1

    return answer


board = ["...D..R", ".D.G...", "....D.D", "D....D.", "..D...."]
solution(board)
