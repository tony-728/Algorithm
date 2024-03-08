# %%
def solution(n):
    answer = []

    hanoi(n, 1, 2, 3, answer)

    return answer


def hanoi(n, start, sub, end, answer):
    if n == 1:
        answer.append([start, end])

    else:
        # 1번기둥에 있는 n개의 원판 중 n-1개를 2번 기둥으로 욺긴다. 이 때 3번 기둥으로 거친다.
        hanoi(n - 1, start, end, sub, answer)
        answer.append([start, end])
        # 2번 기둥에 있는 n-1개의 원판을 다시 3번 기둥으로 옮긴다. 1번 기둥을 거쳐감
        hanoi(n - 1, sub, start, end, answer)


n = 3

solution(n)
