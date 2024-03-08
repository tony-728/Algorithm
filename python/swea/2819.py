def dfs(start, visited, count, number):
    if count == 7:
        answer.add(number)
        return

    temp = count + 1
    number += number_list[start[0]][start[1]]

    for i in range(4):
        new_x = start[0] + dx[i]
        new_y = start[1] + dy[i]
        if 0 <= new_x <= 3 and 0 <= new_y <= 3:
            visited[new_x][new_y] = True
            dfs([new_x, new_y], visited, temp, number)
            visited[new_x][new_y] = False


visited = [[False for _ in range(4)] for _ in range(4)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    number_list = list()  # adf
    answer = set()

    for i in range(4):
        x = input().split()
        number_list.append(x)

    for i in range(4):
        for j in range(4):
            start = [i, j]
            count = 0
            number = number_list[i][j]

            visited[i][j] = True
            dfs(start, visited, count, number)
            visited[i][j] = False

    print(f"#{test_case} {len(answer)}")
