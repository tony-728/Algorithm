# %%
from heapq import heappush, heappop


def solution(n, k, enemy):
    answer = 0

    if k >= len(enemy):
        answer = len(enemy)

    else:
        heap = []

        for e in enemy:
            # 지금 턴에 무적권을 쓸 수있는 것을 배제하고 생각한거임
            if n >= e:
                n -= e
                heappush(heap, -e)
            else:
                if k > 0:
                    n -= heappop(heap)
                    n -= e
                    heappush(heap, -e)
                    k -= 1
                else:
                    break
            answer += 1

    return answer


# %%
from heapq import heappush, heappop


def solution(n, k, enemy):
    answer = 0

    if k >= len(enemy):
        answer = len(enemy)

    else:
        heap = []
        for e in enemy:
            n -= e

            if n >= 0:
                heappush(heap, -e)
            else:
                if k > 0:
                    heappush(heap, -e)
                    n -= heappop(heap)
                    k -= 1
                else:
                    break

            answer += 1

    return answer


# %%
from heapq import heappush, heappop


def solution(n, k, enemy):
    h = []
    for i, e in enumerate(enemy):
        heappush(h, e)
        if len(h) > k:
            n -= heappop(h)
        if n < 0:
            return i

    return len(enemy)
