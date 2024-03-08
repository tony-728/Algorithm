from collections import Counter


def solution(picks, minerals):
    answer = 0

    # mineral을 5개씩 묶어서 정렬한 후에

    total_loop = sum(picks)

    new_minerals = []

    for i in range(total_loop):
        minerals_count = Counter(minerals[i * 5 : i * 5 + 5])

        if not minerals_count:
            break

        new_minerals.append(minerals_count)

    new_minerals.sort(key=lambda x: (x["diamond"], x["iron"], x["stone"]), reverse=True)

    picks = picks[::-1]
    pick = picks.pop()
    p = 0

    kinds_of_mineral = {"diamond": 0, "iron": 1, "stone": 2}

    # pick dia, iron, stone 순서
    for mineral in new_minerals:
        while not pick:
            if picks:
                pick = picks.pop()
                p += 1
            else:
                break

        for m in ["diamond", "iron", "stone"]:
            if mineral[m]:
                if p <= kinds_of_mineral[m]:
                    answer += 1 * mineral[m]
                else:
                    answer += (5 ** (p - kinds_of_mineral[m])) * mineral[m]

        pick -= 1

    return answer
