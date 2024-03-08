package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class problem1767 {
    /*
     * 
     * 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합을 구하고자 한다.
     * 단, 여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값을 구하라.
     * 
     * 맵에 코어의 위치가 주어진다.
     * 
     * 최대한 많은 core에 전원을 연결해도 전원이 연결되지 않은 core가 존재할 수 있다.
     * 
     * 코어에 연결되는 전선은 교차하면 안된다.
     * 영역에 가장자리에 위치한 코어는 이미 전원이 연결되어 있다.
     * 
     * core는 영역에 가장자리로 전선을 연결하여 전원을 공급받는다.
     * 
     * 
     * 1. 가장자리에 위치한 코어를 제외한 코어들로 선택할 수 있는 부분집합을 만든다. -> 가장 자리 코어는 무조건 연결
     * 2. 만든 부분집합으로 각각의 코어들이 4방향으로 진행했을 때 전원의 연결 여부와 연결에 필요한 케이블 수를 계산한다.
     * 2-1. 확인이 완료된 코어를 초기화해주어야 한다.
     * 3. 모든 코어에 대해 확인이 완료되면 연결된 코어 수와 이 때에 사용된 케이블 수를 저장한다.
     * 4. 모든 부분집합에 대해서 반복한다.
     * 4-1. 모든 부분집합에 대해 코어 연결가능 여부를 확인을 하게 되면 시간초과가 발생한다.
     * -> 현재 선택된 코어의 수보다 저장된 최대연결 코어수가 많으면 이때는 코어 연결 수가 적기 때문에 정답으로 선택될 수 없다.
     * -> 확인하지 않아도 된다.
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int mapSize;
    static int[][] map;

    static int maxCore;
    static int maxCable;

    static final int BLANK = 0;
    static final int CORE = 1;

    static boolean[] selectList;
    static int coreCount;

    static List<Core> coreList;

    // 우,하,좌,상
    static int[] deltaX = { 0, 1, 0, -1 };
    static int[] deltaY = { 1, 0, -1, 0 };

    static class Core {
        int rowIdx;
        int colIdx;

        public Core(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }
    }

    public static void inputTestCase() throws IOException {

        maxCore = 0;
        maxCable = 0;

        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];

        coreList = new ArrayList<>();
        coreCount = 0;

        // 프로세서 입력받기
        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                int value = Integer.parseInt(st.nextToken());
                map[rowIdx][colIdx] = value;

                // 코어인경우 갯수 갱신
                // 코어위치 저장
                if (value == CORE) {
                    // 가장자리에 있는 코어는 빼줌 -> 이미 연결되어 있기 때문에 고려하지 않아도 됨
                    if (rowIdx == 0 || colIdx == 0 || rowIdx == mapSize - 1 || colIdx == mapSize - 1) {
                        continue;
                    }

                    coreList.add(new Core(rowIdx, colIdx));
                    coreCount++;
                }
            }
        }
    }

    public static void selectCore(int selectIdx) {

        if (selectIdx == coreCount) {

            int selectCount = 0;
            // 현재 가능한 코어수보다 연결확인한 최대 코어수가 많을 때는 확인하지 않아도 된다.
            for (int idx = 0; idx < coreCount; idx++) {
                if (selectList[idx]) {
                    selectCount++;
                }
            }

            if (maxCore < selectCount) {
                // 선택된 코어로 전선 연결하기
                connectCore(0, 0, 0);
            }

            return;
        }

        // 코어를 선택하기 위한 부분집합
        selectList[selectIdx] = true;
        selectCore(selectIdx + 1);

        selectList[selectIdx] = false;
        selectCore(selectIdx + 1);

    }

    public static void connectCore(int coreSelectIdx, int connectCoreCount, int connectCableCount) {

        if (coreSelectIdx == coreCount) {

            // 연결한 코어수가 많을 때 연결 케이블 수
            if (maxCore < connectCoreCount) {
                maxCore = connectCoreCount; // 최대 연결 코어 수
                maxCable = connectCableCount; // 최대 사용 전선 수

                // 연결한 코어수가 같다면 연결에 사용한 전선 수가 적어야 한다.
            } else if (maxCore == connectCoreCount) {
                maxCable = Math.min(maxCable, connectCableCount);
            }

            return;

        }

        // 코어가 선택된 코어인지 확인
        if (selectList[coreSelectIdx]) {
            Core currentCore = coreList.get(coreSelectIdx);

            // 선택한 코어에 대해서 4방향을 선택하기
            for (int dir = 0; dir < deltaX.length; dir++) {
                int currentCableCount = 0;
                boolean flag = true;

                // System.out.println(currentCore.rowIdx + " : " + currentCore.colIdx);

                // 선택한 방향으로 전선을 연결할 때 다른 코어가 있다면 연결 불가
                int newRowIdx = currentCore.rowIdx + deltaX[dir];
                int newColIdx = currentCore.colIdx + deltaY[dir];

                while (true) {

                    // 다음 확인하는 곳이 영역보다 크지 않아야 하고
                    if (0 > newRowIdx || newRowIdx >= mapSize || 0 > newColIdx || newColIdx >= mapSize) {
                        break;
                    }

                    // 전선이 겹치면 안되므로
                    // 빈칸이어야 한다.
                    if (map[newRowIdx][newColIdx] == CORE) {
                        flag = false;
                        break;
                    }

                    map[newRowIdx][newColIdx] = CORE;
                    currentCableCount++;

                    newRowIdx = newRowIdx + deltaX[dir];
                    newColIdx = newColIdx + deltaY[dir];
                }

                // 연결한 케이블이 있을 때
                if (flag) {
                    // 연결 코어 수를 증가, 연결 케이블 수 추가해서 재귀호출
                    connectCore(coreSelectIdx + 1, connectCoreCount + 1, connectCableCount + currentCableCount);
                } else {
                    // 연결 코어 수, 연결 케이블 수 그대로 재귀호출
                    connectCore(coreSelectIdx + 1, connectCoreCount, connectCableCount);
                }

                // 확인이 완료되었으면 초기화
                newRowIdx = currentCore.rowIdx + deltaX[dir];
                newColIdx = currentCore.colIdx + deltaY[dir];

                // 위에서 새로 설정한 케이블만큼 다시 되돌려야 한다.
                for (int setCableCountIdx = 0; setCableCountIdx < currentCableCount; setCableCountIdx++) {
                    map[newRowIdx][newColIdx] = BLANK;

                    newRowIdx = newRowIdx + deltaX[dir];
                    newColIdx = newColIdx + deltaY[dir];

                }

            }
            // 선택한 코어가 아니라면 다음 코어 확인하러 간다.
        } else {
            connectCore(coreSelectIdx + 1, connectCoreCount, connectCableCount);
        }

    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {

            inputTestCase();

            selectList = new boolean[coreCount];

            selectCore(0);

            sb.append(String.format("#%d %d\n", tc, maxCable));

        }

        System.out.println(sb);
    }
}
