# %%
def solution(survey, choices):
    answer = ""

    indicator_dict = {"R": 0, "T": 0, "C": 0, "F": 0, "J": 0, "M": 0, "A": 0, "N": 0}
    indicator = ["RT", "CF", "JM", "AN"]
    score = {1: 3, 2: 2, 3: 1, 4: 0, 5: 1, 6: 2, 7: 3}

    for s, c in zip(survey, choices):
        if c < 4:
            indicator_dict[s[0]] += score[c]
        elif c > 4:
            indicator_dict[s[1]] += score[c]

    for inid in indicator:
        answer += (
            inid[0] if indicator_dict[inid[0]] >= indicator_dict[inid[1]] else inid[1]
        )

    return answer
