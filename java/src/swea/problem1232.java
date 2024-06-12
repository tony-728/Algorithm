package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1232 {
    /*
     * 사칙연산과 양의 정수로만 구성된 임의의 이진 트리가 주어질 때
     * 이를 계산한 결과를 출력하는 프로그램
     * 계산 과정에서의 연산은 모두 실수
     * 
     * 테스트케이스는 10개 주어진다.
     * 정점이 정수면 정점 번호와 양의 정수가 주어진다.
     * 정점이 연산자면 정점 번호, 연산자, 해당 정점의 왼쪽 자식 오른쪽 자식의 정점 번호가 차례대로 주어진다.
     * 정점은 1부터 N까지 루트 정점은 1
     * 
     */

    static int testCase = 1;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static String[] tree;
    static int[] parentsTree;
    static int numOfNode;

    static void inputTestCase() throws IOException {
        numOfNode = Integer.parseInt(br.readLine().trim());

        tree = new String[numOfNode + 1]; // 루트 정점은 1부터이기 때문
        parentsTree = new int[numOfNode + 1];

        for (int idx = 0; idx < numOfNode; idx++) {
            parentsTree[idx] = 1;
        }

        for (int idx = 0; idx < numOfNode; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int node = Integer.parseInt(st.nextToken());

            // 현재 남은 토큰의 개수가 1개보다 많으면 자식노드가 있는 정점
            if (st.countTokens() > 1) {
                tree[node] = st.nextToken();

                // 자식 노드가 무엇인지 저장
                parentsTree[node] = Integer.parseInt(st.nextToken());
                st.nextToken();
            } else {

                tree[node] = st.nextToken();
            }
        }

    }

    static double calculate(int node) {

        // 현재 노드가 연산자인지 확인
        if (tree[node].equals("+")) {
            return calculate(parentsTree[node]) + calculate(parentsTree[node]+1);
        } else if (tree[node].equals("-")) {
            return calculate(parentsTree[node]) - calculate(parentsTree[node]+1);
        } else if (tree[node].equals("/")) {
            return calculate(parentsTree[node]) / calculate(parentsTree[node]+1);
        } else if (tree[node].equals("*")) {
            return calculate(parentsTree[node]) * calculate(parentsTree[node]+1);
        } else {
            return Double.parseDouble(tree[node]);
        }
    }

    public static void main(String[] args) throws IOException {

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            sb.append(String.format("#%d %d\n", tc, (int) calculate(1)));
        }

        System.out.println(sb);
    }
}
