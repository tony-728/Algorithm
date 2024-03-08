def row_check(sudoku):
    row_check = False

    for r in sudoku:
        if sum(r) == 45:
            row_check = True
        else:
            row_check = False
            break

    return row_check


def column_check(sudoku):
    column_check = False

    temp = list(map(list, zip(*sudoku)))

    for r in temp:
        if sum(r) == 45:
            column_check = True
        else:
            column_check = False
            break

    return column_check


def part_check(sudoku):
    part_check = False
    temp = 0

    for i in range(3):
        for j in range(3):
            for x in range(i * 3, i * 3 + 3):
                for y in range(j * 3, j * 3 + 3):
                    temp += sudoku[x][y]

            if not temp == 45:
                return part_check

            temp = 0

    else:
        part_check = True
        return part_check


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    answer = 0
    sudoku = []

    # row, column 확인 3x3 확인
    for _ in range(9):
        row = list(map(int, input().split()))
        sudoku.append(row)

    if not row_check(sudoku):
        print(f"#{test_case} {answer}")
        continue

    if not column_check(sudoku):
        print(f"#{test_case} {answer}")
        continue

    if not part_check(sudoku):
        print(f"#{test_case} {answer}")
        continue

    answer = 1
    print(f"#{test_case} {answer}")
