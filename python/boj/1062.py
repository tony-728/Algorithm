import sys
import re
from itertools import combinations


def myreplace(x):
    return re.sub(r"[antic]", "", x)


def count_words(word_list, char_list):
    count = 0
    for char_set in word_list:
        for c in char_set:
            if not char_list[ord(c) - ord("a")]:
                break
        else:
            count += 1

    return count


input = sys.stdin.readline

N, K = map(int, input().split())
word_list = [input().rstrip() for _ in range(N)]

answer = 0

word_list = list(map(myreplace, word_list))


t = len(set("".join(word_list)))

if K < 5:
    print(answer)

# 조합하는 수보다 조합 개수가 크면 만들 수 없다.
# 미리 확인
elif t < K - 5 or K == 26:
    print(N)

else:
    combi_char = list(combinations(set("".join(word_list)), K - 5))
    word_list = list(map(set, word_list))
    for combi in combi_char:
        char_list = [False] * 26
        for learn_char in combi:
            char_list[ord(learn_char) - ord("a")] = True

        count = count_words(word_list=word_list, char_list=char_list)
        answer = max(answer, count)

    print(answer)
