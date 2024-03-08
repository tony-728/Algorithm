from math import gcd


def lcm(a, b):
    return int(a * b / gcd(a, b))


def solution(n, cores):
    answer = 1
    len_cores = len(cores)

    if n <= len_cores:
        answer = n

    else:
        c_lcm = lcm(cores[0], cores[1])

        if len_cores > 2:
            for core in cores[2:]:
                c_lcm = lcm(c_lcm, core)

        total_job = sum([c_lcm // i for i in cores])

        time = 0

        while total_job <= n:
            n %= total_job

        while n > 0:
            for i in range(len_cores):
                if time % cores[i] == 0:
                    n -= 1

                if n == 0:
                    answer = i + 1
                    break

            time += 1

    return answer


# %%
def solution(n, cores):
    # 작업 시작시에는 모든 코어에 일을 할당하므로
    # 할 작업량이 core 수 보다 작으면 n번째 코어가 마지막 일을 담당
    if n <= len(cores):
        return n
    else:
        # 작업 시작시에는 모든 코어에 일을 할당하므로 작업량에서 코어의 수롤 뺌
        n -= len(cores)

        # 이분탐색을 위한 준비
        left = 1
        # 남은 작업량에 가장 오래 걸리는 코어를 곱함
        # 왜냐하면 가장 오래 걸리는 코어는 해당 시간만큼이 필요하기 때문
        right = max(cores) * n

        # 이분탐색 시작
        while left < right:
            mid = (left + right) // 2  # 중간값(시간)

            # 코어들이 중간값까지 할 수 있는 작업량 확인
            capacity = 0
            for c in cores:
                capacity += mid // c

            # 중간값까지 할 수 있는 양이 실제 양보다 많을 경우 right를 mid로 변경
            # 아닌 경우 left를 한칸 이동
            if capacity >= n:
                right = mid
            else:
                left = mid + 1

        # while을 빠져나왔으면
        # right 시간에 마지막으로 일하는 core가 있다는 것을 알게됨
        # right-1 시간동안 할 수 있는 작업을 한 후 남음 작업에서 마지막으로 일하는 core를 찾으면 됨
        for c in cores:
            n -= (right - 1) // c

        # 마지막으로 일하는 코어찾기
        for i in range(len(cores)):
            if right % cores[i] == 0:
                n -= 1
                if n == 0:
                    return i + 1


n = 6
cores = [1, 2, 3]

# n = 50000
# cores = [1, 2, 3, 34343, 223, 3223, 4443]

print(solution(n, cores))
