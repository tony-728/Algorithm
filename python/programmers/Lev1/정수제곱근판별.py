#%%
def solution(n):
    answer = 0

    sqrt_n = n ** 0.5
    if str(sqrt_n).split('.')[-1] == '0':
        answer = int((sqrt_n + 1) ** 2)
    else:
        answer = -1
        
    return answer

solution(3)
# %%
def solution(n):
    answer = 0

    sqrt_n = n ** 0.5
    if sqrt_n % 1 == 0: # 1로 나눴을 때 나머지가 없으면 정수이다.
        answer = int((sqrt_n + 1) ** 2)
    else:
        answer = -1
        
    return answer

solution(4)