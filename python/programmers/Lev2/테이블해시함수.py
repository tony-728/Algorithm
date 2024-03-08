def solution(data, col, row_begin, row_end):
    answer = 0

    """
    주어진 2차원 데이터의 col에 해당하는 값을 기준으로 행을 오름차순 정렬한다.
    이때 같은 값인 경우 첫번째 col에 해당하는 값을 기준으로 내림차순 정렬한다.
    
    정렬이 완료된 이후 row_begin, row_end에 해당하는 row의 값을 row_begin, row_end의 값으로 나머지 연산을 각각 수행한다.
    
    이렇게 얻게 된 2개의 값을 XOR 연산을 취하여 최종 값을 얻는다.
    
    """

    data.sort(key=lambda x: x[0], reverse=True)
    data.sort(key=lambda x: x[col - 1])

    result = []

    for r_index in range(row_begin, row_end + 1):
        result.append(sum([i % r_index for i in data[r_index - 1]]))

    answer = result[0]

    for e in result[1:]:
        answer = answer ^ e

    return answer
