package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problem2930 {
    /*
     * 힙
     * 
     * 연산 1: 최대 힙에 추가
     * 연산 2: 최대 힙의 루트 노드의 키 값을 출력하고 해당 노드를 삭제
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfOperator;

    static PriorityQueue<Integer> pQ;

    static void inputTestCase() throws IOException {
        numOfOperator = Integer.parseInt(br.readLine().trim());

        // java는 최소힙이기 때문에 -를 붙여서 최대힙으로 변경
        pQ = new PriorityQueue<>();

        for (int operatorIdx = 0; operatorIdx < numOfOperator; operatorIdx++) {
            st = new StringTokenizer(br.readLine().trim());

            int operator = Integer.parseInt(st.nextToken());

            if (operator == 1) {
                int value = -Integer.parseInt(st.nextToken());
                pQ.offer(value);
            } else {
                if (pQ.isEmpty()) {
                    sb.append(-1).append(" ");
                } else {
                    sb.append(-pQ.poll()).append(" ");
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {

            sb.append("#").append(tc).append(" ");

            inputTestCase();
            sb.append("\n");
        }

        System.out.println(sb);
    }

}
