from collections import defaultdict


def solution(genres, plays):
    answer = []

    genre_dict = defaultdict(int)  # genre: total_count
    gp_dict = defaultdict(list)
    i = 0

    for g, p in zip(genres, plays):
        genre_dict[g] += p
        gp_dict[g].append((p, i))
        i += 1

    genre_dict = sorted(genre_dict.items(), key=lambda x: x[1], reverse=True)

    for g, _ in genre_dict:
        temp = sorted(gp_dict[g], key=lambda x: x[0], reverse=True)
        count = 2

        for p, i in temp:
            answer.append(i)
            count -= 1

            if count == 0:
                break

    return answer
