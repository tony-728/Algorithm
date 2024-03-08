# %%
import itertools


def solution(nums):
    def check_prime(x):
        for i in range(2, int(x**0.5) + 1):
            if x % i == 0:
                return False  # 소수가 아님
        else:
            return True

    sum_list = list(map(lambda x: sum(x), list(itertools.combinations(nums, 3))))
    answer = len([i for i in sum_list if check_prime(i)])

    return answer
