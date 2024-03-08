# %%
def solution(s):
    answer = True

    if len(s) == 4 or len(s) == 6:
        answer = True if s.isdigit() else False
    
    else:
        answer = False
    return answer


# %%
s = ''

answer = True
for i in s:
    if i.isdigit():
        continue
    else:
        answer = False
        break

answer