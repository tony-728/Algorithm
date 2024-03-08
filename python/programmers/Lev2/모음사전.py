# %%
from itertools import product


def solution(word):
    answer = 0

    words = []

    for i in range(1, 6):
        for p in product(["A", "E", "I", "O", "U"], repeat=i):
            words.append("".join(list(p)))

    words.sort()
    answer = words.index(word) + 1
    return answer


# %%
def dfs(x, words, alpha):
    words.append(x)

    for i in alpha:
        if len(x) != len(alpha):
            dfs(x + i, words, alpha)


def solution(word):
    alpha = ["A", "E", "I", "O", "U"]
    words = []

    for i in range(len(alpha)):
        dfs(alpha[i], words, alpha)

    return words.index(word) + 1


solution("I")
