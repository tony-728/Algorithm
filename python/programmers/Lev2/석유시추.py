from collections import deque


def solution(land):
    def bfs(y: int, x: int, oil: int) -> tuple:
        """
        연결되어 있는 석유량 확인

        Args:
            y: 세로
            x: 가로
            oil: 초기 석유량

        Returns:
            oil: 총 연결되어 있는 석유량
            move_right: 연결되어 있는 석유의 가로 인덱스
        """
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        q = deque()
        q.append((y, x))

        move_right = set()

        while q:
            y, x = q.popleft()

            for i in range(4):
                new_x = x + dx[i]
                new_y = y + dy[i]

                if -1 < new_y < n and -1 < new_x < m:
                    if not visited[new_y][new_x]:
                        visited[new_y][new_x] = True

                        if land[new_y][new_x]:
                            oil += 1
                            q.append((new_y, new_x))
                            move_right.add(new_x)

        return oil, move_right

    n = len(land)
    m = len(land[0])

    answer = 0
    result = [0 for _ in range(m)]

    visited = [[False for _ in range(m)] for _ in range(n)]

    for x in range(m):  # 가로
        for y in range(n):  # 세로
            if not visited[y][x]:
                visited[y][x] = True

                # 석유가 있으면 bfs
                if land[y][x]:
                    oil, move_right = bfs(y, x, 1)
                    result[x] += oil

                    # 가로로 연결되어 있는 만큼 추가
                    for mr in move_right:
                        if mr != x:
                            result[mr] += oil

    answer = max(result)
    return answer
