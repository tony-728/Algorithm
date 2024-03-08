def solution(s: str):
    answer = []
    s = s.replace("{", "[").replace("}", "]")
    s = eval(s)
    s = sorted(s, key=lambda x: len(x))

    for i in s:
        while i:
            temp = i.pop()

            if temp not in answer:
                answer.append(temp)

    return answer
