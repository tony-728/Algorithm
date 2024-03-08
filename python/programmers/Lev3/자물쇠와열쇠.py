def rotate90(arr):
    return list(zip(*arr[::-1]))


def solution(key, lock):
    def attach(x, y, key):
        for i in range(M):
            for j in range(M):
                board[x + i][y + j] += key[i][j]

    def detach(x, y, key):
        for i in range(M):
            for j in range(M):
                board[x + i][y + j] -= key[i][j]

    def check():
        for i in range(N):
            for j in range(N):
                if board[M + i][M + j] != 1:
                    return False
        return True

    answer = False

    """
    열쇠를 회전과 이동을 하여 자물쇠에 패턴과 일치하는지 물어보는 문제    
    """
    N = len(lock)
    M = len(key)

    board = [[0] * (M * 2 + N) for _ in range(M * 2 + N)]

    for i in range(N):
        for j in range(N):
            board[M + i][M + j] = lock[i][j]

    rotated_key = key
    for _ in range(4):
        rotated_key = rotate90(rotated_key)
        for i in range(1, M + N):
            for j in range(1, M + N):
                attach(i, j, rotated_key)

                if check():
                    return True

                detach(i, j, rotated_key)

    return answer
