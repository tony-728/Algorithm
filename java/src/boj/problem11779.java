package boj;

import java.io.*;
import java.util.*;

public class problem11779 {
    /*
     * n개의 도시가 있다.
     * 한 도시에서 출발해서 다른 도시에 도착하는 m개의 버스가 있다.
     * 
     * A도시에서 B도시까지 가는데 드는 버스 비용을 최소화하려고 한다.
     * 
     * A->B 최소비용과 경로를 출력하라
     * 
     * 다익스트라
     * 경로확인
     * 
     */

    static class Path {
        int cost;
        List<Integer> path;

        public Path(int num) {
            this.cost = Integer.MAX_VALUE;
            this.path = new ArrayList<Integer>();
            this.path.add(num);
        }
    }

    static class Node {
        int to;
        int cost;
        Node next;

        public Node(int to, int cost, Node next) {
            this.to = to;
            this.cost = cost;
            this.next = next;
        }
    }

    static int numOfNode;
    static int numOfEdge;
    static Node[] graph;
    static Path[] path;

    static int start;
    static int end;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfNode = Integer.parseInt(br.readLine().trim());
        numOfEdge = Integer.parseInt(br.readLine().trim());

        graph = new Node[numOfNode + 1];
        path = new Path[numOfNode + 1]; // 기존 다익스트라에 distance 배열에 경로를 저장할 수 있도록 클래스로 관리

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from] = new Node(to, cost, graph[from]);
        }

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void solution() {
        PriorityQueue<int[]> pQ = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        // 시작 경로별 초기화
        for (int idx = 1; idx < numOfNode + 1; idx++) {
            path[idx] = new Path(idx);
        }

        pQ.add(new int[] {start, 0});
        path[start].cost = 0;

        while (!pQ.isEmpty()) {
            int[] now = pQ.poll();

            int nowNode = now[0];
            int nowCost = now[1];

            // 현재 노드가 도착지면 종료
            if (nowNode == end) {
                break;
            }

            // 현재 노드까지 가는 비용이 작으면 갱신 안함
            if (path[nowNode].cost < nowCost) {
                continue;
            }

            // 현재 노드에서 갈 수 있는 노드를 모두 확인한다.
            for (Node node = graph[nowNode]; node != null; node = node.next) {
                int newNode = node.to;
                int newCost = nowCost + node.cost;

                // 노드에 대한 비용이 비교하여 경로를 재생성한다.
                // 기존 다익스트라에서 추가된 부분
                if (path[newNode].cost > newCost) {
                    path[newNode].path = new ArrayList<>();

                    for (int idx : path[nowNode].path) {
                        path[newNode].path.add(idx);
                    }

                    path[newNode].cost = newCost;
                    path[newNode].path.add(newNode);

                    pQ.offer(new int[] {newNode, newCost});
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(path[end].cost).append("\n").append(path[end].path.size()).append("\n");

        for (int idx : path[end].path) {
            sb.append(idx).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();
    }
}
