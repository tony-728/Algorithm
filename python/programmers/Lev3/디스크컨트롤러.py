from heapq import heappush, heappop, heapify
from collections import deque


def solution(jobs):
    answer = 0
    start = 0

    jobs.sort(key=lambda x: x[0])

    len_jobs = len(jobs)

    access_time, play_time = jobs[0]
    answer += abs(start - access_time) + play_time

    start += play_time

    jobs = deque(jobs[1:])

    while jobs:
        # 우선순위가 높은 것은 플레이타임이 짧고 종료된 시간으로부터 요청 시간이 짧은 것
        #         access_time = jobs[0][0]

        #         if access_time >= start:
        #             access_time, play_time = jobs.popleft()
        #             answer += abs(start - access_time) + play_time
        #             start += play_time
        #         else:
        heap = []

        for i in range(len(jobs)):
            temp = [start - access_time, access_time, play_time]
            heappush(heap, temp)

        item = heappop(heap)
        _, access_time, play_time = item
        answer += abs(start - access_time) + play_time
        start += play_time
        jobs = heap

    answer //= len_jobs

    return answer


# %%
from heapq import heappush, heappop


def solution(jobs):
    answer = 0

    now = 0

    jobs.sort(key=lambda x: x[0])
    len_jobs = len(jobs)

    # 현재를 기준으로 가장 대기 시간이 짧은 작업먼저 실행한다.

    while jobs:
        heap = []
        for i in range(len(jobs)):
            access_time, play_time = jobs[i]
            temp = [abs(now - access_time), i, access_time, play_time]
            heappush(heap, temp)

        wait_time, i, access_time, play_time = heappop(heap)

        print(access_time, play_time)

        if now - access_time >= 0:
            answer += wait_time + play_time
            now += play_time
        else:
            answer += play_time
            now += abs(wait_time) + play_time

        del jobs[i]

    answer //= len_jobs

    return answer


# %%
import heapq
from collections import deque


def solution(jobs):
    # 순서를 (수행시간, 요청시간)으로 변경하는 이유는 수행시간이 짧은 것을 기준으로 힙을 만들 때 튜플의 앞이 기준이 되기 때문
    # (요청시간, 수행시간) 기준으로 정렬하는 이유는 이 순서가 요청순서에 맞는 정렬이고 수행시간 또한 짧은 순서대로 정렬시키기 때문
    # 여기서 정렬하기 때문에 이후에는 큐에 앞부분부터 확인하면 된다.
    tasks = deque(sorted([(x[1], x[0]) for x in jobs], key=lambda x: (x[1], x[0])))
    q = []

    heapq.heappush(q, tasks.popleft())
    current_time, total_response_time = 0, 0

    while q:
        play_time, access_time = heapq.heappop(q)
        # 현재 시간을 갱신할 때
        # 현재 시간+수행시간, 요청시간+수행시간 중 최대값으로 처리하는 이유는 현재시간보다 요청시간이 작을 때 요청시간까지 이동하는 시간을 고려하지 않기 때문
        current_time = max(current_time + play_time, access_time + play_time)
        total_response_time += current_time - access_time

        # 작업이 끝나는 시점에서 요청시간이 작은 작업은 고려해야하기 때문에 힙에 추가
        while tasks and tasks[0][1] <= current_time:
            heapq.heappush(q, tasks.popleft())

        # 작업이 남았는데 힙이 비어 있지 않도록 하기 위함
        if tasks and not q:
            heapq.heappush(q, tasks.popleft())

    return total_response_time // len(jobs)
