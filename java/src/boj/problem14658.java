package boj;

import java.util.*;
import java.io.*;

public class problem14658 {
    /*
     * 지표면에 떨어지는 별동별의 수를 최소화해야 한다.
     * LxL 트램펄린
     * - 별동별이 어디로 떨어질지 알고 있다.
     * 
     * 트랜펄린을 배치했을 때 지구에 떨어지는 별동별의 개수
     * - 별동별은 위치가 겹치지 않는다.
     * - 트램펄린 모서리에 맞아도 튕겨나간다.
     * - 트램펄린을 비스듬하게 배치할 수 없다.
     * 
     * 모든 별동별들에 대해서 확인해야한다.
     * - 별동별은 최대가 100개
     * 
     */

    static class Star {
        int colIdx;
        int rowIdx;

        public Star(int colIdx, int rowIdx) {
            this.colIdx = colIdx;
            this.rowIdx = rowIdx;
        }
    }


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int trapSize;
    static int numOfStar;

    static Star[] starList;

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        trapSize = Integer.parseInt(st.nextToken());
        numOfStar = Integer.parseInt(st.nextToken());

        starList = new Star[numOfStar];

        for (int idx = 0; idx < numOfStar; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int colIdx = Integer.parseInt(st.nextToken());
            int rowIdx = Integer.parseInt(st.nextToken());

            starList[idx] = new Star(colIdx, rowIdx);
        }
    }

    static void solution() {

        // 별이 100개라서 3중 반복문도 가능
        // 중복을 포함해서 두 별에 x좌표, y좌표에 대해서 트랩을 그렸을 때 별이 얼마나 포함되는지 확인한다.
        for (Star left : starList) {
            for (Star right : starList) {

                int leftRowIdx = left.rowIdx;
                int rightColIdx = right.colIdx;

                int count = 0;
                for (Star check : starList) {
                    if (leftRowIdx <= check.rowIdx && check.rowIdx <= leftRowIdx + trapSize
                            && rightColIdx <= check.colIdx
                            && check.colIdx <= rightColIdx + trapSize) {
                        count++;
                    }
                }

                answer = Math.max(answer, count);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(numOfStar - answer);
    }
}
