T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    tc = input().replace("()", "L")

    answer = 0
    stick = 0
    total_stick = 0
    laser = 0

    for e in tc:
        if e == ")":
            stick -= 1

        elif e == "(":
            stick += 1
            total_stick += 1

        elif e == "L":
            laser += stick

    print(f"#{test_case} {laser + total_stick}")
# %%
# 오답

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    tc = input().replace("()", "L")

    answer = 0
    stick = 0
    laser = 0
    local_laser = 0
    local = False

    for e in tc:
        if e == ")":
            stick -= 1
            if local:
                answer += local_laser + 1
                laser += local_laser
                local_laser = 0
                local = False
            else:
                laser += local_laser
                answer += laser + 1
                local_laser = 0

        elif e == "(":
            stick += 1
            local = True
            if local_laser:
                laser += local_laser
                local_laser = 0

        elif e == "L":
            if stick:
                local_laser += 1

    print(f"#{test_case} {answer}")
