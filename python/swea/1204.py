from collections import Counter

T = int(input())
for test_case in range(1, T + 1):
    n = input()
    l = Counter(map(int, input().split()))

    print(f"#{n} {l.most_common()[0][0]}")
