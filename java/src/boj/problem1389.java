package boj;

import java.util.*;
import java.io.*;

public class problem1389 {
    /*
     * 
     * 임의의 두 사람이 최소 몇 단계 만에 이어질 수 있는지 계산하는 게임
     * 
     * 케빈 베이컨 게임을 했을 때 나오는 단계의 총 합이 가장 적은 사람
     * - 케빈 베이컨 수는 모든 사람과 케빈 베이컨 게임을 했을 때 나오는 단계의 합
     * 
     * 유저가 5명
     * 1, 3
     * 1, 4
     * 2, 3
     * 3, 4
     * 4, 5 가 친구일 때
     * 
     * 1일 때
     * 1 - 3 - 2
     * 1 - 3
     * 1 - 4
     * 1 - 4 - 5
     * 
     * 2 + 1 + 1 + 2 = 6
     * 
     * bfs
     * - bfs가 퍼지는 단계를 관리하면 된다.
     * - 모든 노드에 대해서 진행
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int max;
    static int answer;

    static int numOfUser;
    static int numOfRelation;
    static Node[] map;

    static class Node {
        int from;
        int to;
        Node next;

        public Node(int from, int to, Node next) {
            this.from = from;
            this.to = to;
            this.next = next;
        }

    }

    static void inputData() throws IOException {
        max = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine().trim());

        numOfUser = Integer.parseInt(st.nextToken());
        numOfRelation = Integer.parseInt(st.nextToken());

        map = new Node[numOfUser + 1];

        for (int idx = 0; idx < numOfRelation; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map[from] = new Node(from, to, map[from]);
            map[to] = new Node(to, from, map[to]);
        }
    }

    static void solution(int start) {
        Deque<Node> q = new ArrayDeque<>();
        int[] visited = new int[numOfUser + 1];

        Arrays.fill(visited, -1);

        int sum = 0;

        q.addLast(map[start]);
        visited[start] = 0;

        while (!q.isEmpty()) {

            Node startNode = q.pollFirst();
            int idx = startNode.from;

            for (Node node = startNode; node != null; node = node.next) {

                // 방문하지 않음
                if (visited[node.to] == -1) {
                    sum += visited[idx] + 1;
                    visited[node.to] = visited[idx] + 1;
                    q.addLast(map[node.to]);
                }
            }
        }

        if (sum < max) {
            max = sum;
            answer = start;
        }
    }


    public static void main(String[] args) throws IOException {
        inputData();

        for (int idx = 1; idx <= numOfUser; idx++) {
            solution(idx);
        }

        System.out.println(answer);
    }
}
