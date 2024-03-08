# 부분합

"""
N: 수열의 길이
S: 수열의 연속된 수들의 부분합 중에 그 합이 S이상이 되어야 한다.

다음 줄에 수열이 공백으로 입력된다.
조건을 만족하는 것 연속된 수들 중에 가장 짧은 길이를 입력한다.
만약 S를 만들 수 없다면 0을 출력한다.
"""


"""
두개의 포인터를 두고 S이상이 되는 가장 짧은 부분합을 구한다.
"""
import sys

N, S = map(int, input().split())

num_list = list(map(int, sys.stdin.readline().split()))

answer = N + 1

start = 0
end = 0

sum_value = 0

for i in range(N):
    sum_value += num_list[i]
    end += 1  # 현재 값을 더해줄때마다 다음 index를 포인팅

    if sum_value >= S:
        while sum_value >= S:
            # S이상의 부분합이 가장 최소일 때를 찾는다.
            # start index 부터 빼면서, start는 다음 index를 포인팅
            sum_value -= num_list[start]
            start += 1

        # S이상의 가장 짧은 부분합을 구했을 때 길이

        # 빼는 과정에서 start가 다음 index를 포인팅하기 때문에 1을 더해준다.
        answer = min(answer, end - start + 1)

if answer > N:
    print(0)
else:
    print(answer)
