from collections import deque


def solution(m, n, puddles):
    answer = 0

    dp = [[0] * m for _ in range(n)]

    dp[0][0] = 1

    for puddle in puddles:
        dp[puddle[1] - 1][puddle[0] - 1] = "P"

    dx = [1, 0]
    dy = [0, 1]

    q = deque()
    q.append((0, 0))

    while q:
        now = q.popleft()

        for i in range(2):
            new_x = now[0] + dx[i]
            new_y = now[1] + dy[i]

            if new_x < n and new_y < m:
                if dp[new_x][new_y] == "P":
                    continue

                else:
                    dp[new_x][new_y] += dp[now[0]][now[1]]
                    if not (new_x, new_y) in q:
                        q.append((new_x, new_y))

    answer = dp[-1][-1] % 1000000007
    return answer


# %%
def solution(m, n, puddles):
    dp = [[0] * (m + 1) for i in range(n + 1)]
    dp[1][1] = 1

    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if i == 1 and j == 1:
                continue
            if [j, i] in puddles:
                dp[i][j] = 0
            else:
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007
    return dp[n][m]
