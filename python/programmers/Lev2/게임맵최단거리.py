from collections import deque


def solution(maps):
    # 최단거리를 구해야하므로 BFS
    # 큐로 구현할 수 있다.
    target_y, target_x = len(maps) - 1, len(maps[0]) - 1

    queue = deque()
    queue.append((0, 0))

    dx = [-1, 0, 1, 0]
    dy = [0, -1, 0, 1]

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            new_x = x + dx[i]
            new_y = y + dy[i]

            if new_x < 0 or new_y < 0 or new_x > target_x or new_y > target_y:
                continue

            if maps[new_y][new_x] == 1:
                maps[new_y][new_x] = maps[y][x] + 1

                if new_x == target_x and new_y == target_y:
                    return maps[-1][-1]

                queue.append((new_x, new_y))

    return -1


maps = [
    [1, 0, 1, 1, 1],
    [1, 0, 1, 0, 1],
    [1, 0, 1, 1, 1],
    [1, 1, 1, 0, 1],
    [0, 0, 0, 0, 1],
]
a = solution(maps)

print(a)
