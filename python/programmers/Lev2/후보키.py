from itertools import combinations


def solution(relation):
    answer = 0

    col = [i for i in range(len(relation[0]))]
    candidate = []

    for i in range(1, len(relation[0]) + 1):
        # 조합만들기 1~컬럼 수
        combi = combinations(col, i)

        for c in combi:
            # 후보키의 유일성 확인
            for candi in candidate:
                if set(candi).issubset(set(c)):
                    break
            else:
                check = []
                # 후보키의 최소성 확인
                for r in relation:
                    t = [r[i] for i in c]

                    if t in check:
                        break
                    else:
                        check.append(t)
                else:
                    answer += 1
                    candidate.append(c)

    return answer
