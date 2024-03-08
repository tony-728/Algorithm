def dfs(index, lastX, lastY, distance):
    global answer
    if answer < distance:
        return

    # 모든경로 탐색이 완료되었다면
    if index == n:
        # 거리를 계산한다.
        distance += abs(lastX - house[0]) + abs(lastY - house[1])
        # 제일짧은거리 리턴
        answer = min(distance, answer)
        return

    # 경로를 하나씩 추가한다.
    for i in range(n):
        if not check[i]:
            check[i] = True

            temp = (
                distance
                + abs(lastX - position_list[i][0])
                + abs(lastY - position_list[i][1])
            )

            dfs(index + 1, position_list[i][0], position_list[i][1], temp)
            # 돌아오면 사용체크 해제
            check[i] = False


T = int(input())
for t in range(1, T + 1):
    answer = 1e9
    n = int(input())

    position_list = []

    x = input().split()

    for i in range(0, (n + 2) * 2, 2):
        position_list.append([int(x[i]), int(x[i + 1])])

    company = position_list.pop(0)  # 회사
    house = position_list.pop(0)  # 집

    check = [False for _ in range(n)]

    # 경로찾기 시작
    dfs(0, company[0], company[1], 0)

    print("#{} {}".format(t, answer))


# %%
from itertools import permutations

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    answer = 1e9
    n = int(input())

    coordinate = []

    x = input().split()

    for i in range(0, (n + 2) * 2, 2):
        coordinate.append([int(x[i]), int(x[i + 1])])

    company = coordinate.pop(0)  # 회사
    house = coordinate.pop(0)  # 집

    for p in permutations(coordinate):
        start = company
        distance = 0
        for x, y in p:
            distance += abs(start[0] - x) + abs(start[1] - y)
            start = [x, y]
            if distance > answer:
                break
        else:
            distance += abs(house[0] - start[0]) + abs(house[1] - start[1])
            answer = min(answer, distance)

    print(f"#{test_case} {answer}")
