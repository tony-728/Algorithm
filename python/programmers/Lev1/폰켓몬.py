#%%
def solution(nums):
    answer = 0
    
    target_num = len(nums) // 2

    set_nums = len(set(nums))
    
    print(target_num)
    print(set_nums)

    answer = min(target_num, set_nums)


    print(answer)

    return answer


# nums = [3,1,2,3]
# nums = [3,3,3,2,2,4]
nums = [3,3,3,2,2,2]
solution(nums)


