package boj;

import java.io.*;
import java.util.*;

public class problem1107 {
    /*
     * 버튼이 0-9, +, -이 있다.
     * - +: 현재 채널에서 +1
     * - -: 현태 체널에서 -1
     * - 채널 0에서 -를 누른 경우 채널이 변하지 않는다.
     * - 채널은 무한대만큼 있다.
     * 
     * 이동하려는 채널은 N이다.
     * 어떤 버튼이 고장났는지 주어졌을 때 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야 하는지 구하라
     * 
     * 현재 채널은 100이다.
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int targetChannel;
    static int numOfButton;
    static boolean[] buttonList = new boolean[10];

    static int answer;
    static int[] answerList;

    static final int CURRENT_CHANNEL = 100;

    static final char ZERO = '0';

    static void inputData() throws IOException {
        answer = 0;

        String channel = br.readLine().trim();
        targetChannel = Integer.parseInt(channel);
        numOfButton = Integer.parseInt(br.readLine().trim());

        if (numOfButton > 0) {
            st = new StringTokenizer(br.readLine().trim());
        }

        // 누를 수 없는 버튼은 true
        for (int idx = 0; idx < numOfButton; idx++) {
            int button = Integer.parseInt(st.nextToken());
            buttonList[button] = true;
        }
    }

    static void solution() {

        //그냥 0부터 999999까지 전부 확인한다.
        for (int button = 0; button <= 999999; button++) {
            String channel = String.valueOf(button);

            boolean isBreak = false;
            for (int j = 0; j < channel.length(); j++) {
                if (buttonList[channel.charAt(j) - ZERO]) {
                    //고장난 버튼을 눌러야 하면 멈춘다.
                    isBreak = true;
                    break;
                }
            }

            if (!isBreak) {
                //버튼을 누르는 횟수 중 가장 적은 횟수를 result에 담는다.
                int min = Math.abs(targetChannel - button) + channel.length();
                answer = Math.min(min, answer);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        if (targetChannel != CURRENT_CHANNEL) {
            // 현재 위치에서 이동으로만 목표 채널에 가는 횟수
            answer = Math.abs(targetChannel - CURRENT_CHANNEL);
            solution();
        }

        System.out.println(answer);
    }
}
