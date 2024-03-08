# %%
def solution(strings, n):
    answer = []

    answer = sorted(strings, key=lambda x: x[1])
    return answer


# %%
strings = ["sun", "bed", "car"]
n = 1
# strings = ['abce','abcd','cdx']
# n = 2

sorted(sorted(strings), key=lambda x: x[n])
