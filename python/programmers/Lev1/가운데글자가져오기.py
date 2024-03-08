#%%
def solution(s):
    answer=''
    len_s = len(s)

    if len_s%2: #odd
        answer = s[len_s//2]
    else:# even
        answer = s[len_s//2-1:len_s//2+1]

    return answer
