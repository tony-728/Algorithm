def solution(files):
    answer = []

    # HEAD의 사전순서
    # number의 순서
    # 입력된 순서가 일치해야 한다.

    for file in files:
        head = ""
        number = ""
        tail = ""
        number_check = False
        for i in range(len(file)):
            # 문제 조건에 number는 1부터 5자리까지만 온다고 했
            if file[i].isdigit() and len(number) < 5:
                number += file[i]
                number_check = True
            elif number_check == False:
                head += file[i]
            else:
                tail = file[i:]
                break

        answer.append((head, number, tail))

    answer.sort(key=lambda x: (x[0].lower(), int(x[1])))

    print(answer)

    return ["".join(a) for a in answer]
