# %%
def solution(arr):
    answer = []
    sorted_arr = sorted(arr, reverse=True)
    low_number = sorted_arr.pop()
    arr.pop(arr.index(low_number))
    return arr if arr else [-1]


solution([4, 3, 2, 1])


# %%
def solution(arr):
    min_num = min(arr)

    answer = [i for i in arr if i > min_num]

    return answer if answer else [-1]


solution([4, 3, 2, 1, 1, 2, 3])
