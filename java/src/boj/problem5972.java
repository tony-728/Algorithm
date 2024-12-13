package boj;

import java.io.*;
import java.util.*;

public class problem5972 {
    /*
    n개의 헛간 m개의 양방향 길이 있다.
    
    각각의 길은 c 마리의 소가 있다. 
    소들의 길은 두 개의 떨어진 헛간인 a와 b를 잇는다.
    
    두 개의 헛간은 하나 이상의 길로 연결되어 있을 수도 있다.
    
    현서는 헛간 1에 있고 찬홍은 헛간 N에 있다.
    
    현서가 찬홍이에게 택배를 배달해야한다.
    가는 길에 만나는 모든 소들에게 여물을 주어야한다.
    최소한의 소들을 만나면서 지나가야 한다.
    
    bfs
    - 방문배열을 해당 위치까지의 최소비용으로 처리한다.
    
    */

    static class Node {
        int start;
        int end;
        int cost;
        Node next;

        public Node(int start, int end, int cost, Node next) {
            this.start = start;
            this.end = end;
            this.cost = cost;
            this.next = next;
        }
    }


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfNode;
    static int numOfEdge;
    static Node[] nodeList;
    static int[] visited;

    static void inputData() throws IOException {

        st = new StringTokenizer(br.readLine().trim());

        numOfNode = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        nodeList = new Node[numOfNode + 1];
        visited = new int[numOfNode + 1];

        Arrays.fill(visited, Integer.MAX_VALUE);

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodeList[start] = new Node(start, end, cost, nodeList[start]);
            nodeList[end] = new Node(end, start, cost, nodeList[end]);
        }
    }

    static void solution() {

        Deque<Integer> pQ = new ArrayDeque<>();
        visited[1] = -1;

        for (Node node = nodeList[1]; node != null; node = node.next) {
            int end = node.end;
            int cost = node.cost;

            pQ.addLast(end);

            visited[end] = cost;
        }

        while (!pQ.isEmpty()) {

            int currentNode = pQ.pollFirst();

            for (Node node = nodeList[currentNode]; node != null; node = node.next) {
                int start = node.start;
                int end = node.end;
                int cost = node.cost;

                // 처음 이동하는 노드
                if (visited[end] == Integer.MAX_VALUE) {
                    pQ.addLast(end);
                    visited[end] = visited[start] + cost;
                    continue;
                }

                // 이전에 방문비용보다 현재 방문비용이 작은 경우 갱신
                if (visited[end] > visited[start] + cost) {
                    pQ.addLast(end);
                    visited[end] = visited[start] + cost;
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        inputData();

        solution();

        System.out.println(visited[numOfNode]);
    }
}
