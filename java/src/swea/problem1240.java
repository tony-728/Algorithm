package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class problem1240 {
    /*
     * 암호코드는 8개의 숫자로 이루어져 있다.
     * 
     * 암호코드에서 숫자 하나는 7개의 비트로 암호화되어 주어진다. -> 암호코드의 가로 길이는 56이다.
     * 
     * 올바른 암호코드 = 홀수 자리의 합 * 3 + 짝수 자리의 합 이 10의 배수가 되어야 한다.
     * 
     * 
     * NxM 크기로 직사각형으로 입력이 주어진다.
     * 암호화된 코드가 아닌 곳은 모두 0으로 주어진다.
     * 
     *  0111011 0110001 0111011 0110001 0110001 0001101 0010011 0111011
     * 
     * 암호화된 코드의 마지막은 무조건 1이므로 주어진 입력의 뒤에서 부터 확인해서 1이 등장하는지 확인한다.
     * - 1이 등장하면 해당 열에 코드를 탐색
     * - 7자리씩 끊어서 코드를 만들고 코드가 올바른지 판단한다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int ENCODEBIT_LENGTH = 7;
    static final int ANSWER_LENGTH = 8;

    static int testCase;
    static int numOfRow;
    static int numOfCol;
    static int[][] encodeMap;

    static Deque<Integer> answerQueue;
    static int answer;

    static final int ZERO = 1 + 4 + 8;
    static final int ONE = 1 + 8 + 16;
    static final int TWO = 1 + 2 + 16;
    static final int THREE = 1 + 4 + 8 + 16 + 32;
    static final int FOUR = 1 + 2 + 32;
    static final int FIVE = 1 + 16 + 32;
    static final int SIX = 1 + 2 + 4 + 8 + 32;
    static final int SEVEN = 1 + 2 + 8 + 16 + 32;
    static final int EIGHT = 1 + 2 + 4 + 16 + 32;
    static final int NINE = 1 + 2 + 8;

    static final int[] codeList = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};

    static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfRow = Integer.parseInt(st.nextToken());
        numOfCol = Integer.parseInt(st.nextToken());

        encodeMap = new int[numOfRow][numOfCol];

        for (int rowIdx = 0; rowIdx < numOfRow; rowIdx++) {
            String row = br.readLine().trim();
            for (int colIdx = 0; colIdx < numOfCol; colIdx++) {
                encodeMap[rowIdx][colIdx] = row.charAt(colIdx) - '0';
            }
        }

        answerQueue = new ArrayDeque<Integer>();
        answer = 0;
    }

    static void decode(int rowIdx, int colIdx) {
        int idx = colIdx;
        int bitCount = 0; // 암호화 코드의 자리에 맞는 비트를 넣어주기 위함
        int count = 0; // 암호코드 7개를 모두 확인했는지 확인
        int value = 0; // 암호코드가 가리키는 값

        while (true) {

            if (encodeMap[rowIdx][idx] == 1) {
                value += 1 << bitCount;
            }

            bitCount++;
            count++;
            idx--;

            if (count % ENCODEBIT_LENGTH == 0) {
                for (int codeIdx = 0; codeIdx < codeList.length; codeIdx++) {
                    if ((value == codeList[codeIdx])) {
                        // 코드를 뒤에서 부터 확인하므로 stack으로 넣어준다.
                        // 암호 코드가 가리키는 값과 같으면 암호코드의 index를 넣는다.
                        answerQueue.addFirst(codeIdx);
                    }

                }

                // 코드 확인이 완료되었으므로 초기화
                value = 0;
                bitCount = 0;
            }

            // 모든 비트를 확인했으면 종료
            if (count == (ENCODEBIT_LENGTH * ANSWER_LENGTH)) {
                break;
            }
        }


        // 암호 코드 검증
        int answerIdx = 1;
        int check = 0;
        while (!answerQueue.isEmpty()) {
            int val = answerQueue.pop();
            answer += val;

            if (answerIdx % 2 == 0) {
                check += val;
            } else {
                check += val * 3;
            }

            answerIdx++;
        }

        if (check % 10 == 0) {
            sb.append(answer).append("\n");
        } else {
            sb.append(0).append("\n");
        }
    }

    static void checkMap() {
        for (int rowIdx = 0; rowIdx < numOfRow; rowIdx++) {
            // 뒤에서부터 확인한다.
            for (int colIdx = numOfCol - 1; colIdx > -1; colIdx--) {
                // 1이 등장하면 코드 복호화 시작
                if (encodeMap[rowIdx][colIdx] == 1) {
                    decode(rowIdx, colIdx);
                    return;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");

            inputTestCase();

            checkMap();
        }

        System.out.println(sb);

    }
}
