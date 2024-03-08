# %%
def solution(arr1, arr2):
    answer = []
    for i, j in zip(arr1, arr2):
        low = []

        for x, y in zip(i, j):
            low.append(x + y)

        answer.append(low)

    return answer


solution([[1, 2], [2, 3]], [[3, 4], [5, 6]])


# %%
def sumMatrix(A, B):
    answer = [[c + d for c, d in zip(a, b)] for a, b in zip(A, B)]
    return answer


# 아래는 테스트로 출력해 보기 위한 코드입니다.
print(sumMatrix([[1, 2], [2, 3]], [[3, 4], [5, 6]]))
