# %%
def solution(answers):
    answer = []
    person = {1: 0, 2: 0, 3: 0}

    first_answer = [5, 1, 2, 3, 4]
    second_answer = [5, 1, 3, 4]
    thrid_answer = [5, 3, 3, 1, 1, 2, 2, 4, 4, 5]

    for i, a in enumerate(answers, 1):
        if first_answer[i % 5] == a:
            person[1] += 1

        if i % 2 and a == 2:  # 홀수
            person[2] += 1
        elif i % 2 == 0 and second_answer[(i % 8) // 2] == a:
            person[2] += 1

        if thrid_answer[i % 10] == a:
            person[3] += 1

    max_score = max(person.values())
    for i in person:
        if person[i] == max_score:
            answer.append(i)

    return answer


solution([1, 3, 2, 4, 2])

# %%

a = [1, 2, 2]

a.index(2)
