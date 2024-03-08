# %%
def solution(sizes):
    answer = 0
    new_sizes = list(map(lambda size: [max(size), min(size)], sizes))

    width, height = [i[0] for i in new_sizes], [i[1] for i in new_sizes]

    answer = max(width) * max(height)

    return answer


solution([[60, 50], [30, 70], [60, 30], [80, 40]])

# %%
sizes = [[60, 50], [30, 70], [60, 30], [80, 40]]

new_sizes = list(map(lambda size: [max(size), min(size)], sizes))

width, height = [i[0] for i in new_sizes], [i[1] for i in new_sizes]

max(width) * max(height)
