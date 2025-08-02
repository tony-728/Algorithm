package boj;

import java.io.*;
import java.util.*;

public class problem1094 {
    /**
     * 64cm 막대를 Xcm로 바꾸고 싶다.
     * 
     * 1. 갖고 있는 막대의 길이를 모두 더한다. 처음에는 64, 합이 x보다 크면 아래 과정을 반복
     * 1-1. 가지고 있는 막대 중 길이가 가장 짧은 것을 절반으로 자른다.
     * 1-2. 위에서 자른 막대의 절반 중 하나를 버리고 남아있는 막대의 길이의 합이 x보다 크거나 같다면 위에서 자른 막대의 절반 중 하나를 버린다.
     * 2. 남아 있는 모든 막대를 붙여 x로 만든다.
     * 
     * 위 과정을 거쳐 몇개의 막대를 풀로 붙여서 Xcm를 만들 수 있는지 구하라
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine().trim());

        PriorityQueue<Integer> pQ = new PriorityQueue<>();

        int now = 64;

        pQ.add(64);

        int answer = 0;

        while (true) {

            if (now == target) {
                answer = pQ.size();
                break;
            }

            if (now > target) {
                int val = pQ.poll();

                int halfVal = val / 2;

                pQ.add(halfVal);

                int sum = 0;

                Iterator<Integer> iter = pQ.iterator();

                while (iter.hasNext()) {
                    sum += iter.next();
                }

                now = sum;
                if (sum < target) {
                    pQ.add(halfVal);
                    now += halfVal;
                }
            }
        }

        System.out.println(answer);
    }
}
