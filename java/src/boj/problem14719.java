package boj;

import java.io.*;
import java.util.*;

public class problem14719 {

    /*
     * 2차원 배열에 빗물이 쌓인 양을 구하라
     * 
     * 한칸은 1
     * 
     * 세로, 가로 길이가 주어진다.
     * 블록이 쌓인 높이가 차례로 주어진다.
     * 
     * 
     * 기준 높이를 잡는다.(시작은 -1)
     * 기준 높이보다 같거나 큰 높이가 나올 때까지 이동
     * - 같거나 큰 높이가 등장하면 기준 높이의 인덱스까지 이동하면서 높이차를 계산
     * 
     * 기준높이로 이동할 때 끝까지 간 경우 마지막 인덱스부터 마지막 기준 높이 인덱스까지 이동
     * - 이때 기준 높이는 마지막 인덱스의 높이
     * 
     * 스택을 사용하면 된다.
     * 
     * 끝까지 확인했는데 스택에 값이 남은 경우
     * - 높이가 0보다 큰 값을 찾는다. -> 기준 높이와 비교해서 작은 것을 기준으로 한다.
     * - 기준높이보다 큰 높이가 있는 경우 기준높이를 큰 높이고 갱신한다. -> 왜냐하면 큰 높이가 더 많은 빗물을 받기 때문에
     * 반례
     * 3 6
     * 5 4 1 3 1 2
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int height;
    static int width;

    static int[] heightList;

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        heightList = new int[width];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < width; idx++) {
            heightList[idx] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> stack = new ArrayDeque<>();

        stack.addLast(heightList[0]);
        int maxHeight = heightList[0];

        for (int idx = 1; idx < width; idx++) {

            int currentHeight = heightList[idx];

            if (maxHeight <= currentHeight) {
                int minHeight = Math.min(maxHeight, currentHeight);

                // 스택에 모든 값을 확인한다.
                while (!stack.isEmpty()) {
                    int val = stack.pollLast();

                    answer += (minHeight - val);
                }

                // 계산이 끝난 후에는 현재 높이를 스택에 넣어준다.
                stack.addLast(currentHeight);
                maxHeight = currentHeight;

                // 높이가 작을 땐 스택에 넣어준다.
            } else {
                stack.addLast(currentHeight);
            }
        }

        // 스택에 값이 남아있을 때 확인한다.
        if (!stack.isEmpty()) {

            int minHeight = 0;

            while (!stack.isEmpty()) {
                if (stack.peekLast() == 0) {
                    stack.pollLast();
                    continue;
                }

                minHeight = Math.min(maxHeight, stack.pollLast());
                break;
            }

            while (!stack.isEmpty()) {
                int val = stack.pollLast();

                if (minHeight > val) {
                    answer += (minHeight - val);

                    // 중간에 기준 높이보다 큰 높이가 나타나면 갱신해야한다.
                    // 왜냐하면 큰 높이가 더 많은 빗물을 받기 때문에
                } else {
                    minHeight = val;
                }
            }
        }

        System.out.println(answer);
    }
}
