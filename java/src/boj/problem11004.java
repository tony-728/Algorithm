package boj;

import java.io.*;
import java.util.*;

public class problem11004 {
    /*
     * Arrays.sort로 정렬하면 시간초과 발생
     * 
     * merge sort, heap sort를 사용해야 한다.
     */


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int numOfNumber, targetIndex;

        st = new StringTokenizer(br.readLine().trim());

        numOfNumber = Integer.parseInt(st.nextToken());
        targetIndex = Integer.parseInt(st.nextToken());

        int[] arrOfNumber = new int[numOfNumber];

        // heapSort를 위해 pQ를 사용
        PriorityQueue<Integer> pQ = new PriorityQueue<>();

        int[] temp = new int[numOfNumber];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < numOfNumber; idx++) {
            int value = Integer.parseInt(st.nextToken());
            arrOfNumber[idx] = value;
            pQ.add(value);
        }

        sort(arrOfNumber, temp, 0, numOfNumber - 1);

        // for (int idx = 0; idx < targetIndex; idx++) {
        //     if (idx != targetIndex - 1) {
        //         pQ.poll();
        //         continue;
        //     }

        //     System.out.println(pQ.poll());
        // }

        System.out.println(arrOfNumber[targetIndex - 1]);

    }

    static void sort(int[] array, int[] temp, int start, int end) {

        if (start >= end)
            return;

        int mid = (start + end) / 2;

        // 절반씩 쪼갠다.
        sort(array, temp, start, mid);
        sort(array, temp, mid + 1, end);

        merge(array, temp, start, mid, end);
    }

    static void merge(int[] array, int[] temp, int start, int mid, int end) {

        int i = start;     // 왼쪽 부분 배열 시작 인덱스
        int j = mid + 1;  // 오른쪽 부분 배열 시작 인덱스
        int k = start;     // temp 배열에 복사할 위치

        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        // 왼쪽 배열에 남은 요소 복사
        while (i <= mid) {
            temp[k++] = array[i++];
        }

        // 오른쪽 배열에 남은 요소 복사
        while (j <= end) {
            temp[k++] = array[j++];
        }

        // 정렬된 데이터를 원래 배열로 복사
        for (int idx = start; idx <= end; idx++) {
            array[idx] = temp[idx];
        }
    }
}
