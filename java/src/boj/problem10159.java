package boj;

import java.io.*;
import java.util.*;

public class problem10159 {
    /*
     * 그래프
     * - 작은쪽으로만 이동할 수 있는 단방향 그래프를 구성한다.
     * 
     * 시작 위치에서 이동할 수 있는 노드의 개수를 체크
     * 이동하면서 경유한 노드는 해당 인덱스에 카운트 추가
     * 
     * 최종적으로 전체 노드에서 자신을 뺀 값에서 카운트 된 값을 제외한 값이 각 노드별로 정답
     * 
     * bfs
     * 
     */

    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    static int numOfNode;
    static int numOfRelation;

    static Node[] graph;

    static StringBuilder sb = new StringBuilder();

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfNode = Integer.parseInt(br.readLine().trim());
        numOfRelation = Integer.parseInt(br.readLine().trim());

        graph = new Node[numOfNode + 1];

        for (int idx = 0; idx < numOfRelation; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());

            graph[big] = new Node(small, graph[big]);
        }
    }

    static void solution() {
        int[] arrOfNodeCount = new int[numOfNode + 1];

        for (int idx = 1; idx < numOfNode + 1; idx++) {
            boolean[] visited = new boolean[numOfNode + 1];

            Deque<Integer> q = new ArrayDeque<>();

            q.addLast(idx);
            visited[idx] = true;

            while (!q.isEmpty()) {

                int start = q.pollFirst();

                for (Node node = graph[start]; node != null; node = node.next) {
                    // 방문확인
                    if (visited[node.to]) {
                        continue;
                    }


                    q.addLast(node.to);
                    visited[node.to] = true;

                    // 시작 노드와 비교 가능함
                    // 시작 노드가 더 큼
                    arrOfNodeCount[idx]++;
                    // 시작 노드보다 작음
                    arrOfNodeCount[node.to]++;
                }
            }
        }


        for (int idx = 1; idx < numOfNode + 1; idx++) {
            sb.append(numOfNode - 1 - arrOfNodeCount[idx]).append("\n");
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);

    }
}
