# %%
def solution(s):
    answer = 0
    first = s[0]
    first_check = False

    same = 0
    nosame = 0

    for i in s:
        if first_check:
            first = i
            first_check = False

        if first == i:
            same += 1
        else:
            nosame += 1

        if same == nosame:
            answer += 1
            same = 0
            nosame = 0
            first_check = True

    if same:
        answer += 1

    return answer


s = "abracadabra"
solution(s)
