package boj;

import java.io.*;
import java.util.*;

public class problem21921 {

    /*
     * x일 동안 가장 많이 들어온 방문자 수와 그 기간이 몇개 있는지
     * 
     * x일 동안을 기준으로 슬라이딩 윈도우로 계산
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int totalDay;
    static int duringDay;
    static int[] visitList;

    static int answer;
    static int maxVal;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        totalDay = Integer.parseInt(st.nextToken());
        duringDay = Integer.parseInt(st.nextToken());

        visitList = new int[totalDay];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < totalDay; idx++) {
            int day = Integer.parseInt(st.nextToken());

            visitList[idx] = day;
        }
    }

    static void solution() {
        int sum = 0;

        Deque<Integer> q = new ArrayDeque<>();

        for (int idx = 0; idx < duringDay; idx++) {
            int val = visitList[idx];
            sum += val;
            q.addLast(val);
        }

        maxVal = sum;
        answer++;

        for (int idx = duringDay; idx < totalDay; idx++) {
            if(q.isEmpty()){
                break;
            }

            int postVal = q.removeFirst();

            sum -= postVal;

            int currentVal = visitList[idx];

            sum += currentVal;

            q.addLast(currentVal);

            if (maxVal < sum) {
                answer = 1;
                maxVal = sum;
            } else if (maxVal == sum) {
                answer++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        if (maxVal == 0) {
            System.out.println("SAD");

        } else {
            System.out.println(maxVal);
            System.out.println(answer);
        }

    }
}
