from collections import deque


def solution(cacheSize, cities):
    answer = 0

    cities = map(str.lower, cities)

    # deque의 maxlen으로 캐시 조건 완료
    cache = deque(maxlen=cacheSize)

    for city in cities:
        if city in cache:
            answer += 1
            # maxlen으로 자동으로 갯수가 맞춰지는데 remove를 한 이유
            # LRU 알고리즘에서 캐시에 중복된 데이터가 올 경우 중복으로 넣어주는 것이 아닌
            # 기존에 데이터를 최근으로 옮기는 작업이 필요함
            # 따라서 제거후 다시 넣어주는 방법으로 해결
            cache.remove(city)
            cache.append(city)

        else:
            answer += 5
            cache.append(city)

    return answer
