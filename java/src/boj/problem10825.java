package boj;

import java.io.*;
import java.util.*;

public class problem10825 {

    /**
     * 조건에 맞게 정렬
     */

    static class Student implements Comparable<Student> {
        String name;
        int korean;
        int english;
        int math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        public int compareTo(Student o) {
            if (this.korean == o.korean) {
                if (this.english == o.english) {
                    if (this.math == o.math) {
                        return this.name.compareTo(o.name);
                    }

                    return o.math - this.math;
                }
                return this.english - o.english;
            }
            return o.korean - this.korean;
        }
    }

    static int numOfStudent;
    static Student[] arrOfStudent;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfStudent = Integer.parseInt(br.readLine().trim());

        arrOfStudent = new Student[numOfStudent];

        for (int idx = 0; idx < numOfStudent; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            arrOfStudent[idx] = new Student(name, korean, english, math);
        }

        Arrays.sort(arrOfStudent);

    }

    public static void main(String[] args) throws IOException {
        inputData();

        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < numOfStudent; idx++) {
            sb.append(arrOfStudent[idx].name).append("\n");
        }

        System.out.println(sb);
    }
}
