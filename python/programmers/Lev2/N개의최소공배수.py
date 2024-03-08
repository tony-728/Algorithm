# %%
def div(n):
    r = []
    for i in range(1, int(n**0.5) + 1):
        if n % i == 0:
            r.append(i)
            if i**2 != n:
                r.append(n // i)

    return sorted(r)


def solution(arr):
    answer = 1
    x = []

    answer = arr[0]

    for a in arr[1:]:
        answer = answer * a // max((set(div(answer)).intersection(set(div(a)))))

    return answer


# arr = [2, 6, 8, 14]
# arr = [1,2,3]
arr = [12, 32, 45, 67, 72]


solution(arr)


# %%
def solution(arr):
    from math import gcd  # 최대공약수를 구하는 gcd() import

    answer = arr[0]  # answer을 arr[0]으로 초기화

    for num in arr:  # 반복문을 처음부터 끝까지 돈다.
        # 1. (arr[0],arr[1])의 최소공배수를 구한 후 answer에 저장
        # 2. (#1에서 구한 최소공배수, arr[2])의 최소공배수를 구한 후 answer에 저장
        # 3. 모든 배열을 돌면서 최소공배수를 구하고, 저장하고 하는 방식을 진행
        answer = answer * num // gcd(answer, num)

    return answer


# arr = [2, 6, 8, 14]
# arr = [1,2,3]
arr = [12, 32, 45, 67, 72]

solution(arr)

# %%
