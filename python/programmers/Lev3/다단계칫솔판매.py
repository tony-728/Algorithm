def solution(enroll, referral, seller, amount):
    answer = {x: 0 for x in enroll}

    graph = {c: p for c, p in zip(enroll, referral)}

    X = 100

    for s, a in zip(seller, amount):
        a *= X
        ten = int(a * 0.1)
        ninety = a - ten

        answer[s] += ninety

        while graph[s] != "-":
            s = graph[s]

            ninety = ten

            ten = int(ten * 0.1)
            ninety -= ten

            if ten < 1:
                answer[s] += ninety + ten
                break
            else:
                answer[s] += ninety

    answer = list(answer.values())
    return answer
