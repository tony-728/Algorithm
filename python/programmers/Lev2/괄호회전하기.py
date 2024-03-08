from collections import deque


def checking(S):
    temp = deque()

    for i in range(len(S)):
        s = S[i]

        if not temp and (s == ")" or s == "]" or s == "}"):
            return False

        if s == "(" or s == "{" or s == "[":
            temp.append(s)

        else:
            if s == ")":
                if temp[-1] == "(":
                    temp.pop()
                else:
                    return False

            elif s == "}":
                if temp[-1] == "{":
                    temp.pop()
                else:
                    return False

            elif s == "]":
                if temp[-1] == "[":
                    temp.pop()
                else:
                    return False

    return True


def solution(s):
    answer = 0

    s = deque(s)

    if checking(s):
        answer += 1

    # 0-s 만큼 왼쪽으로 회전하면서 찾아야 한다.
    # 한번 올바른 괄호가 되면 다음 이동때에는 절대 올바른 괄호가 될 수 없다.

    for _ in range(1, len(s)):
        a = s.popleft()
        s.append(a)

        print(s, answer)

        if checking(s):
            answer += 1

    return answer


# s = "[(])"
s = "}]()[{"
solution(s)
