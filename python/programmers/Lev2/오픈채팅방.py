def solution(record):
    answer = []

    # 필요한 것은 들어오고 나간 순서
    # 유저 아이디
    # 유저 아이디가 최종적으로 사용하는 닉네임

    status = {"Enter": "님이 들어왔습니다.", "Leave": "님이 나갔습니다."}
    user_dict = {}

    for i in record:
        if "Enter" in i:
            stat, uid, nickname = i.split(" ")
            answer.append((uid, status[stat]))
            user_dict[uid] = nickname

        elif "Leave" in i:
            stat, uid = i.split(" ")
            answer.append((uid, status[stat]))

        elif "Change" in i:
            stat, uid, nickname = i.split(" ")
            user_dict[uid] = nickname

    for i in range(len(answer)):
        answer[i] = user_dict[answer[i][0]] + answer[i][1]
    return answer
