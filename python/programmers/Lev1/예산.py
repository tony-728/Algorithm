#%%
def solution(d, budget):
    answer = 0

    d = sorted(d)
    for i in d:
        if budget >= i:
            budget -= i
            answer += 1

    return answer

solution([1,3,2,5,4], 9)