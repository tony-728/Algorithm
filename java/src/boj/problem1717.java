package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1717 {
    /*
     * n+1개의 집합이 있다. 집합은 0~n까지
     * 합집합연산과 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행한다.
     * 
     * 0 a b: 합집합
     * 1 a b: a, b가 같은 집합인지 확인
     * - 같은 집합이면 YES, 다른 집합이면 NO
     * 
     * union-find
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final String NO = "NO";
    static final String YES = "YES";
    static final int UNION = 0;
    static final int CHECK_SAME_SET = 1;

    static int numOfSet; // 초기 집합의 개수
    static int numOfOperator; // 연산의 갯수
    static int[] parentsList; // 부모를 확인하기 위한 배열
    static int[] rank; // 합집합 기준으로 사용할 랭크

    // 합집합 연산
    static void union(int leftElement, int rightElement) {

        // 두 원소의 부모를 찾는다.
        int leftParent = find(leftElement);
        int rightParent = find(rightElement);

        // 부모가 같으면 합집합 연산을 하지 않아도 된다.
        if (leftParent == rightParent) {
            return;
        }

        // 부모가 다르다면 합집합 연산을 한다.
        // 합치는 기준은 랭크로 한다.

        // 왼쪽원소의 부모 랭크가 높으면 오른쪽원소의 부모를 왼쪽원소의 부모로 갱신
        if (rank[leftParent] > rank[rightParent]) {
            parentsList[rightParent] = leftParent;
            return;
        }

        // 랭크가 같거나 오른쪽이 더 큰 경우
        parentsList[leftParent] = rightParent;

        // 랭크가 같으면 오른쪽 부모의 랭크를 올려준다.
        if (rank[leftParent] == rank[rightParent]) {
            rank[rightParent]++;
        }
    }

    static int find(int element) {
        // 내가 부모면 종료
        if (parentsList[element] == element) {
            return element;
        }

        // 내가 부모가 아님
        // 부모가 누구인지 찾으러감
        // 가장 높은 부모로 갱신
        return parentsList[element] = find(parentsList[element]);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfSet = Integer.parseInt(st.nextToken());
        numOfOperator = Integer.parseInt(st.nextToken());

        // 부모를 가리킬 배열
        parentsList = new int[numOfSet + 1];
        rank = new int[numOfSet + 1];

        // 자기 자신을 부모로 초기화
        for (int idx = 0; idx < numOfSet + 1; idx++) {
            parentsList[idx] = idx;
        }

        for (int operatorIdx = 0; operatorIdx < numOfOperator; operatorIdx++) {

            st = new StringTokenizer(br.readLine().trim());

            int operator = Integer.parseInt(st.nextToken());
            int leftElement = Integer.parseInt(st.nextToken());
            int rightElement = Integer.parseInt(st.nextToken());

            // 합집합 연산일 때
            if (operator == UNION) {
                union(leftElement, rightElement);
            }

            // 같은 집합인지 확인할 때
            if (operator == CHECK_SAME_SET) {
                int leftParent = find(leftElement);
                int rightParent = find(rightElement);

                // 두 원소의 부모가 같다
                if (leftParent == rightParent) {
                    sb.append(YES).append('\n');
                    // 두 원소의 부모가 다르다
                } else {
                    sb.append(NO).append('\n');
                }
            }
        }

        System.out.println(sb);
    }
}
