# %%
def solution(n):
    def binary(n):
        binary_n = []
        while n:
            binary_n.append(n % 2)
            n = n // 2

        return binary_n[::-1]

    if n == 1:
        return 2
    elif n == 2:
        return 4
    elif not (n & (n - 1)):  # 2의 제곱수 판별
        return n * 2
    elif (n - 1) % 4 == 0:
        return n + 1
    else:
        binary_n = binary(n)
        one_count = binary_n.count(1)

        while True:
            n += 1
            b_n = binary(n)
            if one_count == b_n.count(1):
                break

        return n


n = 27

solution(n)


# %%
def solution(n):
    num1 = bin(n).count("1")
    while True:
        n = n + 1
        if num1 == bin(n).count("1"):
            break
    return n
