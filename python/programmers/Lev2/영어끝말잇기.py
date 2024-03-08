# %%
def solution(n, words):
    player = {i: 0 for i in range(n)}
    answer = []
    check = []

    last_word = ""

    for idx, word in enumerate(words):
        if word in check:  # 같은 단어를 말한 경우
            answer = [(idx % n) + 1, player[idx % n] + 1]
            break
        elif last_word and word[0] != last_word:  # 끝말잇기가 안되는 경우
            answer = [(idx % n) + 1, player[idx % n] + 1]
            break
        else:
            player[idx % n] += 1
            check.append(word)
            last_word = word[-1]

    else:
        answer = [0, 0]

    return answer


n = 3
words = ["tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"]


solution(n, words)

# %%
