package boj;

import java.io.*;
import java.util.*;

public class problem2357 {
    /*
     * N(최대 10만)개의 정수가 있을 때
     * - a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수, 제일 큰 정수를 찾는 일
     * - a, b의 쌍(최대 10만 쌍)이 주어졌을 때 문제를 해결하자
     * 
     * a번째라는 것 입력되는 순서로 a번째
     * - a=1, b=3 입력된 순서대로 1번, 2번, 3번 정수 중에서 최소, 최대값을 찾아야 한다.
     * - 각 정수는 1이상 10억이하의 값
     * 
     * maxDp[][], minDp[][]로 구간 별 최대, 최소를 저장 -> 메모리 초과
     * dp[][] 하나로 구간 별 최대, 최소를 저장 -> 메모리 초과
     * 당연하다 10만짜리 2차원 배열을 어떻게 만드냐
     * 
     * 세그먼트 트리
     * 
     * 
     */



    static StringBuilder sb = new StringBuilder();

    static int numOfInteger;
    static int numOfPair;
    static int[] arrOfNumber;

    static int[] maxTree;
    static int[] minTree;
    static int treeSize;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfInteger = Integer.parseInt(st.nextToken());
        numOfPair = Integer.parseInt(st.nextToken());

        arrOfNumber = new int[numOfInteger + 1];

        for (int idx = 0; idx < numOfInteger; idx++) {
            arrOfNumber[idx] = Integer.parseInt(br.readLine().trim());
        }

        treeSize = (int) Math.ceil(Math.log(numOfInteger) / Math.log(2));

        maxTree = new int[1 << (treeSize + 1)];
        minTree = new int[1 << (treeSize + 1)];

        makeSegmentTree();

        for (int idx = 0; idx < numOfPair; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int min = findMin(start, end);
            int max = findMax(start, end);

            sb.append(min).append(" ").append(max).append("\n");
        }
    }

    static void makeSegmentTree() {
        // 리프노드 초기화
        int elementIdx = 1 << treeSize;

        for (int idx = 0; idx < numOfInteger; idx++) {
            maxTree[idx + elementIdx] = arrOfNumber[idx];
            minTree[idx + elementIdx] = arrOfNumber[idx];
        }

        // 부모노드 초기화
        for (int idx = elementIdx - 1; idx > 0; idx--) {
            maxTree[idx] = Math.max(maxTree[idx * 2], maxTree[idx * 2 + 1]);
            minTree[idx] = Math.min(minTree[idx * 2], minTree[idx * 2 + 1]);
        }
    }

    static int findMax(int start, int end) {

        int treeStart = start + (1 << treeSize) - 1;
        int treeEnd = end + (1 << treeSize) - 1;

        int result = 0;

        while (treeStart <= treeEnd) {

            if (treeStart % 2 == 1) {
                result = Math.max(result, maxTree[treeStart]);
            }

            if (treeEnd % 2 == 0) {
                result = Math.max(result, maxTree[treeEnd]);
            }

            treeStart = (treeStart + 1) / 2;
            treeEnd = (treeEnd - 1) / 2;
        }

        return result;
    }

    static int findMin(int start, int end) {
        int treeStart = start + (1 << treeSize) - 1;
        int treeEnd = end + (1 << treeSize) - 1;

        int result = Integer.MAX_VALUE;

        while (treeStart <= treeEnd) {

            // 시작 인덱스를 선택할 때
            if (treeStart % 2 == 1) {
                result = Math.min(result, minTree[treeStart]);
            }

            // 마지막 인덱스를 선택할 때
            if (treeEnd % 2 == 0) {
                result = Math.min(result, minTree[treeEnd]);
            }

            // 부모 노드로 변경
            treeStart = (treeStart + 1) / 2;
            treeEnd = (treeEnd - 1) / 2;
        }

        return result;
    }


    public static void main(String[] args) throws IOException {

        inputData();

        System.out.println(sb);
    }
}
