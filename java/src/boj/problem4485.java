package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class problem4485 {
    /*
     * N x N 동굴에
     * (0,0) 젤다가 있다.
     * (N-1, N-1)까지 이동해야한다.
     * 젤다는 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.
     * 이동한 칸을 지나면 해당 칸의 값만큼 비용이 든다.
     * 
     * 목적지까지 이동할 때 드는 최소비용은 몇인가?
     * 
     * 첫줄에 N이 주어지고
     * 동굴의 각 값이 주어진다.
     * 0이 입력으로 들어오면 종료된다.
     * 
     * 
     * BFS + 다익스트라로 비용이 작을 때만 비용을 갱신해나가면서 목적지까지 진행한다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int size;
    static int[][] cave;
    static int[][] costMap;

    static final String MSG = "Problem ";
    static int problemNumber = 0;

    static final int START = 0;

    static final int INF = Integer.MAX_VALUE;

    static final int[] deltaX = { -1, 0, 1, 0 };
    static final int[] deltaY = { 0, -1, 0, 1 };

    static class Node implements Comparable<Node> {
        int rowIdx;
        int colIdx;
        int cost;

        public Node(int rowIdx, int colIdx, int cost) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.cost = cost;
        }

        public int compareTo(Node node) {
            return this.cost - node.cost;
        }

    }

    static void dijkstra() {

        costMap[0][0] = cave[0][0];

        PriorityQueue<Node> pQueue = new PriorityQueue<>();

        pQueue.offer(new Node(0, 0, cave[0][0]));

        while (!pQueue.isEmpty()) {

            Node currentNode = pQueue.poll();

            int cost = currentNode.cost;

            // 현재 노드의 비용보다 맵에 기록된 비용이 더 작은 경우 갱신하지 않는다.
            if (cost > costMap[currentNode.rowIdx][currentNode.colIdx]) {
                continue;
            }

            // 4방 확인
            for (int dir = 0; dir < deltaX.length; dir++) {

                int newRowIdx = currentNode.rowIdx + deltaX[dir];
                int newColIdx = currentNode.colIdx + deltaY[dir];

                // 범위 확인
                if (newRowIdx < 0 || newRowIdx >= size || newColIdx < 0 || newColIdx >= size) {
                    continue;
                }

                // 새로 구한 비용
                int newCost = costMap[currentNode.rowIdx][currentNode.colIdx] + cave[newRowIdx][newColIdx];

                // 다음 위치에 저장된 비용과 새로 구한 비용을 비교
                if (costMap[newRowIdx][newColIdx] > newCost) {
                    costMap[newRowIdx][newColIdx] = newCost;
                    pQueue.offer(new Node(newRowIdx, newColIdx, newCost));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        while (true) {
            size = Integer.parseInt(br.readLine().trim());

            int end = size - 1;

            if (size == 0) {
                break;
            }

            cave = new int[size][size];
            costMap = new int[size][size];

            // 2차원 배열 입력받기
            for (int rowIdx = 0; rowIdx < size; rowIdx++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int colIdx = 0; colIdx < size; colIdx++) {
                    cave[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
                }
                Arrays.fill(costMap[rowIdx], INF);
            }

            dijkstra();

            sb.append(String.format(MSG + "%d: %d\n", ++problemNumber, costMap[end][end]));

        }

        System.out.println(sb);
    }
}