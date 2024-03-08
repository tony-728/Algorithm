# %%
def solution(babbling):
    answer = 0
    can_speaking = ["aya", "ye", "woo", "ma"]

    for ba in babbling:
        if ba in can_speaking:
            answer += 1

        else:
            # 아기가 발음할 수 있는지 확인해야 한다. 같은 발음이 연속해서 등장하면 안된다.
            new_ba = (
                ba.replace("aya", "1")
                .replace("ye", "2")
                .replace("woo", "3")
                .replace("ma", "4")
            )
            if new_ba.isdigit():
                is_continue = new_ba[0]
                for i in new_ba[1:]:
                    if is_continue == i:
                        break
                    else:
                        is_continue = i
                else:
                    answer += 1
            else:
                continue
    return answer


# %%
def solution(babbling):
    count = 0

    for b in babbling:
        if "ayaaya" in b or "yeye" in b or "woowoo" in b or "mama" in b:
            continue
        if (
            not b.replace("aya", " ")
            .replace("ye", " ")
            .replace("woo", " ")
            .replace("ma", " ")
            .replace(" ", "")
        ):
            count += 1

    return count


solution(["wooyemawooye", "mayaa", "ymaeaya"])
