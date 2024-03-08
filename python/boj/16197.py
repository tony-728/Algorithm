import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())

matrix = [list(input().rstrip()) for _ in range(N)]

# 동전 위치 찾기
coin_loc = []

coin = deque()

for row in range(N):
    for col in range(M):
        if matrix[row][col] == "o":
            coin_loc.append([row, col])

coin.append((coin_loc[0][0], coin_loc[0][1], coin_loc[1][0], coin_loc[1][1], 0))

# 움직이는 방향, 상하좌우
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

"""
상하좌우로 움직이는 것을 확인

벽이면 움직이지 못함

둘 다 떨어지면 안됨

10번보다 많이 눌러야 한다면 안됨
"""


def bfs(coin, matrix, n, m):
    while coin:
        x1, y1, x2, y2, cnt = coin.popleft()

        # 10번보다 많이 누르면 아웃
        if cnt >= 10:
            return -1

        for i in range(4):
            nx1 = x1 + dx[i]
            ny1 = y1 + dy[i]
            nx2 = x2 + dx[i]
            ny2 = y2 + dy[i]

            if 0 <= nx1 < n and 0 <= ny1 < m and 0 <= nx2 < n and 0 <= ny2 < m:
                # 벽이라면 이동할 수 없음
                if matrix[nx1][ny1] == "#":
                    nx1, ny1 = x1, y1
                if matrix[nx2][ny2] == "#":
                    nx2, ny2 = x2, y2
                coin.append((nx1, ny1, nx2, ny2, cnt + 1))
            elif 0 <= nx1 < n and 0 <= ny1 < m:  # coin2가 떨어진 경우
                return cnt + 1
            elif 0 <= nx2 < n and 0 <= ny2 < m:  # coin1가 떨어진 경우
                return cnt + 1
            else:  # 둘 다 빠진 경우 무시
                continue

    return -1


print(bfs(coin, matrix, N, M))
