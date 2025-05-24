package boj;

import java.io.*;
import java.util.*;

public class problem18352 {
    /*
     * N개의 도시, M개의 단방향 도로를 갖고 있다.
     * 특정 도시에서 출발하여 도달할 수 있는 모든 도시 중에서 최단 거리가 k인 모든 도시를 출력
     * 
     * 
     * 다익스트라 
     * 
     * 목표 거리에 맞는 노드만 출력
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

    static int numOfCity;
    static int startCity;
    static int targetWeight;
    static Node[] graph;
    static int answer;

    static StringBuilder sb = new StringBuilder();

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());
        numOfCity = Integer.parseInt(st.nextToken());
        int numOfEdge = Integer.parseInt(st.nextToken());
        targetWeight = Integer.parseInt(st.nextToken());
        startCity = Integer.parseInt(st.nextToken());

        graph = new Node[numOfCity + 1];

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start] = new Node(end, 1, graph[start]);
        }
    }

    static void solution() {

        int[] minDistance = new int[numOfCity + 1];

        Arrays.fill(minDistance, Integer.MAX_VALUE);

        minDistance[startCity] = 0;

        PriorityQueue<int[]> pQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

        pQ.add(new int[] {startCity, 0});

        while (!pQ.isEmpty()) {
            int[] room = pQ.poll();

            for (Node node = graph[(int) room[0]]; node != null; node = node.next) {
                if (minDistance[node.to] > room[1] + node.weight) {

                    minDistance[node.to] = room[1] + node.weight;

                    pQ.add(new int[] {node.to, minDistance[node.to]});
                }
            }
        }

        // System.out.println(Arrays.toString(minDistance));

        for (int idx = 1; idx <= numOfCity; idx++) {
            if (minDistance[idx] == targetWeight) {
                sb.append(idx).append("\n");
            }
        }

        if (sb.length() == 0) {
            sb.append(-1);
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);

    }
}
