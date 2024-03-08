def rotate(matrix):
    result = list(map(list, zip(*matrix[::-1])))

    return result


T = int(input())
for test_case in range(1, T + 1):
    N = int(input())

    matrix = []
    result = []

    for _ in range(N):
        row = list(map(str, input().split()))
        matrix.append(row)

    for _ in range(3):
        matrix = rotate(matrix)
        result.append(matrix)

    print(f"#{test_case}")
    for i in range(N):
        for j in range(3):
            print("".join(result[j][i]), end=" ")
        print()
