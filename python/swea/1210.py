T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())

    # 100 x 100
    ladder = []
    start_x, start_y = 0, 0
    for i in range(100):
        row = input().split()
        if i == 99:
            start_y = row.index("2")
            start_x = i

        ladder.append(row)

    dy = [-1, 1]
    move = False

    while start_x != 0:
        # 올라간 후에 좌우 살피기
        start_x -= 1

        # 좌우 살피기
        for i in range(2):
            new_y = start_y + dy[i]
            if 0 <= new_y <= 99:
                if ladder[start_x][new_y] == "1":
                    start_y = new_y
                    move = True
                    break

        # 좌우로 이동했으면 이동할 수 있는 만큼 이동하기기
        while move:
            if 0 <= start_y + dy[i] <= 99 and ladder[start_x][start_y + dy[i]] == "1":
                start_y += dy[i]
            else:
                move = False
                break

    print(f"#{test_case} {start_y}")
