package boj;

import java.io.*;
import java.util.*;

public class problem2644 {
    /*
     * 부모와 자식 사이를 1촌으로 사람들 간의 촌수를 계산한다.
     * 
     * 나 - 할아버지 : 2촌
     * 나 - 아버지형제 : 3촌
     * - 나 - 아버지 - 할아버지 - 아버지형제
     * 
     * 부모 자식들 간의 관계가 주어졌을 때 주어진 두 사람의 촌수를 계산하라
     * 
     * 사람은 최대 100명
     * 
     * 그래프
     * bfs
     */

    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int numOfPerson;
    static int numOfRelation;
    static int start, end;
    static Node[] graph;

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        numOfPerson = Integer.parseInt(br.readLine().trim());

        graph = new Node[numOfPerson + 1];

        st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        numOfRelation = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < numOfRelation; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from] = new Node(to, graph[from]);
            graph[to] = new Node(from, graph[to]);
        }
    }

    static void solution() {

        Deque<Integer> q = new ArrayDeque<>();

        boolean[] visited = new boolean[numOfPerson + 1];

        q.addLast(start);
        q.addLast(-1);
        visited[start] = true;

        while (!q.isEmpty()) {
            int nodeIdx = q.pollFirst();

            if(nodeIdx == end){
                break;
            }

            // 사이클 체크
            if (nodeIdx == -1) {

                // 모두 확인했음에도 정답이 없는 경우이다.
                if(q.isEmpty()){
                    answer = -1;
                    break;
                }

                answer++;
                q.addLast(-1);
                continue;
            }

            for (Node node = graph[nodeIdx]; node != null; node = node.next) {
                // 방문한 배열은 통과
                if (visited[node.to]) {
                    continue;
                }

                q.addLast(node.to);
                visited[node.to] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
