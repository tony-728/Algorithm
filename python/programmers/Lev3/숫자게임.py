from heapq import heappop, heapify


def solution(A, B):
    answer = 0

    # 어차피 순서가 공개되었으니까 정렬한 후에 진행해도 상관없지 않나?
    # B팀은 그거에 맞춰서 선수를 내보내니까

    heapify(A)
    heapify(B)

    a = heappop(A)
    b = heappop(B)

    while B:
        if a < b:
            answer += 1
            a = heappop(A)
            b = heappop(B)
        else:
            b = heappop(B)
    else:
        if a < b:
            answer += 1

    return answer


# %%
def solution(A, B):
    answer = 0
    A.sort()
    B.sort()
    j = 0

    for i in range(len(A)):
        if A[j] < B[i]:
            answer = answer + 1
            j = j + 1

    return answer
