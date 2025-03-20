package boj;

import java.io.*;
import java.util.*;

public class problem1043 {
    /*
     * N명의 사람이 주어진다.
     * 이야기의 진실을 아는 사람이 주어진다.
     * 
     * 파티에 오는 사람들의 번호가 주어진다.
     * - 지민이는 모든 파티에 참가한다.
     * 
     * 지민이가 거짓말쟁이로 알려지지 않으면서 과장된 이야기를 할 수 있는 파티의 갯수를 구하라
     * 
     * 파티에 어떤 이야기의 진실을 아는 사람이 없는 경우 과장된 이야기를 할 수 있다.
     * - 단, 임의 사람이 어떤 파티에서는 과장된 이야기, 어떤 파티에서는 진실을 듣게되면 안된다.
     * 
     * 사람과 파티의 최대는 50
     * 
     * 완탐
     * 비트마스킹으로도 가능하다
     * union-find
     */

    static int numOfPerson;
    static int numOfParty;
    static int numOfTruth;
    static int answer;

    static int[] arrOfParent;

    static void union(int left, int right) {
        int parentLeft = find(left);
        int parentRight = find(right);

        // 부모가 같으면 합치지 않아도 된다.
        if (parentLeft == parentRight) {
            return;
        }

        arrOfParent[parentRight] = parentLeft;
    }

    static int find(int child) {

        if (arrOfParent[child] == child) {
            return child;
        }

        return arrOfParent[child] = find(arrOfParent[child]);

    }

    static void inputData() throws IOException {
        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfPerson = Integer.parseInt(st.nextToken());
        numOfParty = Integer.parseInt(st.nextToken());

        // union-find를 위한 준비
        arrOfParent = new int[numOfPerson + 1];
        for (int idx = 0; idx <= numOfPerson; idx++) {
            arrOfParent[idx] = idx;
        }

        st = new StringTokenizer(br.readLine().trim());

        numOfTruth = Integer.parseInt(st.nextToken());

        // 최초 비밀을 알고 있는 사람들
        boolean[] arrOfTruth = new boolean[numOfPerson + 1];

        if (numOfTruth != 0) {
            for (int idx = 0; idx < numOfTruth; idx++) {
                int truth = Integer.parseInt(st.nextToken());

                arrOfTruth[truth] = true;
            }
        }

        boolean[][] party = new boolean[numOfParty][numOfPerson + 1];

        // 파티에 참여하는 사람
        for (int idx = 0; idx < numOfParty; idx++) {

            st = new StringTokenizer(br.readLine().trim());

            // 파티에 참여하는 사람수
            int total = Integer.parseInt(st.nextToken());

            int std = -1;

            for (int personIdx = 0; personIdx < total; personIdx++) {
                int person = Integer.parseInt(st.nextToken());

                // 파티에 참여하는 사람 체크
                party[idx][person] = true;

                if (std == -1) {
                    std = person;
                    continue;
                }

                // 비밀을 공유할 가능성이 있는 사람의 합집합 만들기
                union(std, person);
            }
        }

        // union-find한 결과를 최종 정리
        for (int idx = 1; idx <= numOfPerson; idx++) {
            find(idx);
        }

        // 진실을 알고 있는 사람들의 부모도 진실을 알게 된다.
        for (int idx = 1; idx < numOfPerson + 1; idx++) {
            if (arrOfTruth[idx]) {
                int parent = find(idx);
                arrOfTruth[parent] = true;
            }
        }

        // 파티 인원과 진실을 알고 있는 인원 중 겹치는 인원이 있는지 체크
        for (int idx = 0; idx < numOfParty; idx++) {
            boolean flag = true;

            for (int personIdx = 1; personIdx < numOfPerson + 1; personIdx++) {
                if (party[idx][personIdx]) {
                    int parent = arrOfParent[personIdx];

                    if (arrOfTruth[parent]) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                answer++;
            }
        }

    }

    static void bitmasking() throws IOException {
        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfPerson = Integer.parseInt(st.nextToken());
        numOfParty = Integer.parseInt(st.nextToken());

        // union-find를 위한 준비
        arrOfParent = new int[numOfPerson + 1];
        for (int idx = 0; idx <= numOfPerson; idx++) {
            arrOfParent[idx] = idx;
        }

        st = new StringTokenizer(br.readLine().trim());

        numOfTruth = Integer.parseInt(st.nextToken());

        // 최초 비밀을 알고 있는 사람들
        int[] truthList = new int[numOfTruth];
        long truthBit = 0L;
        if (numOfTruth != 0) {
            for (int idx = 0; idx < numOfTruth; idx++) {
                int truth = Integer.parseInt(st.nextToken());

                truthBit = truthBit | (1L << truth);

                truthList[idx] = truth;
            }
        }

        long[] party = new long[numOfParty];

        // 파티에 참여하는 사람
        for (int idx = 0; idx < numOfParty; idx++) {

            st = new StringTokenizer(br.readLine().trim());

            // 파티에 참여하는 사람수
            int total = Integer.parseInt(st.nextToken());

            int std = -1;

            for (int personIdx = 0; personIdx < total; personIdx++) {
                int person = Integer.parseInt(st.nextToken());

                // 파티에 참여하는 사람 체크
                party[idx] = party[idx] | (1L << person);

                if (std == -1) {
                    std = person;
                    continue;
                }

                // 비밀을 공유할 가능성이 있는 사람의 합집합 만들기
                union(std, person);
            }
        }

        // union-find한 결과를 최종 정리
        for (int idx = 1; idx <= numOfPerson; idx++) {
            find(idx);
        }

        // 진실을 알고 있는 사람들 최종 정리

        // 최초 진실을 알고 있는 사람의 부모을 비트마스킹
        for (Integer truth : truthList) {
            truthBit = truthBit | (1L << arrOfParent[truth]);
        }

        // 파티 인원과 진실을 알고 있는 인원 중 겹치는 인원이 있는지 체크
        for (int idx = 0; idx < numOfParty; idx++) {
            if (!((party[idx] & truthBit) > 0)) {
                // System.out.println("파티 " + idx + " 참가자 " + Long.toBinaryString(party[idx]) + " 진실 "
                //         + Long.toBinaryString(truthBit));
                answer++;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        // inputData();

        bitmasking();

        System.out.println(answer);
    }
}
