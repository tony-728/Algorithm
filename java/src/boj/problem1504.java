package boj;

import java.io.*;
import java.util.*;

public class problem1504 {

    /*
     * 1번 정점에서 N번 정점으로 최단 거리
     * 
     * 조건
     * 임의로 주어진 두 정점은 반드시 통과해야한다.
     * 
     * - 방문한 정점, 간선은 다시 갈 수 있다.
     * 
     * 최단 거리를 구하라
     * 경로가 없을 때는 -1을 출력한다.
     * 
     * 다익스트라
     * 
     * 시작 노드, 중간 노드1, 중간 노드2의 다익스트라를 적용해서
     * 
     * 최소값으로 계산
     * min(1,mid1) + min(mid1, mid2) + min(mid2, N)
     * min(1, mid2) + mid(mid2, mid1) + mid(mid1, N)
     * 이 중 최소가 정답
     * 
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

    static int numOfNode;
    static int numOfEdge;

    static Node[] graphNode;
    static int[][] graph;

    static int[] distanceStart;
    static int[] distanceMid1;
    static int[] distanceMid2;

    static int answer;

    static int mid1;
    static int mid2;

    static final int INF = 20_000 * 1000 + 1;

    static void inputTestByAdjList() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine());

        numOfNode = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        graphNode = new Node[numOfNode + 1];

        distanceStart = new int[numOfNode + 1];
        distanceMid1 = new int[numOfNode + 1];
        distanceMid2 = new int[numOfNode + 1];

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graphNode[start] = new Node(end, cost, graphNode[start]);
            graphNode[end] = new Node(start, cost, graphNode[end]);
        }

        st = new StringTokenizer(br.readLine().trim());

        mid1 = Integer.parseInt(st.nextToken());
        mid2 = Integer.parseInt(st.nextToken());
    }

    static void solutionByAdjList(int node, int[] distance) {

        boolean[] visited = new boolean[numOfNode + 1];

        Arrays.fill(distance, INF);
        distance[node] = 0;

        // 모든 노드를 확인한다.
        for (int idx = 0; idx < numOfNode; idx++) {
            int min = INF;
            int stopOver = -1;

            // 시작점에서 방문하지 않은 정점 중에 가장 가까운 노드를 선택한다.
            for (int to = 1; to < numOfNode + 1; to++) {

                // 방문하지 않았으면서 최소값보다 이동할 노드의 거리가 더 짧으면
                if (!visited[to] && min > distance[to]) {
                    min = distance[to];
                    stopOver = to;
                }
            }

            // 중간 정점이 없으면 통과
            if (stopOver == -1) {
                break;
            }

            // 확인한 노드는 방문처리
            visited[stopOver] = true;

            // 미방문 정점에 대해서 선택된 경유지를 통해 다른 노드로 이동하는 최소값을 찾는다.
            for (Node temp = graphNode[stopOver]; temp != null; temp = temp.next) {
                if (distance[temp.to] > min + temp.weight) {
                    distance[temp.to] = min + temp.weight;
                }
            }
        }
    }

    static void inputTestByAdjMatrix() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine());

        numOfNode = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        graph = new int[numOfNode + 1][numOfNode + 1];

        distanceStart = new int[numOfNode + 1];
        distanceMid1 = new int[numOfNode + 1];
        distanceMid2 = new int[numOfNode + 1];

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start][end] = cost;
            graph[end][start] = cost;
        }

        st = new StringTokenizer(br.readLine().trim());

        mid1 = Integer.parseInt(st.nextToken());
        mid2 = Integer.parseInt(st.nextToken());
    }

    static void solutionByAdjMatrix(int node, int[] distance) {

        boolean[] visited = new boolean[numOfNode + 1];

        Arrays.fill(distance, INF);
        distance[node] = 0;

        // 모든 노드를 확인한다.
        for (int idx = 0; idx < numOfNode; idx++) {
            int min = INF;
            int stopOver = -1;

            // 시작점에서 방문하지 않은 정점 중에 가장 가까운 노드를 선택한다.
            for (int to = 1; to < numOfNode + 1; to++) {

                // 방문하지 않았으면서 최소값보다 이동할 노드의 거리가 더 짧으면
                if (!visited[to] && min > distance[to]) {
                    min = distance[to];
                    stopOver = to;
                }
            }

            // 시작 노드에 연결된 정점이 하나도 없으면 정지
            if (stopOver == -1) {
                break;
            }

            // 확인한 노드는 방문처리
            visited[stopOver] = true;

            // 미방문 정점에 대해서 선택된 경유지를 통해 다른 노드로 이동하는 최소값을 찾는다.
            // 경유지를 통해 갈 수 있어야 함
            for (int to = 1; to < numOfNode + 1; to++) {
                if (distance[to] > min + graph[stopOver][to] && graph[stopOver][to] != 0) {
                    distance[to] = min + graph[stopOver][to];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        // 인접 리스트로 입력과 구현한 방식 412ms
        // inputTestByAdjList();

        // solutionByAdjList(1, distanceStart);
        // solutionByAdjList(mid1, distanceMid1);
        // solutionByAdjList(mid2, distanceMid2);
        
        // 인접 행렬로 입력과 구현한 방식 368ms
        inputTestByAdjMatrix();

        solutionByAdjMatrix(1, distanceStart);
        solutionByAdjMatrix(mid1, distanceMid1);
        solutionByAdjMatrix(mid2, distanceMid2);

        int mid1Distance = distanceStart[mid1] + distanceMid1[mid2] + distanceMid2[numOfNode];
        int mid2Distance = distanceStart[mid2] + distanceMid2[mid1] + distanceMid1[numOfNode];

        answer = Math.min(mid1Distance, mid2Distance);

        // 최대값 계산을 생각해야함
        // 최대 간선 수 20_000, 최대 비용 1000
        // 최대값 20_000 * 1_000 일 수 있다.
        if (mid1Distance >= INF && mid2Distance >= INF) {
            answer = -1;
        }

        System.out.println(answer);
    }
}
