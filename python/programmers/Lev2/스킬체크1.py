def solution(n):
    answer = 0

    val = 0
    for i in range(n, 0, -1):
        val = i

        if val == n:
            answer += 1
        else:
            for j in range(i - 1, 0, -1):
                val += j
                if val == n:
                    answer += 1
                elif val > n:
                    break

    return answer


n = 15
print(solution(n))

"""
1부터 주어진 N까지 수 중에 연속된 숫자로 N을 만들 수 있는 갯수
ex) N = 15
1 ~ 15 중 연속된 숫자의 조합으로 15를 만들 수 있는 경우의 수
1. 1 2 3 4 5
2. 4 5 6
3. 7 8
4. 15
"""
