# %%
from itertools import permutations


def solution(k, dungeons):
    answer = -1

    # 던전을 이동하는 모든 경우의 수를 구한 다음 대입한다.
    # 제한 사항에서 던전의 최대갈이가 8이므로 이때 경우의 수가 약 4만개여서 가능한 방법이다.
    # 던전의 길이가 조금 더 길었으면 시간 초과가 나지 않았을까?
    p = permutations([i for i in range(len(dungeons))])

    for i in p:
        temp = k
        r = 0
        for j in i:
            if temp >= dungeons[j][0]:
                temp -= dungeons[j][1]
                r += 1
        else:
            answer = max(answer, r)

    return answer


# %%

# DFS를 이용한 풀이
answer = 0


def solution(k, dungeons):
    global answer

    def DFS(k, count, dungeons, visited):
        global answer
        if count > answer:
            answer = count

        for i in range(N):
            if k >= dungeons[i][0] and not visited[i]:
                visited[i] = True
                DFS(k - dungeons[i][1], count + 1, dungeons, visited)
                visited[i] = False

                if answer == N:
                    break

    N = len(dungeons)
    visited = [False] * N
    DFS(k, 0, dungeons, visited)
    return answer


k = 80
dungeons = [[80, 20], [50, 40], [30, 10]]

solution(k, dungeons)
