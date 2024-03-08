def preprocess(string):
    return (
        string.replace("C#", "c")
        .replace("D#", "d")
        .replace("F#", "f")
        .replace("G#", "g")
        .replace("A#", "a")
    )


def solution(m, musicinfos):
    result = ""

    m = preprocess(m)

    for music in musicinfos:
        start, end, name, melody = music.split(",")

        melody = preprocess(melody)

        melody_time = len(melody)

        start_h, start_m = map(int, start.split(":"))
        start = start_h * 60 + start_m

        end_h, end_m = map(int, end.split(":"))

        total_time = (end_h - start_h) * 60 + (end_m - start_m)

        play_melody = (
            melody * (total_time // melody_time) + melody[: (total_time % melody_time)]
        )

        if m not in play_melody:
            continue

        if (
            not result
            or result[0] < total_time
            or (result[0] == total_time and result[1] > start)
        ):
            result = (total_time, start, name)

    if result:
        answer = result[-1]
    else:
        answer = "(None)"

    return answer
