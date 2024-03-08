from collections import deque


def solution(skill, skill_trees):
    # skill: 선행 스킬 순서
    # skill_tree: 확인할 스킬트리들
    answer = 0

    len_skill = len(skill)

    for tree in skill_trees:
        check = 0
        tree = deque(tree)
        while tree:
            t = tree.popleft()

            if check < len_skill and skill[check] == t:
                check += 1
            elif skill[check] != t and t in skill:
                break

            if check == len_skill:
                answer += 1
                break
        else:
            answer += 1

    return answer
