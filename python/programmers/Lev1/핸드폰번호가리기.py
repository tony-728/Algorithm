# %%
def solution(phone_number):
    answer = ""
    len_number = len(phone_number)

    last_number = phone_number[-4:]

    answer = "*" * (len_number - 4) + last_number

    return answer
