package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem13300 {
    /*
     * 남학생은 남학생끼리 
     * 여학생은 여학생끼리
     * 
     * 한 방에는 같은 학년의 학생들을 배정해야한다.
     * - 한 방에 한 명만 배정하는 것도 가능하다
     * 
     * 한방에 배정할 수 있는 최대 인원 수 k가 주어졌을 때 
     * 조건에 맞게 모든 학생을 배정하기 위해 필요한 방의 최소 개수
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int numOfStudent;
    static int maxStudent;
    static int answer;

    static int[][] students = new int[7][2];

    static final int MAN = 1;
    static final int WOMAN = 0;

    static void inputCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfStudent = Integer.parseInt(st.nextToken());
        maxStudent = Integer.parseInt(st.nextToken());

        for (int stuIdx = 0; stuIdx < numOfStudent; stuIdx++) {

            st = new StringTokenizer(br.readLine().trim());
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());

            students[grade][gender]++;
        }
    }

    public static void main(String[] args) throws IOException {

        inputCase();

        for (int gradeIdx = 1; gradeIdx <= 6; gradeIdx++) {
            for (int genderIdx = 0; genderIdx < 2; genderIdx++) {
                int count = students[gradeIdx][genderIdx];

                if (count == 0) {
                    continue;
                }

                answer += count / maxStudent;
                answer += count % maxStudent > 0 ? 1 : 0;
            }
        }

        System.out.println(answer);


    }
}
