from collections import deque


def check(p):
    queue = deque()
    p = deque(p)

    while p:
        temp = p.popleft()
        if temp == "(":
            queue.append(temp)
        elif temp == ")":
            if queue and queue[-1] == "(":
                queue.pop()
            else:
                break
    else:
        return True

    return False


def algo(p):
    answer = ""
    p = deque(p)
    u = ""
    v = ""

    l_count = 0
    r_count = 0

    while p:
        x = p.popleft()

        if x == "(":
            l_count += 1
        else:
            r_count += 1

        u += x

        if l_count == r_count:
            if p:
                v = "".join(p)
            else:
                v = ""
            break

    if check(u):
        answer += u
        if v:
            answer += algo(v)

    else:
        if len(u) == 2:
            u = ""
        else:
            u = u[1:-1]

        temp = "("

        u = u.replace("(", "]").replace(")", "[")
        u = u.replace("]", ")").replace("[", "(")

        temp += algo(v) + ")" + u

        answer += temp

    return answer


def solution(p):
    answer = ""

    if check(p):
        answer = p

    else:
        answer = algo(p)

    return answer


p = "()))((()"
solution(p)
