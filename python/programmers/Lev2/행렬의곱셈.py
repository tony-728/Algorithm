# %%
def solution(arr1, arr2):
    answer = []
    arr_r = len(arr1)
    arr_c = len(arr1[0])
    c = len(arr2[0])

    for i in range(arr_r):
        row = arr1[i]
        r_temp = []

        for j in range(c):
            temp = 0

            for k in range(arr_c):
                temp += row[k] * arr2[k][j]
            r_temp.append(temp)
        answer.append(r_temp)

    return answer


# arr1 = [[1, 4], [3, 2], [4, 1]]

# arr2 = [[3, 3], [3, 3]]

arr1 = [[2, 3, 2], [4, 2, 4], [3, 1, 4]]

arr2 = [[5, 4, 3], [2, 4, 1], [3, 1, 1]]

# arr1 = [[2, 3, 2], [4, 2, 4]]

# arr2 = [[5, 4], [2, 4], [3, 1]]


solution(arr1, arr2)


# %%
def solution(A, B):
    return [
        [sum(a * b for a, b in zip(A_row, B_col)) for B_col in zip(*B)] for A_row in A
    ]
