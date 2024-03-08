# %%
from collections import Counter


def solution(s):
    answer = True
    s = s.lower()
    count_s = Counter(s)

    if count_s["p"] == count_s["y"]:
        answer = True
    else:
        answer = False
    return answer


# %%
from collections import Counter

# s = 'pPoooyY'
s = "abcde"
s = s.lower()

count_s = Counter(s)

if count_s["p"] == count_s["y"]:
    answer = True
else:
    answer = False

print(answer)

# %%
s = "abcdep"
s = s.lower()


if s.count("p") == s.count("y"):
    answer = True
else:
    answer = False

print(answer)
