package boj;

import java.io.*;
import java.util.*;

public class problem17299 {
    /*
     * 크기가 N인 수열
     * 수열의 각 원소 a에 대해서 오등큰수를 구하려고 한다.
     * 
     * a가 수열에 등장한 횟수를 F(a)라고 할 때 
     * a의 오등큰수는 오른쪽에 있으면서 수열에서 등장한 횟수가 F(a)보다 큰 수 중에서 가장 왼쪽에 있는 수
     * 이러한 경우가 없는 경우 오등큰수는 -1이다.
     * 
     * n^2으로는 풀 수 없다.
     * 배열 길이가 최대 100만이기 때문에 
     * 
     * 뒤에서 부터 정답을 찾아간다.
     * 오등큰수를 스택으로 관리한다.
     * 
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int lengthOfArr;
    static int[] arrOfNumber;
    static int[] arrOfNumberCount = new int[1_000_001];
    static int[] answer;

    static void inputData() throws IOException {
        lengthOfArr = Integer.parseInt(br.readLine().trim());

        arrOfNumber = new int[lengthOfArr];
        answer = new int[lengthOfArr];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < lengthOfArr; idx++) {
            int value = Integer.parseInt(st.nextToken());

            arrOfNumber[idx] = value;
            arrOfNumberCount[value]++;
        }
    }

    static void solution() {

        Deque<Integer> stack = new ArrayDeque<>();

        // 역순으로 확인
        for (int idx = lengthOfArr - 1; idx > -1; idx--) {
            boolean flag = true;

            // 오등 큰 수가 있는지 확인
            while (!stack.isEmpty()) {

                // 현재 수열의 값의 횟수보다 오등 큰 수의 횟수가 더 크다면 
                // 현재 수열의 값은 오등큰수가 존재함
                if (arrOfNumberCount[arrOfNumber[idx]] < arrOfNumberCount[stack.peek()]) {
                    answer[idx] = stack.peek();
                    flag = false;
                    break;

                    // 자신보다 작은 값은 스택에서 제거
                } else {
                    stack.pollFirst();
                }
            }

            // 오등큰수가 없다.
            if (flag) {
                answer[idx] = -1;
            }
            stack.addFirst(arrOfNumber[idx]);
        }

        for (int idx = 0; idx < lengthOfArr; idx++) {
            sb.append(answer[idx]).append(" ");
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.print(sb);
    }
}
