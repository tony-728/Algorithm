from collections import deque


def make_graph(begin, words):
    # 단어들의 연결에 따른 그래프를 만든다.
    # 단어들이 연결되기 위해서는 하나의 알파벳만 달라야 한다.
    check = {}

    max_len = len(begin)  # 모든 단어의 길이는 같다.

    words.append(begin)

    for word in words:
        edge = []

        for x in words:
            if word != x:
                i = 0
                diff = 0
                while i < max_len:
                    if word[i] != x[i]:
                        diff += 1

                    # 2개 이상의 알파벳이 다른 경우 연결 안됨
                    if diff == 2:
                        break

                    i += 1

                else:
                    edge.append(x)

        check[word] = edge

    return check


def solution(begin, target, words):
    answer = 0

    # BFS로 최종 단어를 찾아야 한다.

    if target in words:
        # 단어의 이동을 확인
        word_count = {word: 0 for word in words}
        word_count[begin] = 0

        # 단어의 방문을 확인
        word_check = {word: False for word in words}
        word_check[begin] = False

        graph = make_graph(begin, words)

        queue = deque()
        queue.append(begin)

        # BFS 알고리즘
        while queue:
            node = queue.popleft()
            word_check[node] = True

            for n in graph[node]:
                if word_check[n] == False:
                    queue.append(n)
                    # 한번도 이동을 확인하지 않은 경우에 갱신한다.
                    if word_count[n] == 0:
                        word_count[n] = word_count[node] + 1

        answer = word_count[target]

    return answer


begin = "hit"
target = "cog"
words = ["hot", "dot", "dog", "lot", "log", "cog"]
solution(begin, target, words)
