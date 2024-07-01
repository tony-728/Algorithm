package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class problem5432 {
    /*
     * 쇠막대기는 자신보다 긴 쇠막대기 위에만 놓일 수 있다.
     * 쇠막대기를 다른 쇠막대기 위에 놓는 경우 완전히 포함되도록 놓되, 끝점은 겹치지 않도록 놓는다.
     * 각 쇠막대기를 자르는 레이저는 적어도 하나 존재한다.
     * 레이저는 어떤 쇠막대기의 양 끝점과도 겹치지 않는다
     * 
     * () 를 레이저로 변환한다.
     * 
     * 새로운 ( 가 등장할 때 전체 막대기 개수를 증가한다.
     * 레이저가 등장할 때 현재 막대기 개수만큼 정답에 더한다.
     * 새로운 ) 가 등장할 때 정답에 1을 더한다.
     * 
     */

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb;
    public static int testCase;
    public static Deque<Character> stickList;

    public static int answer;

    public static int totalStick;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();
        for (int tc = 1; tc <= testCase; tc++) {
            totalStick = 0;
            answer = 0;

            char[] tempList = br.readLine().trim().toCharArray();

            // 입력 문자열 입력받기
            // () -> L로 변환
            stickList = new ArrayDeque<>();
            for (int idx = 0; idx < tempList.length; idx++) {
                if (tempList[idx] == '(') {
                    stickList.add(tempList[idx]);
                } else if (tempList[idx] == ')') {
                    if (stickList.peekLast() == '(') { // () 모양이 만들어질 때
                        stickList.pollLast();
                        stickList.add('L');
                    } else {
                        stickList.add(tempList[idx]);
                    }
                }
            }


            for (Character x : stickList) {
                // 새로운 ( (막대기)가 등장할 때 전체 막대기 개수를 증가한다.
                if (x == '(') {
                    totalStick++;

                    // 레이저가 등장할 때 현재 막대기 개수만큼 정답에 더한다.
                    // 레이저는 막대기를 둘로 나누기 때문에 현재 막대기 개수만큼 막대기가 나눠진다.
                } else if (x == 'L') {
                    answer += totalStick;

                    // 새로운 ) (막대기 끝)가 등장할 때 정답에 1을 더한다.
                    // 막대기가 끝이므로 전체 막대기에서 빼주어야 한다.
                } else if (x == ')') {
                    answer++;
                    totalStick--;
                }
            }

            sb.append(String.format("#%d %d\n", tc, answer));
        }
        System.out.println(sb);
    }
}
