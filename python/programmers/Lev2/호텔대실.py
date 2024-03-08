def change_time(time):
    h, m = map(int, time.split(":"))

    return h * 60 + m


def solution(book_time):
    answer = 1

    book_time.sort()

    room_dict = dict()

    for time in book_time:
        s, e = map(lambda x: change_time(x), time)

        if not room_dict:
            room_dict[answer] = [s, e]

        else:
            for k in room_dict.keys():
                r_s, r_e = room_dict[k]
                if s >= r_e + 10:
                    room_dict[k] = [s, e]
                    break
            else:
                answer += 1
                room_dict[answer] = [s, e]

    return answer
