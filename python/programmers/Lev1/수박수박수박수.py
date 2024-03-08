#%%
def solution(n):
    answer = ''

    if n % 2: # odd
        if n == 1:
            answer = '수'
        else:
            answer = '수박' * (n//2) + '수'
        pass
    else: # even
        answer = '수박' * (n//2)

    return answer

solution(100)
#%%
