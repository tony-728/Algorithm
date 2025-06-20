package boj;

import java.io.*;
import java.util.*;

public class problem12852 {
    /**
     * 정수 X가 주어진다.
     * - X가 3으로 나눠 떨어지면 3으로 나눈다.
     * - X가 2로 나눠 떨어지면 2로 나눈다.
     * - 1을 뺀다.
     * 
     * 위 3가지 동작을 적절히 수행해서 1을 만들려고 한다.
     * 연산을 사용하는 최소값을 구하라
     */

    static class Node {
        int value;
        int count;
        String command;

        public Node(int value, int count, String command) {
            this.value = value;
            this.count = count;
            this.command = command;
        }
    }

    static void DpSolution(int N) {
        int dp[] = new int[N + 1]; //최소 횟수 저장
        int before[] = new int[N + 1]; //경로 저장

        dp[1] = 0;

        for (int i = 2; i <= N; i++) {

            // 1을 빼는 연산
            dp[i] = dp[i - 1] + 1;
            before[i] = i - 1;

            // 3으로 나눠 떨어지면서 이전 연산 횟수보다 작은경우 갱신
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                before[i] = i / 3;
            }

            // 2로 나눠 떨어지면서 이전 연산 횟수보다 작은경우 갱신
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                before[i] = i / 2;
            }
        }
        StringBuilder sb = new StringBuilder();

        sb.append(dp[N]).append(" ");

        while (N > 0) {
            sb.append(N).append(" ");
            N = before[N];
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int value = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        DpSolution(value);

        PriorityQueue<Node> pQ = new PriorityQueue<>((o1, o2) -> {
            return o1.count - o2.count;
        });

        pQ.add(new Node(value, 0, ""));

        while (!pQ.isEmpty()) {
            Node node = pQ.poll();

            if (node.value == 1) {
                sb.append(node.count).append("\n");
                sb.append(value).append(" ");
                for (int idx = 0; idx < node.command.length(); idx++) {
                    char c = node.command.charAt(idx);
                    if (c == '3') {
                        value /= 3;
                    } else if (c == '2') {
                        value /= 2;
                    } else if (c == '1') {
                        value -= 1;
                    }
                    sb.append(value).append(" ");
                }
                break;
            }

            if (node.value % 3 == 0) {
                pQ.add(new Node(node.value / 3, node.count + 1, node.command + "3"));
            }

            if (node.value % 2 == 0) {
                pQ.add(new Node(node.value / 2, node.count + 1, node.command + "2"));
            }

            pQ.add(new Node(node.value - 1, node.count + 1, node.command + "1"));
        }

        System.out.println(sb);
    }
}
