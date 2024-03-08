def check_prime(n, k):
    n = int(n)
    if n == 1:
        return False
    else:
        for i in range(2, int(n**0.5) + 1):
            if n % i == 0:
                return False

        else:
            return True


def solution(n, k):
    answer = 0

    prime_number = []

    while n >= 1:
        a = n % k
        prime_number.append(a)
        n = n // k

    num = ""
    while prime_number:
        if prime_number[-1] == 0:
            if num and check_prime(num, k):
                answer += 1

            num = ""
            prime_number.pop()

        else:
            num += str(prime_number.pop())
    else:
        if num and check_prime(num, k):
            answer += 1

    return answer
