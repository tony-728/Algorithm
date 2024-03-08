def solution(line):
    answer = []

    cross_point = set()
    while line:
        A, B, E = line.pop()

        for C, D, F in line:
            u = A * D - B * C
            if not u == 0:
                x = (B * F - E * D) / u
                y = (E * C - A * F) / u

            if x.is_integer() and y.is_integer():
                cross_point.add((int(x), int(y)))

    x_max = max(cross_point, key=lambda x: x[0])[0]
    x_min = min(cross_point, key=lambda x: x[0])[0]
    y_max = max(cross_point, key=lambda x: x[1])[1]
    y_min = min(cross_point, key=lambda x: x[1])[1]

    for i in range(y_max, y_min - 1, -1):
        temp = ""
        for j in range(x_min, x_max + 1):
            if (j, i) in cross_point:
                temp += "*"
            else:
                temp += "."
        answer.append(temp)

    return answer
