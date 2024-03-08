def check(s):
    if s == s[::-1]:
        return True
    else:
        return False


def solution(s):
    answer = 0

    for i in range(len(s)):
        for j in range(len(s), i - 1, -1):
            if check(s[i:j]):
                answer = max(answer, j - i)

    return answer
