#%%
def solution(a,b):
    answer= 0

    if a==b:
        answer = a
    else:
        answer = sum(range(min(a,b),max(a,b)+1))
    return answer

#%%
a=5
b=3

sum([i for i in range(a,b+1)])