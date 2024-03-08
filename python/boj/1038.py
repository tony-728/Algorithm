"""
음이 아닌 정수 X의 자릿수가 가장 큰 자릿수부터 작은 자릿수까지 감소한다면, 그 수를 감소하는 수라고 한다.
예를 들어, 321과 950은 감소하는 수지만, 322와 958은 아니다.
N번째 감소하는 수를 출력하는 프로그램을 작성하시오.
0은 0번째 감소하는 수이고, 1은 1번째 감소하는 수이다.
만약 N번째 감소하는 수가 없다면 -1을 출력한다.

0 <= N <= 1,000,000
"""

"""
1의 자리 0,1,2,3,4,5,6,7,8,9
10의 자리 10,20,21,30,31,32,...,98
100의 자리 2부터 시작 210,310,320,321,410,420,421,430,431,432,...,987
1,000의 자리 3부터 시작 3210,4210,4310,4320,4321,5410,...,9876
10,000의 자리 4부터 시작 43210,54210,54310,54320,...98765
100,000의 자리 5부터 시작 543210, 

각 자리 수에 가장 큰 자릿수에 해당하는 감소수를 미리 구한다.
"""


from itertools import combinations

N = int(input())

num_list = list()

for i in range(1, 11):  # 1~10까지의 조합을 생성, 0~9까지의 숫자로 만들 수 있음 최대 9876543210
    for comb in combinations(range(0, 10), i):  # 0~9의 숫자로 n개의 조합을 생성
        comb = list(comb)
        comb.sort(reverse=True)
        num_list.append(int("".join(map(str, comb))))

num_list.sort()

print(len(num_list))
print(num_list[1022])

# try:
#     print(num_list[N])
# except:
#     print(-1)
if len(num_list) + 1 < N:
    print(num_list[N])
else:
    print(-1)
