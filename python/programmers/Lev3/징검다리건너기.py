def solution(stones, k):
    answer = 0

    # 최소값을 1로 할 수 있다. 왜냐하면 문제에서 주어진 최소값이 1이기 때문
    start = min(stones)  # 최소로 건널 수 있는 사람수
    end = max(stones)  # 최대로 건널 수 있는 사람수

    # 이분탐색
    while start <= end:
        mid = (start + end) // 2
        count = 0

        for stone in stones:
            # 문제 조건
            if stone - mid <= 0:
                count += 1
                # 연속으로 건널 수 없는 값이 k만큼일 때 end 갱신
                if count >= k:
                    end = mid - 1
                    break
            else:
                count = 0

        else:
            start = mid + 1

    else:
        answer = start

    return answer


stones = [2, 4, 5, 3, 2, 1, 4, 2, 5, 1]
k = 3

solution(stones, k)
