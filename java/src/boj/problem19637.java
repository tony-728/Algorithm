package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem19637 {

    /*
     * 예를 들어
     * 10,000 이하 캐릭터는 WEAK
     * 10,000 <  <= 100,000 NORMAL
     * 100,000 <  <= 1,000,000 STRONG
     * 
     * 칭호의 개수
     * 칭호를 출력해야하는 캐릭터의 개수
     * 
     * 칭호의 이름,  조건
     * 
     * 캐릭터 점수
     * 
     * 각 캐릭터의 점수에 맞는 칭호를 출력
     * 
     * 이진탐색
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfTitle;
    static int numOfCharacter;

    static String[] titleList;
    static int[] pointList;

    static String title;
    static int point;

    static void checkCharacter(int point) {

        int start = 0;
        int last = numOfTitle;

        while (start <= last) {

            int mid = (start + last) / 2;

            if (point <= pointList[mid]) {
                last = mid - 1;

            } else {
                start = mid + 1;
            }
        }

        sb.append(titleList[start]).append("\n");

        return;
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine().trim());

        numOfTitle = Integer.parseInt(st.nextToken());
        numOfCharacter = Integer.parseInt(st.nextToken());

        titleList = new String[numOfTitle];
        pointList = new int[numOfTitle];

        for (int idx = 0; idx < numOfTitle; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            title = st.nextToken();
            point = Integer.parseInt(st.nextToken());

            titleList[idx] = title;
            pointList[idx] = point;
        }

        for (int idx = 0; idx < numOfCharacter; idx++) {
            point = Integer.parseInt(br.readLine().trim());

            checkCharacter(point);
        }

        System.out.print(sb);

    }
}
