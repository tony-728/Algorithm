package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1231 {
    /*
     * 
     * 트리가 주어지면 트리를 중위순회하면서 등장하는 문자를 출력하라
     * 
     * 10개의 테스트 케이스가 주어진다.
     * 정점의 갯수 N
     * 그래프는 완전이진트리 형태로 주어진다.
     * N줄에 걸쳐 각 정점의 정보가 주어진다.
     * 정점번호, 정점의 문자, 왼쪽자식 번호, 오른쪽자식 번호로 주어진다.
     * 자식이 없는 경우 생략된다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase = 1;

    static int numOfVertex;
    static char[] graph;

    static int hasChild;
    static int numOfChild;

    public static void inputTestCase() throws IOException {
        numOfVertex = Integer.parseInt(br.readLine().trim());

        // 자식노드를 갖는 마지막 정점번호
        hasChild = numOfVertex / 2;

        // 자식노드를 몇 개 갖고 있는지 확인
        numOfChild = numOfVertex % 2;

        // 정점번호가 1번부터 시작한다.
        graph = new char[numOfVertex + 1];

        // 그래프 정보 입력받기
        for (int idx = 0; idx < numOfVertex; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            // 정점 번호
            int number = Integer.parseInt(st.nextToken());

            // 정점이 갖고 있는 정보
            char vertex;

            if (number < hasChild) { // 4개가 있다.
                vertex = st.nextToken().charAt(0);
                st.nextToken();
                st.nextToken();

            } else if (number == hasChild) {
                vertex = st.nextToken().charAt(0);

                if (numOfChild == 0) {
                    st.nextToken();

                } else if (numOfChild == 1) {
                    st.nextToken();
                    st.nextToken();

                }

            } else {
                vertex = st.nextToken().charAt(0);
            }

            graph[number] = vertex;
        }

    }

    // 중위순회
    public static void inOrder(int vertex) {

        if (vertex > numOfVertex) {
            return;
        }

        inOrder(vertex * 2);

        sb.append(graph[vertex]);

        inOrder(vertex * 2 + 1);

    }

    public static void main(String[] args) throws IOException {

        for (int tc = 1; tc <= testCase; tc++) {

            sb.append("#").append(tc).append(" ");

            inputTestCase();

            inOrder(1);

            sb.append("\n");
        }

        System.out.println(sb);

    }
}
