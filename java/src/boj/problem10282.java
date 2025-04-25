package boj;

import java.io.*;
import java.util.*;

public class problem10282 {
    /*
     * 컴퓨터가 해킹을 당했다.
     * 서로 의지하는 컴퓨터는 전염이 된다.
     * - 컴퓨터 a가 컴퓨터 b에 의존한다면, b가 감염되면 일정 시간 뒤 a도 감염된다.
     * - b가 a를 의존하지 않는다면 a가 감염되더라도 b는 안전하다.
     * 
     * 다익스트라
     * bfs
     */

    static class Node {
        int to;
        int weight;
        Node next;

        public Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfComputer;
    static int numOfRelation;
    static int hackedComputer;

    static Node[] graph;

    static final int INF = 987654321;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfComputer = Integer.parseInt(st.nextToken());
        numOfRelation = Integer.parseInt(st.nextToken());
        hackedComputer = Integer.parseInt(st.nextToken());

        graph = new Node[numOfComputer + 1];

        for (int idx = 0; idx < numOfRelation; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from] = new Node(to, weight, graph[from]);
        }
    }

    static void solution() {

        int numOfHacked = 0;
        int answerTime = 0;

        int[] arrOfWeight = new int[numOfComputer + 1];
        
        Arrays.fill(arrOfWeight, INF);
        
        // boolean[] visited = new boolean[numOfComputer + 1];
        // arrOfWeight[hackedComputer] = 0;

        // 모든 정점에 대해서 판별한다.
        // for (int idx = 0; idx < numOfComputer; idx++) {

        //     int minWeight = INF;
        //     int stopOver = -1;

        //     for (int nodeIdx = 1; nodeIdx < numOfComputer + 1; nodeIdx++) {

        //         if (!visited[nodeIdx] && arrOfWeight[nodeIdx] < minWeight) {
        //             minWeight = arrOfWeight[nodeIdx];
        //             stopOver = nodeIdx;
        //         }
        //     }

        //     if (stopOver == -1) {
        //         break;
        //     }

        //     visited[stopOver] = true;

        //     for (Node node = graph[stopOver]; node != null; node = node.next) {

        //         if (arrOfWeight[node.to] > minWeight + node.weight) {
        //             arrOfWeight[node.to] = minWeight + node.weight;
        //         }
        //     }
        // }

        // pQ를 사용한 다익스트라
        // for문으로 구현한 다익스트라보다 더 빠르다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        pq.offer(new int[] {hackedComputer, 0});
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            // 계산한 최단 경로 비용보다 현재 이동 비용이 크면 통과
            if (arrOfWeight[now[0]] <= now[1]) {
                continue;
            }

            // 최단 경로 비용에 현재 비용으로 갱신
            arrOfWeight[now[0]] = now[1];

            // 현재 경유지에서 이동할 수 있는 경로를 pQ에 추가
            for (Node node = graph[now[0]]; node != null; node = node.next) {
                int nNode = node.to;    // 새로 이동하는 노드
                int nCost = now[1] + node.weight; // 현재까지 이동 비용 + 현재노드에서 다른 노드로 가는 비용

                // 계산한 최단 경로 비용보다 새롭게 계산한 최단 비용이 작으면 pQ에 추가
                if (arrOfWeight[nNode] > nCost) {
                    pq.add(new int[] {nNode, nCost});
                }
            }
        }

        for (int idx = 1; idx < numOfComputer + 1; idx++) {
            if (arrOfWeight[idx] != INF) {
                numOfHacked++;

                if (answerTime < arrOfWeight[idx]) {
                    answerTime = arrOfWeight[idx];
                }
            }
        }

        sb.append(numOfHacked).append(" ").append(answerTime).append("\n");

    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            inputData();

            solution();
        }

        System.out.println(sb);
    }
}
