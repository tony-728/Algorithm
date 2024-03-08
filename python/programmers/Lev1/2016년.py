#%%
def solution(a, b):
    answer = ''
    days = ['FRI','SAT','SUN', 'MON','TUE','WED','THU']

    month = [31,29,31,30,31,30,31,31,30,31,30,31]
    start_a = 1
    start_b = 1

    total = (sum(month[:a-start_a]) + (b-start_b)) % 7

    answer = days[total]

    return answer



solution(5, 24)


#%%
# 2016년에 a월 b일의 요일을 맞춰야함
# 20160101은 금요일

days = ['FRI','SAT','SUN', 'MON','TUE','WED','THU']

month = [31,29,31,30,31,30,31,31,30,31,30,31]

a = 5 # month
b = 24 # day

start_a = 1
start_b = 1

total = (sum(month[:a-start_a]) + (b-start_b)) % 7

days[total]


