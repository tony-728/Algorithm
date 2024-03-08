# from itertools import permutations
# from collections import deque


# def calculate(l, r, o):
#     if o == "+":
#         return l + r
#     elif o == "-":
#         return l - r
#     elif o == "*":
#         return l * r


# def solution(expression):
#     answer = 0

#     operators = []

#     queue = deque()
#     stack = deque()

#     if "*" in expression:
#         operators.append("*")
#     if "-" in expression:
#         operators.append("-")
#     if "+" in expression:
#         operators.append("+")

#     temp = ""
#     for x in expression:
#         if x.isdigit():
#             temp += x
#         else:
#             queue.append(int(temp))
#             queue.append(x)

#             temp = ""
#     else:
#         queue.append(int(temp))

#     expression = queue.copy()

#     for oper in permutations(operators):
#         queue = expression.copy()
#         for o in oper:
#             while queue:
#                 val = queue.popleft()
#                 if isinstance(val, int):
#                     stack.append(val)
#                 else:
#                     if val == o:
#                         left = stack.pop()
#                         right = queue.popleft()
#                         result = calculate(left, right, val)
#                         stack.append(result)
#                     else:
#                         stack.append(val)

#             queue = stack.copy()
#             stack.clear()

#         answer = max(answer, abs(queue.pop()))

#     return answer


def solution(expression):
    operations = [
        ("+", "-", "*"),
        ("+", "*", "-"),
        ("-", "+", "*"),
        ("-", "*", "+"),
        ("*", "+", "-"),
        ("*", "-", "+"),
    ]
    answer = []
    for op in operations:
        a = op[0]
        b = op[1]
        temp_list = []
        for e in expression.split(a):
            temp = [f"({i})" for i in e.split(b)]
            temp_list.append(f"({b.join(temp)})")
        answer.append(abs(eval(a.join(temp_list))))
    return max(answer)


expression = "100-200*300-500+20"
solution(expression)
