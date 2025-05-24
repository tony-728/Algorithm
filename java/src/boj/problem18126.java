package boj;

import java.io.*;
import java.util.*;

public class problem18126 {
    /*
     * N개의 방을 갖고 있다.
     * 입구를 포함한 모든 방은 1부터 N까지의 번호가 있고 입구는 1번
     * 입구는 하나이며 입구와 모든 방들은 N-1개의 길로 서로 오고 갈 수 있다.
     * 
     * 입구에서 최대한 먼 곳에 아이스크림을 숨기는 거리를 구하라
     * 
     * 다익스트라 + 자료형 방 별 거리가 최대 10억이므로 long으로 처리해야함
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

    static int numOfRoom;
    static Node[] graph;
    static long answer;

    static void inputData() throws IOException {
        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfRoom = Integer.parseInt(br.readLine().trim());
        graph = new Node[numOfRoom + 1];

        for (int idx = 0; idx < numOfRoom - 1; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start] = new Node(end, weight, graph[start]);
            graph[end] = new Node(start, weight, graph[end]);
        }
    }

    static void solution() {

        long[] minDistance = new long[numOfRoom + 1];

        Arrays.fill(minDistance, Long.MAX_VALUE);

        minDistance[1] = 0;

        PriorityQueue<long[]> pQ = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));

        pQ.add(new long[] {1, 0});

        while (!pQ.isEmpty()) {
            long[] room = pQ.poll();

            for (Node node = graph[(int) room[0]]; node != null; node = node.next) {
                if (minDistance[node.to] > room[1] + node.weight) {

                    minDistance[node.to] = room[1] + node.weight;

                    pQ.add(new long[] {node.to, minDistance[node.to]});
                }
            }
        }

        for (int idx = 1; idx <= numOfRoom; idx++) {
            answer = Math.max(answer, minDistance[idx]);
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);

    }
}
