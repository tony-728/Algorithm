# %%
def solution(today, terms, privacies):
    answer = []
    today_y, today_m, today_d = map(int, today.split("."))

    terms_dict = {term.split(" ")[0]: int(term.split(" ")[1]) for term in terms}

    for index, privacy in enumerate(privacies, 1):
        term, p_type = privacy.split(" ")
        term_y, term_m, term_d = map(int, term.split("."))

        if terms_dict[p_type] >= 12:
            add_year = terms_dict[p_type] // 12
            term_y += add_year

        add_month = terms_dict[p_type] % 12

        if term_m + add_month <= 12:
            term_m = term_m + add_month
        else:
            term_m = (term_m + add_month) % 12
            term_y += 1

        if term_d - 1 == 0:
            term_d = 28
            if term_m - 1 == 0:
                term_m = 12
                term_y -= 1
            else:
                term_m -= 1
        else:
            term_d -= 1

        if term_y < today_y:
            answer.append(index)
        elif term_y == today_y:
            if term_m < today_m:
                answer.append(index)
            elif term_m == today_m and term_d < today_d:
                answer.append(index)

    return answer


# today = "2022.05.19"
# terms = ["A 6", "B 12", "C 3"]  # 달
# privacies = ["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]

today = "2020.01.01"
terms = ["Z 3", "D 5"]  # 달
privacies = [
    "2019.01.01 D",
    "2019.11.15 Z",
    "2019.08.02 D",
    "2019.07.01 D",
    "2018.12.28 Z",
]


solution(today, terms, privacies)


# %%
def to_days(date):
    year, month, day = map(int, date.split("."))
    return year * 28 * 12 + month * 28 + day


def solution(today, terms, privacies):
    months = {v[0]: int(v[2:]) * 28 for v in terms}
    today = to_days(today)
    expire = [
        i + 1
        for i, privacy in enumerate(privacies)
        if to_days(privacy[:-2]) + months[privacy[-1]] <= today
    ]
    return expire
