package boj;

import java.io.*;
import java.util.*;

public class problem1766 {
    /**
     * 1번부터 N번까지 N개의 문제가 있다.
     * - 문제는 난이도 순서로 되어있다.
     * - 1번이 쉽고 N번이 어렵다.
     * 
     * 먼저 푸는 것이 좋은 문제가 있다.
     * 
     * 조건에 따라 문제를 풀 순서를 정한다.
     * 1. N개의 문제는 모두 풀어야 한다.
     * 2. 먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 풀어야 하는 문제를 풀어야 한다.
     * 3. 가능하면 쉬운 문제부터 풀어야 한다.
     * 
     * a b
     * a를 풀고 b를 풀어야 한다.
     * 
     * 그래프
     * 위상 정렬
     */

    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    static int numOfProblem;
    static int numOfOrder;
    static Node[] graph;
    static int[] indegree;
    static StringBuilder sb = new StringBuilder();

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfProblem = Integer.parseInt(st.nextToken());
        numOfOrder = Integer.parseInt(st.nextToken());

        graph = new Node[numOfProblem + 1];
        indegree = new int[numOfProblem + 1];

        for (int idx = 0; idx < numOfOrder; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from] = new Node(to, graph[from]);
            indegree[to]++; // 먼저 풀어야할 문제의 개수
        }
    }

    static void solution() {

        // pq를 사용하는 이유는 숫자가 낮은 문제가 난이도가 낮기 때문에 낮은 숫자의 문제를 먼저 출력하기 위해서
        PriorityQueue<Integer> q = new PriorityQueue<>();

        // 선행 문제가 없는 것들을 넣어준다.
        for (int idx = 1; idx < numOfProblem + 1; idx++) {
            if (indegree[idx] == 0) {
                q.offer(idx);
            }
        }

        while (!q.isEmpty()) {

            // 선행 문제가 0개인 문제들
            int problem = q.poll();
            sb.append(problem).append(" ");


            for (Node node = graph[problem]; node != null; node = node.next) {
                // 선행 문제를 해결했다.
                indegree[node.to]--;

                // 선행 문제가 0개면 큐에 추가
                if (indegree[node.to] == 0) {
                    q.offer(node.to);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);
    }
}
