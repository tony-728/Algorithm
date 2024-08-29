package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem1251 {
    /*
     * 모든 섬을 연결해야 한다.
     * 간선의 가중치
     * - E * L^2 만큼이 필요하다.
     * - E 가중치 상수, L 두 섬의 거리
     * long으로 처리해야함
     * 
     * testcase
     * 섬의 개수
     * 각 섬의 x좌표
     * 각 섬의 y좌표
     * 가중치 상수
     * 
     * 1. 섬의 정보를 입력받는다.
     * 2. 각 섬끼리의 거리를 모두 구한다.
     * 3. 거리를 기준으로 오름차순한다.
     * 4. 최소 신장 트리를 만든다.
     * 
     */

    static class Land {

        int rowIdx;
        int colIdx;

        public Land(int rowIdx) {
            this.rowIdx = rowIdx;
        }

        public void setColIdx(int colIdx) {
            this.colIdx = colIdx;
        }

        @Override
        public String toString() {
            return "Land [rowIdx=" + rowIdx + ", colIdx=" + colIdx + "]";
        }


    }

    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
        }


    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfLand;
    static double taxRate;
    static double answer;

    static boolean[] visited;

    static int numOfEdge;
    static int[] parentsList;

    static Land[] landList;
    static Edge[] edgeList;

    public static double calDistance(Land leftLand, Land rightLand) {

        return (Math.pow((leftLand.rowIdx - rightLand.rowIdx), 2)
                + Math.pow((leftLand.colIdx - rightLand.colIdx), 2)) * taxRate;
    }

    public static void makeRoad() {

        parentsList = new int[numOfLand];

        for (int idx = 0; idx < numOfLand; idx++) {
            parentsList[idx] = idx;

        }
    }

    public static int findRoad(int landIdx) {

        if (parentsList[landIdx] == landIdx) {
            return landIdx;
        }

        return parentsList[landIdx] = findRoad(parentsList[landIdx]);
    }

    public static boolean unionRoad(int leftLandIdx, int rightLandIdx) {

        int leftLandRoot = findRoad(leftLandIdx);
        int rightLandRoot = findRoad(rightLandIdx);

        // 같은 섬이다.
        if (leftLandRoot == rightLandRoot) {
            return false;
        }

        parentsList[rightLandRoot] = leftLandRoot;

        return true;
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            answer = 0;
            numOfLand = Integer.parseInt(br.readLine().trim());

            landList = new Land[numOfLand];

            // 섬 정보 입력받기
            for (int idx = 0; idx < 2; idx++) {
                st = new StringTokenizer(br.readLine().trim());

                for (int landIdx = 0; landIdx < numOfLand; landIdx++) {
                    if (idx == 0) {
                        landList[landIdx] = new Land(Integer.parseInt(st.nextToken()));
                    } else {
                        landList[landIdx].setColIdx(Integer.parseInt(st.nextToken()));
                    }
                }
            }

            taxRate = Double.parseDouble(br.readLine().trim());

            numOfEdge = (numOfLand * (numOfLand - 1)) / 2;
            edgeList = new Edge[numOfEdge];

            // 각 섬들의 거리를 모두 구한다.
            int edgeIdx = 0;
            for (int startLandIdx = 0; startLandIdx < numOfLand - 1; startLandIdx++) {
                for (int endLandIdx = startLandIdx + 1; endLandIdx < numOfLand; endLandIdx++) {

                    Land startLand = landList[startLandIdx];
                    Land endLand = landList[endLandIdx];

                    edgeList[edgeIdx++] =
                            new Edge(startLandIdx, endLandIdx, calDistance(startLand, endLand));
                }
            }

            // 거리기준으로 오름차순
            Arrays.sort(edgeList);

            makeRoad();

            // MST 만들기
            int edgeCount = 0;

            for (Edge edge : edgeList) {

                if (unionRoad(edge.from, edge.to)) {
                    answer += edge.weight;

                    if (++edgeCount == numOfLand - 1) {
                        break;
                    }
                }
            }

            answer = Math.round(answer);
            sb.append(String.format("#%d %.0f\n", tc, answer));
        }

        System.out.println(sb);
    }

}
