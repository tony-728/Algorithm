from collections import defaultdict


def solution(user_id, banned_id):
    answer = []

    banned_id_dict = defaultdict(set)

    visited = dict()

    # 불량 사용자 목록에 매핑되는 아이디 찾기
    for user in user_id:
        for banned in banned_id:
            if len(user) == len(banned):
                for i in range(len(user)):
                    # 서로 다르면 멈춤, 추가 조건은 banned[i]가 *이 아니여야함
                    if user[i] != banned[i] and banned[i] != "*":
                        break
                else:
                    banned_id_dict[banned].add(user)
                    visited[user] = False

    index = 0
    result = list()

    def dfs(index, result: list):
        if index == len(banned_id):
            # 순서가 상관없기 때문에 set으로 변경후에 확인하여 최종목록에 넣음
            x = set(result)
            if not x in answer:
                answer.append(x)

        else:
            for id in banned_id_dict[banned_id[index]]:
                if visited[id] == False:
                    result.append(id)
                    visited[id] = True
                    index += 1

                    dfs(index, result)

                    result.pop()
                    visited[id] = False
                    index -= 1

    dfs(index, result)

    return len(answer)


user_id = ["frodo", "fradi", "crodo", "abc123", "frodoc"]
banned_id = ["fr*d*", "*rodo", "******", "******"]

solution(user_id, banned_id)
