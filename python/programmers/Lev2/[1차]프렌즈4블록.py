def solution(m, n, board):
    answer = 0
    # string을 list로 변환 -> 2차원 리스트
    board = list(map(list, board))

    # answerSet에 2*2 있을경우 인덱스 tuple 추가 + 인덱스 원소가 0일경우 추가 X / answerSet 반환
    def search():
        answerSet = set()
        for i in range(m - 1):
            for j in range(n - 1):
                if (
                    board[i][j]
                    == board[i + 1][j]
                    == board[i][j + 1]
                    == board[i + 1][j + 1]
                ) and board[i][j] != 0:
                    answerSet.add((i, j))
                    answerSet.add((i + 1, j))
                    answerSet.add((i, j + 1))
                    answerSet.add((i + 1, j + 1))
        return answerSet

    # Search 후 빈칸처리
    def remove(lst):
        for x, y in lst:
            board[x][y] = 0

    # 빈칸 채우기
    def down():
        for i in range(m - 1, -1, -1):
            for j in range(n):
                if board[i][j] == 0:
                    for k in range(i - 1, -1, -1):
                        if board[k][j] != 0:
                            board[i][j] = board[k][j]
                            board[k][j] = 0
                            break

    while True:
        answerSet = search()
        if len(answerSet) == 0:
            break
        answer += len(answerSet)
        remove(answerSet)
        down()

    return answer


# %%
# 행, 열을 바꿔 빈자리를 채우는 것을 자연스럽게 함
def solution(m, n, board):
    answer = 0
    # string을 list로 변환 -> 2차원 리스트(행, 열을 바꿈)
    board = list(map(list, zip(*board)))

    # answerSet에 2*2 있을경우 인덱스 tuple 추가 + 인덱스 원소가 0일경우 추가 X / answerSet 반환
    def search():
        answerSet = set()
        for i in range(n - 1):
            for j in range(m - 1):
                if (
                    board[i][j]
                    == board[i + 1][j]
                    == board[i][j + 1]
                    == board[i + 1][j + 1]
                ) and board[i][j] != 0:
                    answerSet.add((i, j))
                    answerSet.add((i + 1, j))
                    answerSet.add((i, j + 1))
                    answerSet.add((i + 1, j + 1))
        return answerSet

    def remove(lst):
        # 빈칸처리
        for x, y in lst:
            board[x][y] = 0

    def down():
        # 빈자리 채우기
        for i, row in enumerate(board):
            empty = [0] * row.count(0)
            board[i] = empty + [block for block in row if block != 0]

    while True:
        answerSet = search()
        if len(answerSet) == 0:
            break
        answer += len(answerSet)
        remove(answerSet)
        down()

    return answer
