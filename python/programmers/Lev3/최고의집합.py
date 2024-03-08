def solution(n, s):
    answer = []

    if n > s:
        answer = [-1]
    else:
        if s % n:  # 나눠떨어지지 않음
            x = s // n
            diff = s % n
            answer = [x] * n
            for i in range(diff):
                answer[i % n] += 1

        else:  # 나눠떨어짐
            x = s // n
            answer = [x] * n

    answer.sort()
    return answer
