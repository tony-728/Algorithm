def solution(scores):
    answer = 0

    wanho = scores[0]

    check = []

    # 두 점수의 합계로 정렬하여 완호의 랭킹을 확인한다.
    scores = sorted(scores, key=lambda x: (x[0] + x[1]), reverse=True)

    key = scores[0][0] + scores[0][1]
    max_key = key

    rank = 1

    for a, b in scores:
        # 완호의 점수가 정렬된 점수들부터 확인하면서 모두 작은지 확인
        # 작은 적이 한번이라도 존재하면 -1
        if wanho[0] < a and wanho[1] < b:
            answer = -1
            break

        yes = True

        # 최대 합보다 작을 때
        # 두 점수가 모두 작으면 카운팅하지 않음
        if max_key > a + b:
            for value in check:
                if a < value[0] and b < value[1]:
                    yes = False
                    break

        if yes:
            if [a, b] == wanho:
                answer = rank
                break

            rank += 1  # 중복된 만큼 석차가 밀리므로 1을 더해준다.

            # 카운팅에 필요한 점수 추가
            if not check:
                check.append([a, b])
            else:
                for c in check:
                    if c[0] < a or c[1] < b:
                        check.append([a, b])

    return answer


# %%
def solution(scores):
    wanho = scores[0]
    wanho_sum = sum(wanho)

    # 근무태도 점수를 기준으로 내림차순
    scores.sort(key=lambda s: (-s[0], s[1]))
    max_company, answer = 0, 1

    for s in scores:
        # 완호의 점수가 모두 작으면 -1
        if wanho[0] < s[0] and wanho[1] < s[1]:
            return -1

        # 확인하는 값은 동료평가점수
        # 왜냐하면 이미 근무태도 점수는 정렬로 지난 값보다 작거나 같다는 전제
        # 따라서 동료평가(두번째점수)가 지난 값보다 크거나 같으면 통과된다.
        if max_company <= s[1]:
            # 완호의 점수보다 확인하는 점수가 크면 완호의 석차가 밀림
            if wanho_sum < s[0] + s[1]:
                answer += 1

            # 동료평가점수 갱신
            # 위에 if문에서 s[1]이 max_company보다 크거나 같음을 확인했으므로 다른 조건없이 갱신할 수 있다.
            max_company = s[1]

    return answer


scores = [[3, 1], [1, 4], [2, 3], [2, 3], [1, 5], [1, 0], [1, 0]]

print(solution(scores))
