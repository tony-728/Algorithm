# %%
from itertools import permutations


# 소수인지 확인
def is_prime(n):
    if n == 0 or n == 1:
        return False
    else:
        for i in range(2, int(n**0.5) + 1):
            if n % i == 0:
                return False
        else:
            return True


def solution(numbers):
    answer = 0

    number_list = list(numbers)
    prime_number = []

    for i in range(1, len(numbers) + 1):
        # 숫자 문자열로 만들 수 있는 1부터 문자열 길이 만큼의 조합을 만든다.
        c = permutations(number_list, i)
        for i in set(c):  # 중복을 제외하고 확인
            number = "".join(i)
            number = int(number)

            # 만든 숫자가 소수인지 판별하고 이미 확인한 소수인지 확인한다.
            if number not in prime_number and is_prime(number):
                answer += 1
                prime_number.append(number)

    return answer
