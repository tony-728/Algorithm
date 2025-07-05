package boj;

import java.io.*;
import java.util.*;

public class problem2042 {
    /**
     * 구간합 구하기
     * 
     * 입력을 유의해서 받자
     * 
     * 세그먼트 트리
     */

    static int numOfNumber;
    static int numOfChange;
    static int numOfSum;
    static long[] arrOfNumber;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int treeSize;
    static long[] segmentTree;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfNumber = Integer.parseInt(st.nextToken());
        numOfChange = Integer.parseInt(st.nextToken());
        numOfSum = Integer.parseInt(st.nextToken());

        arrOfNumber = new long[numOfNumber];

        for (int idx = 0; idx < numOfNumber; idx++) {
            arrOfNumber[idx] = Long.parseLong(br.readLine().trim());
        }

        makeTree();
    }

    static void makeTree() {
        treeSize = (int) Math.ceil(Math.log(numOfNumber) / Math.log(2));

        segmentTree = new long[1 << (treeSize + 1)];

        int elementIdx = 1 << treeSize;

        // 리프노드 초기화
        for (int idx = 0; idx < numOfNumber; idx++) {
            segmentTree[idx + elementIdx] = arrOfNumber[idx];
        }

        // 구간합
        for (int idx = elementIdx - 1; idx > 0; idx--) {
            segmentTree[idx] = segmentTree[idx * 2] + segmentTree[idx * 2 + 1];
        }
    }

    static void update(int idx, long value) {

        int segIndex = idx + (1 << treeSize) - 1;

        long diff = value - segmentTree[segIndex] ;

        while (segIndex > 0) {
            segmentTree[segIndex] += diff;
            segIndex /= 2;
        }
    }

    static long sum(int start, int end) {

        long sum = 0;

        int treeStart = start + (1 << treeSize) - 1;
        int treeEnd = end + (1 << treeSize) - 1;

        while (treeStart <= treeEnd) {
            if (treeStart % 2 == 1) {
                sum += segmentTree[treeStart];
            }

            if (treeEnd % 2 == 0) {
                sum += segmentTree[treeEnd];
            }

            treeStart = (treeStart + 1) / 2;
            treeEnd = (treeEnd - 1) / 2;
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        inputData();

        for (int idx = 0; idx < numOfChange + numOfSum; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int command = Integer.parseInt(st.nextToken());

            switch (command) {
                // change
                case 1:
                    int index = Integer.parseInt(st.nextToken());
                    long value = Long.parseLong(st.nextToken());

                    update(index, value);
                    break;

                // sum
                case 2:
                    int start = Integer.parseInt(st.nextToken());
                    int end = Integer.parseInt(st.nextToken());

                    sb.append(sum(start, end)).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }
}
