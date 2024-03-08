from collections import defaultdict


def solution(str1, str2):
    answer = 0

    str1 = str1.lower()
    str2 = str2.lower()

    str1_dict = defaultdict(int)
    str2_dict = defaultdict(int)

    for i in range(len(str1) - 1):
        if 97 > ord(str1[i]) or ord(str1[i]) > 122:
            continue
        if 97 > ord(str1[i + 1]) or ord(str1[i + 1]) > 122:
            continue

        new1 = str1[i] + str1[i + 1]
        if new1 in str1_dict.keys():
            str1_dict[new1] += 1
        else:
            str1_dict[new1] = 1

    for i in range(len(str2) - 1):
        if 97 > ord(str2[i]) or ord(str2[i]) > 122:
            continue
        if 97 > ord(str2[i + 1]) or ord(str2[i + 1]) > 122:
            continue

        new2 = str2[i] + str2[i + 1]
        if new2 in str2_dict.keys():
            str2_dict[new2] += 1
        else:
            str2_dict[new2] = 1

    if len(str2_dict) == 0:
        answer = 65536

    else:
        inner = 0
        outer = 0
        total_keys = list(set(list(str1_dict.keys()) + list(str2_dict.keys())))

        for key in total_keys:
            if key in str1_dict.keys() and key in str2_dict.keys():
                # defaultdict으로 했기 때문에 없는 key 참조시 0으로 반환하여 에러가 나지 않음
                inner += min(str1_dict[key], str2_dict[key])

            if key in str1_dict.keys() or key in str2_dict.keys():
                outer += max(str1_dict[key], str2_dict[key])

        answer = int(inner / outer * 65536)

    return answer


str1 = "FRANCE"
str2 = "french"

print(solution(str1, str2))

# %%
import re
import math


def solution(str1, str2):
    str1 = [
        str1[i : i + 2].lower()
        for i in range(0, len(str1) - 1)
        if not re.findall("[^a-zA-Z]+", str1[i : i + 2])
    ]
    str2 = [
        str2[i : i + 2].lower()
        for i in range(0, len(str2) - 1)
        if not re.findall("[^a-zA-Z]+", str2[i : i + 2])
    ]

    gyo = set(str1) & set(str2)
    hap = set(str1) | set(str2)

    if len(hap) == 0:
        return 65536

    gyo_sum = sum([min(str1.count(gg), str2.count(gg)) for gg in gyo])
    hap_sum = sum([max(str1.count(hh), str2.count(hh)) for hh in hap])

    return math.floor((gyo_sum / hap_sum) * 65536)
