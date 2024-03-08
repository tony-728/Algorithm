# %%
def solution(s):
    answer = ""

    s = s.split(" ")

    answer = " ".join(list(map(lambda s: s.capitalize(), s)))
    return answer


s = "3people unFollowed me"


# %%
s = "asdfa adjkf"

s.capitalize()
s.title()
