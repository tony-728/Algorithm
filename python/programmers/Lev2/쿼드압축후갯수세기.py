def solution(arr):
    result = [0, 0]
    length = len(arr)

    def compression(a, b, l):
        start = arr[a][b]  # 해당 영역에 값이 같은지 확인하기 위한 기준

        for i in range(a, a + l):
            for j in range(b, b + l):
                if arr[i][j] != start:  # 영역에서 다른 값이 등장하면
                    l = l // 2  # 확인할 길이를 2로 나누고 쿼드로 나누어서 다시 확인
                    compression(a, b, l)
                    compression(a, b + l, l)
                    compression(a + l, b, l)
                    compression(a + l, b + l, l)
                    return

        # 확인하는 영역에 값이 같을 때 해당 값(인덱스)의 갯수 증가
        result[start] += 1

    compression(0, 0, length)

    return result


arr = [[1, 1, 0, 0], [1, 0, 0, 0], [1, 0, 0, 1], [1, 1, 1, 1]]
solution(arr)
