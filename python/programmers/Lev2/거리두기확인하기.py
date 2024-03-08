def check(place, i, j):
    move = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, 1), (-1, -1), (1, -1), (1, 1)]

    for x, y in move:
        new_i = i + x
        new_j = j + y

        if new_i >= 0 and new_i < 5 and new_j >= 0 and new_j < 5:
            if place[new_i][new_j] == "P":
                if x == 0 or y == 0:
                    return False

                elif place[i + x][j] == "O" or place[i][j + y] == "O":
                    return False

            if (x == 0 or y == 0) and place[new_i][new_j] == "O":
                new_i_2 = i + x * 2
                new_j_2 = j + y * 2

                if new_i_2 >= 0 and new_i_2 < 5 and new_j_2 >= 0 and new_j_2 < 5:
                    if place[new_i_2][new_j_2] == "P":
                        return False
    else:
        return True


def solution(places):
    answer = []

    """
    p가 있는 위치를 찾고 
    p가 있는 위치에서 맨헤튼 거리만큼에서 다른 p가 있는지 파악 
    - p를 기준으로 1칸으로 (상하좌우, 대각)이동할 수 있는 위치에 있으면 안됨
    - p를 기준으로 상하좌우로 2칸 이동할 수 있는 위치에 있으면 안됨
    이때 파티션으로 가려져서 이동할 수 없으면 통과 아니면 탈락
    """

    for place in places:
        flag = False
        for i in range(5):
            for j in range(5):
                if place[i][j] == "P":
                    if check(place, i, j) == False:
                        flag = True

                if flag:
                    break

            if flag:
                answer.append(0)
                break
        else:
            answer.append(1)

    return answer
