package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem19941 {
    /*
     * 벤치 모양의 식탁
     * 사람들과 햄버거가 단위 간격으로 놓여있다.
     * 사람들은 자신의 위치에서 거리가 K 이하인 햄버거를 먹을 수 있다.
     * 
     * 식탁의 길이, 햄버거를 선택할 수 있는 길이,k가 주어졌을 때
     * 햄버거를 먹을 수 있는 사람의 최대 수를 구하라
     * 
     * 좌우로 k 만큼을 확인해서 체크한다음 먹는다. -> 이때가 최대값일까
     * 재귀함수로 모든 경우에 수를 확인한다. -> 일반 재귀는 시간초과
     * 가지치기를 할 수 있는 것을 추가해야함 -> 시간초과
     * 
     * 그리디한 풀이가 정답 -> 너무 어렵게 생각했음
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final char PERSON = 'P';
    static final char HAMBURGER = 'H';

    static int answer;

    static int tableSize;
    static int armSize;

    static char[] table;
    static boolean[] checkList;

    static void inputTestCase() throws IOException {
        answer = 0;
        st = new StringTokenizer(br.readLine().trim());

        tableSize = Integer.parseInt(st.nextToken());
        armSize = Integer.parseInt(st.nextToken());

        table = new char[tableSize];
        checkList = new boolean[tableSize];

        String inputData = br.readLine().trim();

        for (int idx = 0; idx < tableSize; idx++) {
            char value = inputData.charAt(idx);
            table[idx] = value;
        }
    }

    // 너무 어렵게 생각한 풀이였음
    static void eatBurger(int index, int count) {

        // 마지막 인덱스면 최대값 확인
        if (index == tableSize) {
            answer = Math.max(answer, count);
            return;
        }

        // 현재 위치가 햄버거이면 다음 인덱스로 이동
        if (table[index] == HAMBURGER) {
            eatBurger(index + 1, count);

            // 현재 위치가 사람이면 햄버거를 먹을 수 있는지 확인한 후 이동
        } else if (table[index] == PERSON) {
            for (int dist = -armSize; dist < armSize + 1; dist++) {
                // 인덱스 범위 확인
                if (index + dist < 0 || index + dist >= tableSize) {
                    continue;
                }

                // 먹을 수 있는 범위를 확인
                if (table[index + dist] == HAMBURGER && !checkList[index + dist]) {
                    checkList[index + dist] = true;

                    eatBurger(index + 1, count + 1);

                    checkList[index + dist] = false;

                }
            }
            eatBurger(index + 1, count);
        }
    }


    public static void main(String[] args) throws IOException {
        inputTestCase();

        for (int idx = 0; idx < tableSize; idx++) {
            if (table[idx] == PERSON) {
                for (int hamIdx = -armSize; hamIdx < armSize + 1; hamIdx++) {
                    if (idx + hamIdx < 0 || idx + hamIdx >= tableSize) {
                        continue;
                    }

                    if (table[idx + hamIdx] == HAMBURGER && !checkList[idx + hamIdx]) {
                        checkList[idx + hamIdx] = true;
                        answer++;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);

    }
}
