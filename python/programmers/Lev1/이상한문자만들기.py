#%%
def solution(s):
    s = s.split(' ')
    answer = ''
    check = []
    for word in s:
        new = ''
        for i, d in enumerate(word):
            if i % 2: # 홀수
                new += d.lower()
            else:
                new += d.upper()
        check.append(new)

    answer = ' '.join(check)
    
    return answer

solution('try HELLo world')

#%%

# s = 'try hello world'
s = 'aaaaaaadfadf'

s = s.split(' ')
answer = []


for word in s:
    new = ''
    for i, d in enumerate(word):
        if i % 2: # 홀수
            new += d.lower()
        else:
            new += d.upper()
    answer.append(new)

answer = ' '.join(answer).strip()

print(answer)

