package boj;

import java.io.*;
import java.util.*;

public class problem2606 {
    /*
     * 네트워크에 연결된 컴퓨터는 바이러스가 전파 된다.
     * 네트워크에 연결되지 않은 컴퓨터는 바이러스가 전파되지 않는다.
     * 
     * 1번 컴퓨터가 바이러스에 걸렸다.
     * 총 바이러스에 걸리게 되는 컴퓨터의 수를 출력하라
     * 
     * bfs
     * 
     * 총 컴퓨터의 수가 주어진다.
     * 네트워크에 연결된 컴퓨터의 쌍의 수가 주어진다.
     * 네트워크에 연결된 컴퓨터의 쌍이 주어진다.
     */

    static class Node {
        int end;
        Node next;

        public Node(int end, Node next) {
            this.end = end;
            this.next = next;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfComputer;
    static int numOfEdge;

    static Node[] nodeList;
    static int answer;

    static void inputData() throws IOException {
        answer = 0;

        numOfComputer = Integer.parseInt(br.readLine().trim());
        numOfEdge = Integer.parseInt(br.readLine().trim());

        nodeList = new Node[numOfComputer + 1];

        // 인접리스트로 연결관계를 입력받음
        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            nodeList[start] = new Node(end, nodeList[start]);

            nodeList[end] = new Node(start, nodeList[end]);
        }
    }

    public static void main(String[] args) throws IOException {

        inputData();

        boolean[] visited = new boolean[numOfComputer + 1];

        Deque<Integer> q = new ArrayDeque<>();

        q.addLast(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int start = q.pollFirst();

            // 인접리스트에 시작 노드에 연결되어 있는 모든 요소를 확인
            for (Node node = nodeList[start]; node != null; node = node.next) {
                // 방문한 노드가 아닐 때
                // 방문처리 및 감염처리, 큐에 추가
                if (visited[node.end] == false) {
                    answer++;
                    q.addLast(node.end);
                    visited[node.end] = true;
                }
            }
        }

        System.out.println(answer);


    }
}
