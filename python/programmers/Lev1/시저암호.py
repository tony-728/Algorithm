# %%
def solution(s, n):
    answer = ""
    upper_alpha = list("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
    lower_alpha = list("abcdefghijklmnopqrstuvwxyz")

    answer = ""
    for i in s:
        if i.isupper():
            index = upper_alpha.index(i)
            if index + n > 25:
                index = (index + n) % 26
            else:
                index = index + n

            answer += upper_alpha[index]
        elif i.islower():
            index = lower_alpha.index(i)
            index = (index + n) % 26
            answer += lower_alpha[index]
        elif i == " ":
            answer += " "
    return answer


# %%
def solution(s, n):
    answer = ""
    alpha = list("ABCDEFGHIJKLMNOPQRSTUVWXYZ")

    answer = ""
    for i in s:
        upper_check = False
        if i.isupper():
            upper_check = True
        elif i == " ":
            answer += " "
            continue

        index = alpha.index(i.upper())
        index = (index + n) % 26

        answer += alpha[index] if upper_check else alpha[index].lower()

    return answer


solution("a B z", 4)
