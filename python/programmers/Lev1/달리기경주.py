def solution(players, callings):
    answer = []

    index_dict = {i: player for i, player in enumerate(players)}
    players_dict = {player: i for i, player in enumerate(players)}

    for call in callings:
        rank = players_dict[call]
        players_dict[call] -= 1
        players_dict[index_dict[rank]] += 1

        temp = index_dict[rank]
        index_dict[rank] = call
        index_dict[rank + 1] = temp

    return list(index_dict.values())


# %%
def solution(players, callings):
    answer = []

    players_dict = {player: i for i, player in enumerate(players)}

    for call in callings:
        rank = players_dict[call]
        players_dict[call] -= 1
        players_dict[players[rank - 1]] += 1

        players[rank - 1], players[rank] = players[rank], players[rank - 1]

    return players
