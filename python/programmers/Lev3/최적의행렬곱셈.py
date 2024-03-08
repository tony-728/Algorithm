def solution(matrix_sizes):
    answer = 0
    L = len(matrix_sizes)
    dp = [[0 for _ in range(L)] for _ in range(L)]

    for gap in range(1, L):  # gap는 곱할 두 행렬간의 간격
        for start in range(L - gap):  # start는 행렬곱의 시작 행렬
            a = start
            b = start + gap
            dp[a][b] = 1e9

            for k in range(a, b):
                middle_product = (
                    matrix_sizes[a][0] * matrix_sizes[k][1] * matrix_sizes[b][1]
                )
                dp[a][b] = min(dp[a][b], dp[a][k] + middle_product + dp[k + 1][b])
    return dp[0][-1]


# matrix_sizes = [[5, 3], [3, 10], [10, 6]]
matrix_sizes = [[10, 4], [4, 5], [5, 20], [20, 2], [2, 50]]

print(solution(matrix_sizes))
