package boj;

import java.io.*;
import java.util.*;

public class problem1275 {
    /*
     * 
     * 구간합과 인덱스에 숫자를 수정한다.
     * 
     * 세그먼트 트리
     * 
     * 자료형
     * 세그먼트 트리에 입력되는 숫자는 int 범위이다.
     * - 구간합을 구할 때 int 범위를 넘을 수 있다. 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfNumber;
    static int numOfTurn;

    static long[] segmentTree;
    static int treeSize;

    static void initTree() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfNumber = Integer.parseInt(st.nextToken());
        numOfTurn = Integer.parseInt(st.nextToken());

        // 세그먼트 트리 초기화
        treeSize = (int) Math.ceil(Math.log(numOfNumber) / Math.log(2));
        segmentTree = new long[1 << (treeSize + 1)];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfNumber; idx++) {
            int value = Integer.parseInt(st.nextToken());

            // 리프노드에 입력데이터 초기화
            segmentTree[(1 << treeSize) + idx] = (long) value;
        }

        // 구간 합 구하기
        for (int idx = (1 << (treeSize + 1)) - 2; idx > 0; idx = idx - 2) {
            segmentTree[idx / 2] = segmentTree[idx] + segmentTree[idx + 1];
        }
    }

    static long getSum(int start, int end) {

        long sum = 0;

        // 세그먼트 트리 인덱스로 변환
        int leftIdx = (1 << treeSize) + (start - 1);
        int rightIdx = (1 << treeSize) + (end - 1);

        while (leftIdx <= rightIdx) {

            // left 선택한다.
            if (leftIdx % 2 == 1) {
                sum += segmentTree[leftIdx];
            }

            // 인덱스 이동
            leftIdx = (leftIdx + 1) / 2;

            // right 선택한다.
            if (rightIdx % 2 == 0) {
                sum += segmentTree[rightIdx];
            }

            // 인덱스 이동
            rightIdx = (rightIdx - 1) / 2;
        }

        return sum;
    }

    static void modify(int index, int value) {

        // 세그먼트 트리 인덱스로 변환
        int segIdx = (1 << treeSize) + (index - 1);

        // 기존 값과의 차이 계산
        long diff = value - segmentTree[segIdx];

        // 부모노드 갱신
        while (segIdx > 0) {
            segmentTree[segIdx] = segmentTree[segIdx] + diff;
            segIdx /= 2;
        }
    }

    static void query() throws IOException {

        for (int idx = 0; idx < numOfTurn; idx++) {

            // 구간 합
            st = new StringTokenizer(br.readLine().trim());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a < b가 보장되지 않음
            int start = Math.min(a, b);
            int end = Math.max(a, b);

            sb.append(getSum(start, end)).append("\n");

            // 수정
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            modify(a, b);
        }

    }

    public static void main(String[] agrs) throws IOException {
        initTree();

        query();

        System.out.println(sb);

    }
}
