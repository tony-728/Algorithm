# %%
def solution(board):
    answer = 0

    for r_index, row in enumerate(board):
        for index, i in enumerate(row):
            if r_index == 0:
                if 1 in row and not answer:
                    answer = 1
            elif index == 0:
                continue
            elif i:
                board[r_index][index] = (
                    min(
                        board[r_index - 1][index - 1],  # 좌상
                        board[r_index - 1][index],  # 상
                        board[r_index][index - 1],  # 좌
                    )
                    + 1
                )
                if board[r_index][index] > answer:
                    answer = board[r_index][index]

    answer = answer**2
    return answer


"""
DP 문제

board를 하나씩 확인하면서 길이를 찾게 되면 3중 for문을 사용할 수 밖에 없으므로 시간 복잡도가 O(N^3)이 된다.

어떻게 DP로 문제를 풀 수 있을까

현재 위치가 1인 경우에 좌, 상, 좌상위치에 값중 최소값에 1을 더한 값이 현재 위치에서 가질 수 있는 최대 길이가 된다.
현재 위치가 0인 경우에는 어떠한 정사각형도 만들 수 없으므로 0이 된다.

첫번째 행에서는 가장 크게 만들 수 있는 정사각형의 넓이가 1이므로 좌, 좌상, 상을 확인하는 작업이 필요없다.
하지만 1이 첫번째 행에 존재할 때에는 answer를 1로 해주어야 한다. 

"""

board = [[0]]
solution(board)
