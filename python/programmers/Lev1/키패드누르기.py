# %%
class tuple:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __sub__(self, num):
        return abs(self.x - num.x), abs(self.y - num.y)


def solution(numbers, hand):
    answer = ""

    hand = "L" if hand == "left" else "R"

    hand_location = {
        1: tuple(1, 1),
        2: tuple(1, 2),
        3: tuple(1, 3),
        4: tuple(2, 1),
        5: tuple(2, 2),
        6: tuple(2, 3),
        7: tuple(3, 1),
        8: tuple(3, 2),
        9: tuple(3, 3),
        0: tuple(4, 2),
    }

    left = tuple(4, 1)
    right = tuple(4, 3)

    for number in numbers:
        if number in [1, 4, 7]:
            answer += "L"
            left = hand_location[number]
        elif number in [3, 6, 9]:
            answer += "R"
            right = hand_location[number]
        else:
            nl = sum(hand_location[number] - left)
            nr = sum(hand_location[number] - right)

            if nl < nr:
                answer += "L"
                left = hand_location[number]
            elif nl > nr:
                answer += "R"
                right = hand_location[number]
            else:
                answer += hand
                if hand == "L":
                    left = hand_location[number]
                else:
                    right = hand_location[number]

    return answer


# numbers = [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]
# hand = "right"
numbers = [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]
hand = "left"


solution(numbers, hand)
