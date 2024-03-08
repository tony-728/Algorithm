# %%
def solution(rows, columns, queries):
    answer = []

    board = []
    for r in range(1, rows + 1):
        row = []
        for c in range(1, columns + 1):
            row.append((r - 1) * columns + c)

        board.append(row)

    for x1, y1, x2, y2 in queries:
        x1 -= 1  # 1
        y1 -= 1  # 1
        x2 -= 1  # 4
        y2 -= 1  # 3

        temp = board[x1][y2]
        result = temp

        # 오른쪽 -> 위쪽 -> 왼쪽 -> 아래 -> [x1+1][y2] 값 temp로 바꿔주기
        # 오른쪽, x1 고정
        for i in range(y2, y1, -1):
            result = min(result, board[x1][i - 1])
            board[x1][i] = board[x1][i - 1]

        # 위쪽, y1 고정
        for i in range(x1, x2):  # 4, 3, 2,
            result = min(result, board[i + 1][y1])
            board[i][y1] = board[i + 1][y1]

        # 왼쪽, x2고정
        for i in range(y1, y2):
            result = min(result, board[x2][i + 1])
            board[x2][i] = board[x2][i + 1]

        # 아래, y2고정
        for i in range(x2, x1, -1):
            result = min(result, board[i - 1][y2])
            board[i][y2] = board[i - 1][y2]

        board[x1 + 1][y2] = temp

        answer.append(result)

    return answer


# %%
from collections import deque


def solution(rows, columns, queries):
    arr = [[i + columns * j for i in range(1, columns + 1)] for j in range(rows)]
    answer, result = deque(), []
    for i in queries:
        a, b, c, d = i[0] - 1, i[1] - 1, i[2] - 1, i[3] - 1
        for x in range(d - b):
            answer.append(arr[a][b + x])
        for y in range(c - a):
            answer.append(arr[a + y][d])
        for z in range(d - b):
            answer.append(arr[c][d - z])
        for k in range(c - a):
            answer.append(arr[c - k][b])

        # deque의 rotate로 회전을 한번에 처리
        answer.rotate(1)
        result.append(min(answer))

        for x in range(d - b):
            arr[a][b + x] = answer.popleft()
        for y in range(c - a):
            arr[a + y][d] = answer.popleft()
        for z in range(d - b):
            arr[c][d - z] = answer.popleft()
        for k in range(c - a):
            arr[c - k][b] = answer.popleft()
    return result


rows = 6
columns = 6
queries = [[2, 2, 5, 4], [3, 3, 6, 6], [5, 1, 6, 3]]

solution(rows, columns, queries)
