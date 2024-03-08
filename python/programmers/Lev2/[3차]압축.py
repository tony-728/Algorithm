from collections import deque


def solution(msg):
    answer = []

    index = 26
    word_dict = {chr(i): i - 64 for i in range(65, 91)}

    msg = deque(msg)

    check = ""

    while msg:
        a = msg[0]

        check += a

        if check in word_dict.keys():
            msg.popleft()
        else:
            index += 1
            word_dict[check] = index

            answer.append(word_dict[check[:-1]])

            check = ""
    else:
        answer.append(word_dict[check])

    return answer


# msg = "KAKAO"
msg = "TOBEORNOTTOBEORTOBEORNOT"

print(solution(msg))
