package boj;

import java.io.*;
import java.util.*;

public class problem1916 {
    /*
     * N개의 도시가 있다.
     * 
     * 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다.
     * A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화하려고 한다.
     * 
     * A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하라
     * 
     * 다익스트라
     */


    static class Node implements Comparable<Node> {
        int to;
        int weight;
        Node next;

        public Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }

        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }


    static int numOfNode;
    static int numOfEdge;

    static Node[] graph;

    static int start, end;

    static int answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfNode = Integer.parseInt(br.readLine().trim());
        numOfEdge = Integer.parseInt(br.readLine().trim());

        graph = new Node[numOfNode + 1];

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from] = new Node(to, weight, graph[from]);
        }

        st = new StringTokenizer(br.readLine().trim());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void solution() {
        // 다익스트라
        int[] arrOfDistance = new int[numOfNode + 1];
        boolean[] visited = new boolean[numOfNode + 1];

        Arrays.fill(arrOfDistance, 987654321);
        arrOfDistance[start] = 0;

        // 모든 정점에 대해서 확인한다.
        for (int idx = 0; idx < numOfNode; idx++) {

            // 시작 정점에서 가장 가까운 정점을 찾는다.
            int minDistance = 987654321;
            int stopOver = -1;
            for (int nodeIdx = 1; nodeIdx < numOfNode + 1; nodeIdx++) {

                if (!visited[nodeIdx] && arrOfDistance[nodeIdx] < minDistance) {
                    minDistance = arrOfDistance[nodeIdx];
                    stopOver = nodeIdx;
                }
            }

            if (stopOver == -1) {
                break;
            }
            // System.out.println("경유지 " + stopOver + " min " + minDistance);

            visited[stopOver] = true;
            for (Node node = graph[stopOver]; node != null; node = node.next) {
                if (arrOfDistance[node.to] > minDistance + node.weight) {
                    arrOfDistance[node.to] = minDistance + node.weight;
                }
            }
        }

        // System.out.println(Arrays.toString(arrOfDistance));

        answer = arrOfDistance[end];

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
