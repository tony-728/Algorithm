package boj;

import java.io.*;
import java.util.*;

public class problem10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        int numOfCommand = Integer.parseInt(br.readLine().trim());

        Deque<Integer> q = new ArrayDeque<>();
        int value;

        for (int idx = 0; idx < numOfCommand; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            String command = st.nextToken();

            switch (command) {
                case "push":
                    value = Integer.parseInt(st.nextToken());
                    q.addLast(value);
                    break;
                case "front":
                    if (q.isEmpty()) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(q.peek()).append("\n");
                    }
                    break;
                case "back":
                    if (q.isEmpty()) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(q.peekLast()).append("\n");
                    }
                    break;
                case "size":
                    sb.append(q.size()).append("\n");
                    break;
                case "empty":
                    if (q.isEmpty()) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;
                case "pop":
                    if (q.isEmpty()) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(q.pop()).append("\n");
                    }
                    break;
            }
        }

        System.out.println(sb);

    }
}
