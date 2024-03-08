#%%
def solution(n):
    answer = 0

    n_minus1 = n-1

    for i in range(2, n_minus1//2+1):
        if n_minus1 % i==0:
            return i
    else:
        return n_minus1
    