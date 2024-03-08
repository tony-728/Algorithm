#%%
def solution(arr,divisor):
    answer = []
    for i in arr:
        if i % divisor == 0:
            answer.append(i)
            
    answer = sorted(answer)

    answer = answer if answer else [-1]
    return answer


#%%

arr = [5,9,7,10]
divisor = 5
