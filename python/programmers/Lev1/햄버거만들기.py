# %%
def solution(ingredient):
    answer = 0
    hambuger = []

    for element in ingredient:
        hambuger.append(element)
        if hambuger == [1, 2, 3, 1]:
            hambuger.clear()
            answer += 1
        elif hambuger[-4:] == [1, 2, 3, 1]:
            del hambuger[-4:]
            answer += 1

    return answer
