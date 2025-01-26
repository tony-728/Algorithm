package boj;

import java.io.*;
import java.util.*;

public class problem33147 {
    /*
     * 길이가 N인 순열이 주어진다.
     * - 0부터 N-1까지의 정수가 정확히 한 번씩 등장하는 수열
     * 
     * 양의 정수 K가 주어질 때, 다음과 같은 연산을 원하는 만큼 수행할 수 있다.
     * - 임의의 i에 대해 Ai, A(i+K) mod N의 값을 바꾼다.
     * 
     * 주어진 연산을 통해 순열 A를 오름차순으로 정렬할 수 있는지 확인하라
     * - 정렬할 수 있으면 yes 아니면 no
     * 
     * n최대는 100만
     * 
     * 
     * 유클리드 호제법
     * 최대공약수를 이용한 문제 풀이
     * - 교환이 가능한 사이클에 순환 간격이 최대공약수이다.
     * - 전체 길이와 갭으로 주어진 값의 최대공약수를 구한다.
     * - 입력으로 주어진 값과 해당하는 인덱스를 최대공약수를 나눈 나머지가 같지 않으면 정렬할 수 없다.
     * 
     * 정렬할 수 없는 이유
     * - 위에도 말한 것처럼 주어진 값과 인덱스가 서로 다른 순환 간격을 갖고 있으면 구할 수 없다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int K;

    static int[] numberList;
    static String answer;


    static void inputData() throws IOException {

        answer = "YES";
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numberList = new int[N];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < N; idx++) {
            numberList[idx] = Integer.parseInt(st.nextToken());
        }
    }

    static void solution() {
        // 최대 공약수 구하기
        // 유클리드 호제법을 이용

        int a = N;
        int b = K;
        int temp = 0;

        while (b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }


        for (int idx = 0; idx < N; idx++) {
            if (numberList[idx] % a != idx % a) {
                answer = "NO";
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        inputData();

        solution();

        System.out.println(answer);

    }
}
