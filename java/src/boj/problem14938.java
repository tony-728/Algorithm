package boj;

import java.io.*;
import java.util.*;

public class problem14938 {
    /*
     * 양방향 통행이 가능한 그래프
     * 각 지역은 일정한 길이로 다른 지역과 연결되어 있다. 
     * 
     * 낙하한 지역을 중심으로 거리가 수색 범위 m이내의 모든 지역의 아이템을 습득 가능하다고 할 때 얻을 수 있는 아이템 갯수
     * 
     * 플루이드워셜
     */

    static int numOfNode;
    static int range;
    static int numOfEdge;

    static int[] arrOfItem;
    static int[][] distance;

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfNode = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        arrOfItem = new int[numOfNode + 1];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx <= numOfNode; idx++) {
            arrOfItem[idx] = Integer.parseInt(st.nextToken());
        }

        distance = new int[numOfNode + 1][numOfNode + 1];
        for (int idx = 0; idx < numOfNode + 1; idx++) {
            Arrays.fill(distance[idx], 987654321);
        }


        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            distance[from][to] = cost;
            distance[to][from] = cost;
        }
    }

    static void solution() {

        // 경유지
        for (int stopOver = 1; stopOver <= numOfNode; stopOver++) {
            // 출발지
            for (int start = 1; start <= numOfNode; start++) {

                // 출발지와 경유지가 같으면 통과
                if (start == stopOver) {
                    continue;
                }

                // 도착지
                for (int end = 1; end <= numOfNode; end++) {
                    distance[start][end] = Math.min(distance[start][end],
                            distance[start][stopOver] + distance[stopOver][end]);
                }
            }
        }

        // 최대 아이템 갯수 찾기
        for (int start = 1; start <= numOfNode; start++) {

            int sum = 0;

            // 도착지
            for (int end = 1; end <= numOfNode; end++) {
                if (distance[start][end] <= range || start == end) {
                    sum += arrOfItem[end];
                }
            }

            answer = Math.max(answer, sum);
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
