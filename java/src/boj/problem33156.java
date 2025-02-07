package boj;

import java.io.*;
import java.util.*;

public class problem33156 {

    /*
     * 두 수열 s와 t가 다음 연산을 원하는 만큼 반복해 서로 같아질 수 있다면 두 수열은 서로 이븐하다
     * - 1 <= i < j < |s|를 만족하는 두 양의 정수 i와 j를 골라 si 와 sj의 값을 바꾼다.
     * 
     * 길이 N 수열 A가 주어질 때 
     * A[l, r]: 인덱스 l부터 r까지의 연속된 부분 수열
     * 
     * 다음 조건을 만족시키는 A[l, r]의 최대 길이를 구하라
     * 
     * m = math.floor((l + r) / 2) 에 대하여 A[l, m]과 A[m+1, r]이 서로 이븐하다
     * - 연속된 부분 수열 A[l, r]을 절반으로 나눠 얻은 두 수열이 이븐하면 A[l, r]의 최대 길이를 출력하라
     * - 없으면 0
     * 
     * 좌표압축
     * - 수열에 최대값이 10억
     * - 수열의 최대길이는 5000
     * - 좌표 압축을 하지 않으면 구간이 이븐한지 확인하기 위한 배열을 만들 때 수열값의 최대값만큼 길이가 필요-> 이거는 불가능 10억짜리 정수배열는 안됨
     * 좌표압축을 하면 수열의 최대길이 5000만큼의 배열만 필요
     * - 입력 수열을 정렬한 후에 수열의 값을 키로하고 인덱스를 값으로 하는 맵을 사용하면
     * - 맵에서 수열의 값을 키로 해당 인덱스를 조회하면 좌표압축 배열에 접근할 수 있다.
     * 
     * 중간위치부터 절반씩 나눠서 구간이 이븐한지 확인 
     * - 최대값을 구하게 되면 탐색은 종료
     * - 중간위치에서 확인했으면 i만큼 왼쪽, 오른쪽으로 이동후 탐색 이때 최대로 찾는 범위가 축소됨
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int listSize;
    static int[] numberList;
    static int[] sortedList;
    static int[] leftVisited;
    static int[] rightVisited;
    static int total;
    static HashMap<Integer, Integer> map = new HashMap<>();

    static int answer;

    static void inputData() throws IOException {
        answer = 0;

        listSize = Integer.parseInt(br.readLine().trim());

        numberList = new int[listSize];
        sortedList = new int[listSize];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < listSize; idx++) {
            int value = Integer.parseInt(st.nextToken());
            numberList[idx] = value;
            sortedList[idx] = value;
        }

        // 좌표 압축
        // 1. 기존 배열을 정렬
        // 2. 값을 키로 인덱스를 값으로 하는 맵을 구성한다.
        Arrays.sort(sortedList);
        total = 0;
        for (Integer i : sortedList) {
            if (!map.containsKey(i)) {
                map.put(i, total++);
            }
        }
    }


    static boolean checkList(int[] leftVisited, int[] rightVisited) {
        boolean flag = true;

        // 이븐한지 확인
        for (int idx = 0; idx < total; idx++) {
            if (leftVisited[idx] != rightVisited[idx]) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    static void leftCheck() {
        // 중간위치부터 최대로 구간이 이븐한지 확인한다.
        int mid = listSize / 2;
        int staticMax = mid;
        int max = staticMax;
        int value = 0;

        leftVisited = new int[total];
        rightVisited = new int[total];

        if (listSize % 2 == 0) {
            mid = mid - 1;
            value = 1;
        }

        // mid를 기준으로 왼쪽 확인
        while (max > 0) {

            if (answer >= max * 2) {
                break;
            }

            leftVisited = new int[total];
            rightVisited = new int[total];

            for (int alpha = 0; alpha < max; alpha++) {
                // 왼쪽 확인
                leftVisited[map.get(numberList[mid - alpha])]++;

                // 오른쪽 확인
                rightVisited[map.get(numberList[(mid + 1) + alpha])]++;

                // 구간이 이븐한지 확인
                if (checkList(leftVisited, rightVisited)) {
                    answer = Math.max((alpha + 1) * 2, answer);
                }
            }

            mid -= 1;
            max = staticMax - value++;
        }

    }

    static void rightCheck() {
        int mid = listSize / 2;
        int staticMax = mid;
        int max = staticMax;
        int value = 1;

        leftVisited = new int[total];
        rightVisited = new int[total];

        // mid를 기준으로 오른쪽 확인
        max = staticMax - value;

        if (listSize % 2 == 1) {
            mid = mid + 1;
        }

        while (max > 0) {

            if (answer >= max * 2) {
                break;
            }

            leftVisited = new int[total];
            rightVisited = new int[total];

            for (int alpha = 0; alpha < max; alpha++) {
                // 왼쪽 확인
                leftVisited[map.get(numberList[mid - alpha])]++;

                // 오른쪽 확인
                rightVisited[map.get(numberList[(mid + 1) + alpha])]++;

                // 구간이 이븐한지 확인
                if (checkList(leftVisited, rightVisited)) {
                    answer = Math.max((alpha + 1) * 2, answer);
                }
            }

            mid += 1;
            max = staticMax - ++value;
        }
    }


    static void solution() {
        leftCheck();

        // 구한 답이 최대 길이면 오른쪽은 확인하지 않아도 됨
        if (answer >= listSize) {
            return;
        }

        rightCheck();

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
