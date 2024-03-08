def list_add(x, y):
    a = x[0] + y[0]
    b = x[1] + y[1]

    return (a, b)


def solution(dirs):
    answer = 0

    move = {"U": (0, 1), "D": (0, -1), "L": (-1, 0), "R": (1, 0)}
    reverse_move = {"U": "D", "L": "R", "D": "U", "R": "L"}

    roc = (0, 0)

    path = []

    for dir in dirs:
        temp = list_add(roc, move[dir])

        if temp[0] < -5 or temp[0] > 5 or temp[1] < -5 or temp[1] > 5:
            continue

        check = "".join(map(str, roc + move[dir]))
        roc = temp

        if check not in path:
            path.append(check)
            path.append("".join(map(str, roc + move[reverse_move[dir]])))
            answer += 1

    return answer
