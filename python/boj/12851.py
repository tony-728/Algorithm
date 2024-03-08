from collections import deque

N, K = map(int, input().split())
MAX_SIZE = 100001

q = deque()
q.append(N)

count = 0
check = [-1] * MAX_SIZE
check[N] = 0

while q:
    x = q.popleft()

    if x == K:  # 확인하는 x가 K와 같을 때 count 증가
        count += 1

    for next in [x * 2, x + 1, x - 1]:  # 다음을 확인
        if 0 <= next < MAX_SIZE:
            # 처음 방문하는 경우
            if check[next] == -1 or check[next] >= check[x] + 1:
                check[next] = check[x] + 1  # 이전 시간(x)에 1을 더해서 시간을 계산
                q.append(next)

print(check[K])
print(count)
