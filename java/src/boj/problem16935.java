package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem16935 {

    /*
     * 
     * 1. 배열을 상하 반전
     * 2. 배열을 좌우 반전
     * 3. 오른쪽으로 90도 회전
     * 4. 왼쪽으로 90도 회전
     * 배열을 크기가 N/2 x M/2 인 4개의 부분 배열로 나누고 수행, 좌표평면으로 생각
     * 5. 1번 -> 2번 , 2번 -> 3번, 3번 -> 4번, 4번 -> 1번
     * 6. 1번 -> 4번 , 4번 -> 3번, 3번 -> 2번, 2번 -> 1번
     * 
     */

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;
    public static StringTokenizer st;
    public static int mapRowSize;
    public static int mapColSize;
    public static int numOfCommand;
    public static int command;

    public static int[][] map;
    public static int[] temp;

    public static int[][][] subMap; // 5,6 연산 시에 사용

    // 배열을 상하 반전
    public static void command1() {

        mapRowSize = map.length;
        mapColSize = map[0].length;

        int maxRowIdx = mapRowSize - 1;
        for (int Idx = 0; Idx < mapRowSize >> 1; Idx++) {
            int targetIdx = maxRowIdx - Idx;

            // 스위칭
            temp = map[Idx];
            map[Idx] = map[targetIdx];
            map[targetIdx] = temp;

        }

    }

    // 배열을 좌우 반전
    public static void command2() {
        mapRowSize = map.length;
        mapColSize = map[0].length;

        int[][] tempMap = new int[mapRowSize][mapColSize];

        int newRow = 0;
        int newCol = 0;

        for (int rowIdx = 0; rowIdx < mapRowSize; rowIdx++) {
            for (int colIdx = mapColSize - 1; colIdx > -1; colIdx--) {
                tempMap[newRow][newCol++] = map[rowIdx][colIdx];
            }
            newRow++;
            newCol = 0;
        }

        for (int rowIdx = 0; rowIdx < mapRowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapColSize; colIdx++) {
                map[rowIdx][colIdx] = tempMap[rowIdx][colIdx];
            }
        }

    }

    // 오른쪽으로 90도 회전
    public static void command3() {
        mapRowSize = map.length;
        mapColSize = map[0].length;

        int[][] tempMap = new int[mapColSize][mapRowSize];

        int newRow = 0;
        int newCol = 0;

        for (int colIdx = 0; colIdx < mapColSize; colIdx++) {
            for (int rowIdx = mapRowSize - 1; rowIdx > -1; rowIdx--) {
                tempMap[newRow][newCol++] = map[rowIdx][colIdx];
            }
            newRow++;
            newCol = 0;
        }

        map = new int[mapColSize][mapRowSize];

        for (int rowIdx = 0; rowIdx < mapColSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapRowSize; colIdx++) {
                map[rowIdx][colIdx] = tempMap[rowIdx][colIdx];
            }
        }

    }

    // 왼쪽으로 90도 회전
    public static void command4() {
        mapRowSize = map.length;
        mapColSize = map[0].length;

        int[][] tempMap = new int[mapColSize][mapRowSize];

        int newRow = 0;
        int newCol = 0;

        for (int colIdx = mapColSize - 1; colIdx > -1; colIdx--) {
            for (int rowIdx = 0; rowIdx < mapRowSize; rowIdx++) {
                tempMap[newRow][newCol++] = map[rowIdx][colIdx];
            }
            newRow++;
            newCol = 0;
        }

        map = new int[mapColSize][mapRowSize];

        for (int rowIdx = 0; rowIdx < mapColSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapRowSize; colIdx++) {
                map[rowIdx][colIdx] = tempMap[rowIdx][colIdx];
            }
        }
    }

    // 배열을 4구역을 나눔
    // 12
    // 34
    public static void makeSubMap(int[][] subMap, int startRowIdx, int startColIdx) {
        mapRowSize = map.length;
        mapColSize = map[0].length;

        int subRowIdx = 0;
        int subColIdx = 0;

        for (int rowIdx = startRowIdx; rowIdx < startRowIdx + (mapRowSize >> 1); rowIdx++) {
            for (int colIdx = startColIdx; colIdx < startColIdx + (mapColSize >> 1); colIdx++) {
                subMap[subRowIdx][subColIdx++] = map[rowIdx][colIdx];
            }
            subRowIdx++;
            subColIdx = 0;
        }
    }

    // 4개 그룹으로 나누어 오른쪽으로 90도 회전
    public static void command5() {
        mapRowSize = map.length;
        mapColSize = map[0].length;

        int[][] firstSubMap = new int[mapRowSize >> 1][mapColSize >> 1];
        int[][] secondSubMap = new int[mapRowSize >> 1][mapColSize >> 1];
        int[][] thirdSubMap = new int[mapRowSize >> 1][mapColSize >> 1];
        int[][] fourthSubMap = new int[mapRowSize >> 1][mapColSize >> 1];

        makeSubMap(firstSubMap, 0, 0);
        makeSubMap(secondSubMap, 0, mapColSize >> 1);
        makeSubMap(thirdSubMap, mapRowSize >> 1, 0);
        makeSubMap(fourthSubMap, mapRowSize >> 1, mapColSize >> 1);

        int subRowIdx = 0;
        int subColIdx = 0;
        // 1사분면
        for (int rowIdx = 0; rowIdx < mapRowSize >> 1; rowIdx++) {
            for (int colIdx = 0; colIdx < mapColSize >> 1; colIdx++) {
                map[rowIdx][colIdx] = thirdSubMap[subRowIdx][subColIdx++];
            }
            subRowIdx++;
            subColIdx = 0;
        }

        subRowIdx = 0;
        subColIdx = 0;
        // 2사분면
        for (int rowIdx = 0; rowIdx < mapRowSize >> 1; rowIdx++) {
            for (int colIdx = mapColSize >> 1; colIdx < mapColSize; colIdx++) {
                map[rowIdx][colIdx] = firstSubMap[subRowIdx][subColIdx++];
            }
            subRowIdx++;
            subColIdx = 0;
        }

        subRowIdx = 0;
        subColIdx = 0;
        // 3사분면
        for (int rowIdx = mapRowSize >> 1; rowIdx < mapRowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapColSize >> 1; colIdx++) {
                map[rowIdx][colIdx] = fourthSubMap[subRowIdx][subColIdx++];
            }
            subRowIdx++;
            subColIdx = 0;
        }

        subRowIdx = 0;
        subColIdx = 0;
        // 4사분면
        for (int rowIdx = mapRowSize >> 1; rowIdx < mapRowSize; rowIdx++) {
            for (int colIdx = mapColSize >> 1; colIdx < mapColSize; colIdx++) {
                map[rowIdx][colIdx] = secondSubMap[subRowIdx][subColIdx++];
            }
            subRowIdx++;
            subColIdx = 0;
        }

    }

    // 4개 그룹으로 나누어 왼쪽으로 90도 회전
    public static void command6() {
        mapRowSize = map.length;
        mapColSize = map[0].length;

        int[][] firstSubMap = new int[mapRowSize >> 1][mapColSize >> 1];
        int[][] secondSubMap = new int[mapRowSize >> 1][mapColSize >> 1];
        int[][] thirdSubMap = new int[mapRowSize >> 1][mapColSize >> 1];
        int[][] fourthSubMap = new int[mapRowSize >> 1][mapColSize >> 1];

        makeSubMap(firstSubMap, 0, 0);
        makeSubMap(secondSubMap, 0, mapColSize >> 1);
        makeSubMap(thirdSubMap, mapRowSize >> 1, 0);
        makeSubMap(fourthSubMap, mapRowSize >> 1, mapColSize >> 1);

        int subRowIdx = 0;
        int subColIdx = 0;

        // 1사분면
        for (int rowIdx = 0; rowIdx < mapRowSize >> 1; rowIdx++) {
            for (int colIdx = 0; colIdx < mapColSize >> 1; colIdx++) {
                map[rowIdx][colIdx] = secondSubMap[subRowIdx][subColIdx++];
            }
            subRowIdx++;
            subColIdx = 0;
        }

        subRowIdx = 0;
        subColIdx = 0;
        // 2사분면
        for (int rowIdx = 0; rowIdx < mapRowSize >> 1; rowIdx++) {
            for (int colIdx = mapColSize >> 1; colIdx < mapColSize; colIdx++) {
                map[rowIdx][colIdx] = fourthSubMap[subRowIdx][subColIdx++];
            }
            subRowIdx++;
            subColIdx = 0;
        }

        subRowIdx = 0;
        subColIdx = 0;
        // 3사분면
        for (int rowIdx = mapRowSize >> 1; rowIdx < mapRowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapColSize >> 1; colIdx++) {
                map[rowIdx][colIdx] = firstSubMap[subRowIdx][subColIdx++];
            }
            subRowIdx++;
            subColIdx = 0;
        }

        subRowIdx = 0;
        subColIdx = 0;
        // 4사분면
        for (int rowIdx = mapRowSize >> 1; rowIdx < mapRowSize; rowIdx++) {
            for (int colIdx = mapColSize >> 1; colIdx < mapColSize; colIdx++) {
                map[rowIdx][colIdx] = thirdSubMap[subRowIdx][subColIdx++];
            }
            subRowIdx++;
            subColIdx = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        mapRowSize = Integer.parseInt(st.nextToken());
        mapColSize = Integer.parseInt(st.nextToken());
        numOfCommand = Integer.parseInt(st.nextToken());

        map = new int[mapRowSize][mapColSize];

        // 2차원 배열 입력
        for (int rowIdx = 0; rowIdx < mapRowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < mapColSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int commandIdx = 0; commandIdx < numOfCommand; commandIdx++) {
            command = Integer.parseInt(st.nextToken());

            switch (command) {
                case 1:
                    command1();
                    break;
                case 2:
                    command2();
                    break;
                case 3:
                    command3();
                    break;
                case 4:
                    command4();
                    break;
                case 5:
                    command5();
                    break;
                case 6:
                    command6();
                    break;

            }
        }

        sb = new StringBuilder();
        for (int rowIdx = 0; rowIdx < map.length; rowIdx++) {
            for (int colIdx = 0; colIdx < map[0].length; colIdx++) {
                sb.append(map[rowIdx][colIdx]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}