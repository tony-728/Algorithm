package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem5431 {
    /*
     * 과목의 수강생이 N명이다
     * 민석이 처음으로 과제를 내었다
     * 제출한 사람의 목록을 받았다
     * 
     * 수강생들은 1번에서 N번가지 번호가 매겨져 있고 어떤 번호의 사람이 제출했는지에 대한 목록을 받은 것이다.
     * 과제를 제출하지 않는 사람의 번호를 오름차순으로 출력하는 프로그램을 작성하라
     * 
     * 첫줄에는 (수강생의 수, 과제를 제출한 사람의 수)
     * 둘째줄에는 과제를 제출한 사람의 번호 K개가 공백으로 주어진다. 같은 번호가 두 번 이상 주어지는 경우는 없다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int totalStudent;
    static int submitStudent;

    static boolean[] students;

    static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        totalStudent = Integer.parseInt(st.nextToken()); // 전체 수강생 수
        submitStudent = Integer.parseInt(st.nextToken()); // 제출한 수강생 수

        students = new boolean[totalStudent + 1]; // 수강생번호가 1번부터 시작되므로 하나 더 줌

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < submitStudent; idx++) {
            students[Integer.parseInt(st.nextToken())] = true;
        }
    }

    static void checkStudent() {
        for (int idx = 1; idx <= totalStudent; idx++) {
            if (!students[idx]) {
                sb.append(idx).append(" ");
            }
        }
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            sb.append("#").append(tc).append(" ");
            checkStudent();
        }

        System.out.println(sb);
    }
}