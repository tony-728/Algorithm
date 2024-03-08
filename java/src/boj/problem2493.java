package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class problem2493 {

    /*
     * 
     * N개의 탑들이 있고 각 탑의 높이가 주어져 있다.
     * 
     * 각 탑들은 왼쪽으로 레이저를 보낸다. 보낸 레이저를 처음 받는 탑의 번호를 출력한다.
     * 보낸레이저를 받는 탑은 레이저를 보낸 탑보다 높이가 같거나 높아야 한다.
     * 보낸 레이저를 받는 탑이 없으면 0으로 한다.
     * 
     * 타워의 높이를 저장하는 리스트
     * 현재 타워의 레이저를 수신하는 타워 인덱스를 저장하는 리스트
     * 
     * 인덱스가 1부터시작하고 있다.
     * 인덱스번호 관리를 쉽게 하기 위해 인덱스 0번에 최고 높이 타워보다 높은 타워를 넣어서
     * 0번타워가 어떤 타워도 수신할 수 있도록 관리함
     * 
     */

    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static int numOftower;
    public static int[] towerList;
    public static int tower;
    public static List<Integer> stack;
    public static int top;
    public static int[] sendIndexList;

    public static final int MAX_VALUE = 100_000_001;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        numOftower = Integer.parseInt(br.readLine().trim());

        towerList = new int[numOftower];
        sendIndexList = new int[numOftower + 1];

        st = new StringTokenizer(br.readLine().trim());
        stack = new ArrayList<>();

        top = 0;
        // 최고 높이 타워를 0번 인덱스에 넣어 관리를 용이하게 함
        stack.add(MAX_VALUE);

        for (int idx = 0; idx < numOftower; idx++) {
            tower = Integer.parseInt(st.nextToken());
            // 스택의 최상단에 값이 현재 타워보다 높으면 현재 타워는 top에 타워로 레이저 수신가능
            // top의 인덱스를 저장
            if (stack.get(top) >= tower) {
                sb.append(top).append(" ");
                sendIndexList[idx + 1] = top;
            } else { // 스택의 최상단에 값이 현재 타워보다 낮으면 현태 타워의 레이저를 수신할 수 있는 타워를 찾아감
                int sendIdx = sendIndexList[idx]; // 현재 인덱스의 타워를 수신하고 있는 타워의 인덱스
                while (sendIdx != 0) {
                    if (stack.get(sendIdx) >= tower) {
                        sb.append(sendIdx).append(" ");
                        sendIndexList[idx + 1] = sendIdx;
                        break;
                    }
                    // 현재 수신하고 있는 타워보다 높이가 높아서 그 타워를 수신하는 타워를 찾아감
                    // 최종적으로 0번 인덱스의 타워까지 올 수 도 있다.
                    sendIdx = sendIndexList[sendIdx];
                }
                if (sendIdx == 0) { // 최종적으로 0번 인덱스의 타워까지 오는 경우
                    sb.append(0).append(" ");
                    sendIndexList[idx] = 0;
                }
            }
            stack.add(tower);
            top++;
        }

        System.out.println(sb);
    }
}