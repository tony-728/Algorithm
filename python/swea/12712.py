def check(x, y, check):
    temp = maps[x][y]

    if check:  # +
        dx = [0, 0, -1, 1]
        dy = [-1, 1, 0, 0]
    else:  # x
        dx = [-1, -1, 1, 1]
        dy = [-1, 1, -1, 1]

    for i in range(1, M):
        for j in range(4):
            new_x = x + dx[j] * i
            new_y = y + dy[j] * i
            if -1 < new_x < N and -1 < new_y < N:
                temp += maps[new_x][new_y]

    return temp


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    answer = 0

    N, M = map(int, input().split())

    maps = []

    for _ in range(N):
        row = list(map(int, input().split()))

        maps.append(row)

    for x in range(N):
        for y in range(N):
            answer = max(answer, check(x, y, True))

            answer = max(answer, check(x, y, False))

    print(f"#{test_case} {answer}")
