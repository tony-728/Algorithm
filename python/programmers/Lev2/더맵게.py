# 최소힙으로 만들어준다
import heapq


# 힙 자료구조를 잘 기억하자
# 최소값 혹은 최대값이 계속 필요한 곳에서 유용하게 사용할 수 있을 듯
def solution(scoville, K):
    answer = 0

    if min(scoville) >= K:
        return answer

    else:
        heapq.heapify(scoville)

        while True:
            if len(scoville) > 1:
                lower_1 = heapq.heappop(scoville)

                if lower_1 >= K:
                    break

                lower_2 = heapq.heappop(scoville)

                new = lower_1 + lower_2 * 2
                heapq.heappush(scoville, new)
                answer += 1

            else:
                if min(scoville) < K:
                    answer = -1
                break

        return answer
