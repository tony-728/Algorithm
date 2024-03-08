# %%
def solution(number, limit, power):
    def check_div(number):
        count = 0

        for i in range(1, int(number**0.5) + 1):
            if number % i == 0:
                count += 1
                if (i**2) != number:
                    count += 1

        return count

    answer = 0

    number_list = [i + 1 for i in range(number)]
    answer = sum(map(lambda x: power if x > limit else x, map(check_div, number_list)))

    return answer


solution(5, 3, 2)
