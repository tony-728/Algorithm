package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10971 {

    /*
     * 
     * 1번부터 N번까지 번호가 매겨진 도시가 있다.
     * 
     * 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 도시로 돌아오는 순회 여행 경로를
     * 계획한다.
     * 한 번 갔던 도시로는 다시 갈 수 없다.
     * 여행 비용을 가장 적게 한다.
     * 
     * 행렬로 각 도시간에 이동하는데 드는 비용이 주어진다.
     * 방향 그래프이다.
     * 
     * 
     * 1. 도시로 만들 수 있는 순열을 만든다.
     * 2. 만든 경로로 이동할 수 있는지 확인한다.
     * 2-1. 이동가능할 때는 비용을 더해 총 비용을 계산한다.
     * 2-2. 이동이 불가능할 때는 확인을 멈춘다.
     * 
     * 비용들 중에서 가장 적게 걸린 비용을 찾는다.
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfCity;
    static int[][] graph;

    static int[] selectList;
    static boolean[] usedElementList;

    static int answer;

    public static void inputTestCase() throws IOException {
        answer = Integer.MAX_VALUE;
        numOfCity = Integer.parseInt(br.readLine().trim());

        graph = new int[numOfCity][numOfCity];

        // 그래프 입력받기
        for (int rowIdx = 0; rowIdx < numOfCity; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < numOfCity; colIdx++) {
                graph[rowIdx][colIdx] = Integer.parseInt(st.nextToken());

            }
        }

    }

    public static void searchPath(int selectIdx) {
        // N개의 도시로 순열을 만든다.

        if (selectIdx == numOfCity) {
            // 만든 경로로 이동이 가능한지 확인하고
            int cost = 0;
            boolean flag = true;

            for (int idx = 0; idx < numOfCity; idx++) {
                // 경로로 이동이 가능하다면 필요한 비용이 얼마인지 계산한다.

                // 마지막 여행지에서 시작위치로 돌아올 때
                if (idx == numOfCity - 1) {
                    // 갈 수가 없는 경우
                    if (graph[selectList[idx]][selectList[0]] == 0) {
                        flag = false;
                        break;
                    }
                    cost += graph[selectList[idx]][selectList[0]];
                } else {
                    // 갈 수가 없는 경우
                    if (graph[selectList[idx]][selectList[idx + 1]] == 0) {
                        flag = false;
                        break;
                    }
                    cost += graph[selectList[idx]][selectList[idx + 1]];
                }
            }

            // 갈 수 없는 경로가 있는 경우에는 비용확인을 하지 않는다.
            if (flag) {
                answer = Math.min(answer, cost);
            }

            return;
        }

        // 순열로 경로 만들기
        for (int elementIdx = 0; elementIdx < numOfCity; elementIdx++) {
            if (usedElementList[elementIdx]) {
                continue;
            }

            usedElementList[elementIdx] = true;
            selectList[selectIdx] = elementIdx;

            searchPath(selectIdx + 1);

            usedElementList[elementIdx] = false;

        }

    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        selectList = new int[numOfCity];
        usedElementList = new boolean[numOfCity];
        searchPath(0);

        System.out.println(answer);
    }
}
