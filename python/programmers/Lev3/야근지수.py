from heapq import heappush, heappop, heapify


def solution(n, works):
    answer = 0

    works = list(map(lambda x: -x, works))

    heapify(works)

    while n > 0:
        x = heappop(works)

        if x < 0:
            heappush(works, x + 1)
            n -= 1

        else:
            heappush(works, x)
            n -= 1

    answer = sum(map(lambda x: x**2, works))

    return answer
