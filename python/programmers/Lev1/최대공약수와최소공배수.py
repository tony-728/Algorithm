# %%
def check_div(x):
    div_list = []
    for i in range(1, (x // 2) + 1):
        if x % i == 0:
            div_list.append(i)
    else:
        div_list.append(x)

    return div_list


def solution(n, m):
    answer = []
    min_num = min(n, m)
    max_num = max(n, m)

    n_div = check_div(min_num)
    m_div = check_div(max_num)

    # 최대공약수
    max_div = max(set(n_div).intersection(set(m_div)))
    answer.append(max_div)

    # 최소공배수
    # answer.append(max_div * (min_num // max_div) * (max_num // max_div))
    answer.append(min_num * max_num // max_div)

    return answer


solution(3, 4)

# %%
n, m = 24, 36

print(n, m)
answer = []
min_num = min(n, m)
max_num = max(n, m)


def check_div(x):
    div_list = []
    for i in range(1, (x // 2) + 1):
        if x % i == 0:
            div_list.append(i)
    else:
        div_list.append(x)

    return div_list


n_div = check_div(min_num)
m_div = check_div(max_num)

print(n_div)
print(m_div)

# 최대공약수
answer.append(max(set(n_div).intersection(set(m_div))))

if n in m_div:  # 최소공배수는 m
    answer.append(max_num)
else:
    answer.append(min_num * max_num)


print(answer)

# %%
23 * 44
