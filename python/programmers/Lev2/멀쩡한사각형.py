# %%
import math


def solution(w, h):
    answer = 1

    if w == h:
        answer = w * h - w
    else:
        gradient = h / w
        count = 0
        gcd = math.gcd(w, h)
        if h == 1 or w == 1:
            answer = 0
        else:
            temp = 0
            for i in range(1, (w // gcd) + 1):
                x = math.ceil(gradient * i)
                count += x - temp + 1
                temp = x

            else:
                count -= 1

            answer = (w * h) - (count * gcd)
    return answer


# %%
from math import gcd


def solution(w, h):
    return w * h - (w + h - gcd(w, h))
