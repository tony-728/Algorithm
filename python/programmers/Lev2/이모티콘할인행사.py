from itertools import product


def solution(users, emoticons):
    answer = [0, 0]

    e_len = len(emoticons)

    p = product([10, 20, 30, 40], repeat=e_len)

    for pro in p:
        count = 0
        amount = 0
        for discount, target_price in users:
            price = 0
            for i, emo in enumerate(emoticons):
                if pro[i] >= discount:
                    price += int(emo * (100 - pro[i]) * 0.01)

            if price >= target_price:
                count += 1
            else:
                amount += price

            answer = max(answer, [count, amount])

    return answer
