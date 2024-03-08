from collections import defaultdict
import math


def convert(time):
    h, m = map(int, time.split(":"))

    return h * 60 + m


def solution(fees, records):
    answer = []

    cars_time = defaultdict(int)
    cars_check = defaultdict(int)

    basic_time, basic_fee, interval_time, interval_fee = fees

    for record in records:
        time, num, stat = record.split(" ")
        if stat == "IN":
            cars_check[num] = convert(time)
        elif stat == "OUT":
            cars_time[num] += convert(time) - cars_check[num]
            cars_check.pop(num)
    else:
        for key in cars_check.keys():
            cars_time[key] += 23 * 60 + 59 - cars_check[key]

    for key in sorted(cars_time.keys()):
        if cars_time[key] <= basic_time:
            total_fee = basic_fee
        else:
            total_fee = (
                math.ceil((cars_time[key] - basic_time) / interval_time) * interval_fee
                + basic_fee
            )

        answer.append(total_fee)
    return answer
