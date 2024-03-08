def solution(n, money):
    answer = 0

    dp = [1] + [0] * n

    for m in sorted(money):
        for c in range(m, n + 1):
            # 현재 거스름 돈은 현재 거스름 돈 + (현재 거스름 돈-사용하고자하는 돈)
            dp[c] = (dp[c] + dp[c - m]) % 1000000007

    return dp[n]
