# %%
import math


def array_gcd(array):
    # 배열의 최대공약수 구하기
    if len(array) > 1:
        array_gcd = math.gcd(array[0], array[1])
        for d in divisors(array_gcd):
            for a in array[2:]:
                if a % d:
                    break
            else:
                array_gcd = d
                break
    else:
        array_gcd = array[0]

    return array_gcd


def divisors(gcd):
    # 약수 구하기
    result = []
    if gcd > 1:
        for i in range(1, int(gcd**0.5) + 1):
            if gcd % i == 0:
                result.append(i)

                if gcd != i**2:
                    result.append(gcd // i)

        result.sort(reverse=True)

    return result


def solution(arrayA, arrayB):
    answer = 0

    # arrayA, arrayB를 정렬한 후에 앞에서부터 최대공약수를 찾아서 확인해야 함
    arrayA.sort()
    arrayB.sort()

    arrayA_gcd = array_gcd(arrayA)
    arrayB_gcd = array_gcd(arrayB)

    for b in arrayB:
        if b % arrayA_gcd == 0:
            break
    else:
        answer = arrayA_gcd

    for a in arrayA:
        if a % arrayB_gcd == 0:
            break
    else:
        answer = max(answer, arrayB_gcd)

    return answer


# %%
from functools import reduce
from math import gcd


def solution(nums1, nums2):
    gcd1, gcd2 = reduce(gcd, nums1), reduce(gcd, nums2)
    answer = []

    # all: 이 리스트가 모두 1이상 이면 즉, 나눠떨어지지 않으면
    if all(each % gcd2 for each in nums1):
        answer.append(gcd2)
    if all(each % gcd1 for each in nums2):
        answer.append(gcd1)
    return max(answer) if answer else 0
