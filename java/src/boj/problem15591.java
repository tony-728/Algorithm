package boj;

import java.io.*;
import java.util.*;

public class problem15591 {
    /*
     * 1-N까지 번호가 붙여진 동영상
     * 
     * 두 동영상이 서로 얼마나 가까운 지를 측정하는 단위인 USADO를 만들었다.
     * - N-1개의 동영상 쌍을 골라서 두 쌍의 USADO를 계산했다.
     * 
     * 각 동영상을 정점으로 하는 네트워크 구축
     * - 어떤 동영상에서 다른 동영상으로 가는 경로가 반드시 존재한다.
     * - 임의의 두 쌍 사이의 동영상의 USADO를 그 경로의 모든 연결들의 USADO 중 최소값으로 하기로 했다.
     * 
     * 동영상에 대해 K 값을 정해서 USADO가 K이상인 모든 동영상이 추천된다.
     * 
     * 어떤 K값에 대한 추천 동영상의 개수를 구하라
     * 
     * 각 정점에 대해서 최소 비용을 구한 후 질문에 대한 답을 하려고 했다.
     * - dfs로 각 정점에 대해서 계산
     * - 시간 초과 발생
     * 
     * 미리구하는 것이 좋다고 생각했지만 질문에 답을 하기 위해 구하는 과정에서 최소비용이 만들어짐
     * 따라서 미리 계산할 필요가 없이 질문에 대해서 최소비용을 만들자
     * - 대신 이전에 계산을 한 정점이라면 다시 계산하지 않고 만들어진 그래프에서 카운트
     * - 이점이 있는 부분 질문이 모든 정점에 대해서 하는 것이 아닐 수 있기 때문에 모든 정점을 미리 구하는 것보다 나을 수 있다.
     * - 로직은 같은데 그래프를 구성하는 자료구조가 2차원 배열일 때는 시간초과 -> 인접리스트로 바꾸니까 통과함
     * 
     */

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

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfVideo;
    static int numOfQuestion;

    static int minimumUSADO;
    static int targetVideo;

    static Node[] network;
    static int[][] USADO;

    static boolean[] visited;
    static int start;
    static int globalCount;

    static final int MAX_VALUE = 1_000_000_001;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfVideo = Integer.parseInt(st.nextToken());
        numOfQuestion = Integer.parseInt(st.nextToken());

        network = new Node[numOfVideo + 1];
        USADO = new int[numOfVideo + 1][numOfVideo + 1];

        int p, q, r;

        for (int idx = 0; idx < numOfVideo - 1; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            network[p] = new Node(q, r, network[p]);
            network[q] = new Node(p, r, network[q]);

        }

        boolean[] USADOvisited = new boolean[numOfVideo + 1];

        for (int questionIdx = 0; questionIdx < numOfQuestion; questionIdx++) {
            st = new StringTokenizer(br.readLine().trim());

            minimumUSADO = Integer.parseInt(st.nextToken());
            targetVideo = Integer.parseInt(st.nextToken());

            if (USADOvisited[targetVideo]) {
                countVideo();
                continue;
            }

            USADOvisited[targetVideo] = true;

            visited = new boolean[numOfVideo + 1];
            visited[targetVideo] = true;
            globalCount = 0;

            start = targetVideo;
            makeMap(targetVideo);

            sb.append(globalCount).append("\n");
        }
    }

    static void makeMap(int nodeIdx) {

        Deque<int[]> q = new ArrayDeque<>();

        // 초기설정 node에 연결된 정점과 그 비용을 큐에 추가
        for (Node node = network[nodeIdx]; node != null; node = node.next) {
            visited[node.to] = true;
            q.addLast(new int[] {node.to, node.cost});

        }

        while (!q.isEmpty()) {

            int[] next = q.pollFirst();

            USADO[start][next[0]] = next[1];

            // 처음 방문한 정점까지의 비용이 K이상이라면 카운트
            if (next[1] >= minimumUSADO) {
                globalCount++;
            }

            // 이동한 정점에서 연결된 정점과 그 비용을 큐에 추가
            // 이전에 방문한 정점은 다시 방문하지 않는다.
            for (Node node = network[next[0]]; node != null; node = node.next) {
                if (visited[node.to]) {
                    continue;
                }

                visited[node.to] = true;

                // 현재까지의 최소비용과 다음으로 이동할 때 발생하는 비용 중 최소값으로 비용을 처리한다.
                q.addLast(new int[] {node.to, Math.min(next[1], node.cost)});
            }
        }

    }

    static void countVideo() {
        int count = 0;

        for (int idx = 1; idx <= numOfVideo; idx++) {
            if (USADO[targetVideo][idx] >= minimumUSADO) {
                count++;
            }
        }

        sb.append(count).append("\n");
    }

    public static void main(String[] args) throws IOException {
        inputData();

        System.out.println(sb);
    }
}
