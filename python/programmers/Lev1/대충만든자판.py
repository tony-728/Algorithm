# %%
def solution(keymap, targets):
    answer = []
    keymap_dict = {}
    for key in keymap:
        if keymap_dict:
            for k in key:
                if k in keymap_dict:
                    keymap_dict[k] = min(keymap_dict[k], key.find(k) + 1)
                else:
                    keymap_dict[k] = key.find(k) + 1
        else:
            keymap_dict = {k: key.find(k) + 1 for k in key}

    for target in targets:
        count = 0
        for t in target:
            if t in keymap_dict:
                count += keymap_dict[t]
            else:
                count = -1
                break

        answer.append(count)

    return answer


# keymap = ['ABACD','BCEFD']
# targets = ['ABCD','AABB']
# keymap = ["AA"]
# targets = ["B"]
keymap = ["AGZ", "BSSS"]
targets = ["ASA", "BGZ"]
solution(keymap, targets)
