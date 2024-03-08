from collections import deque


def solution(maps):
    answer = []

    h = len(maps)  # y
    w = len(maps[0])  # x

    visited = [[False for _ in range(w)] for _ in range(h)]

    # 2x2 방문배열을 만든다
    # 0,0 위치부터 시작해서 상하좌우로 움직이면서 연결되어있는 섬을 찾는다.
    # 연결되어 있는 섬이 있으면 queue에 넣는다.
    # queue에 원소가 존재하는 동안 반복한다.
    # 이때 확인한 위치는 방문처리를 한다.
    # queue가 끝났는데 방문처리하지 않는 섬이 있다면 확인한다.

    def check_island(i, j):
        result = int(maps[i][j])
        visited[i][j] = True

        queue = deque()

        move = [[-1, 0], [1, 0], [0, 1], [0, -1]]

        queue.append((i, j))

        while queue:
            y, x = queue.popleft()
            # 상하좌우
            for m in move:
                new_x = x + m[0]
                new_y = y + m[1]
                if new_x > -1 and new_x < w and new_y > -1 and new_y < h:
                    if maps[new_y][new_x].isdigit() and visited[new_y][new_x] == False:
                        result += int(maps[new_y][new_x])
                        queue.append((new_y, new_x))
                        visited[new_y][new_x] = True

        return result

    for i in range(h):
        for j in range(w):
            if visited[i][j] == False:
                if maps[i][j].isdigit():
                    answer.append(check_island(i, j))
                else:
                    visited[i][j] = True

    if not answer:
        answer.append(-1)

    answer.sort()

    return answer
