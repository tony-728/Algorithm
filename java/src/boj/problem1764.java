package boj;

import java.io.*;
import java.util.*;

public class problem1764 {
   
    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    
    static HashMap<String, String> nList = new HashMap<>();
    static String[] mList;
    
    static StringBuilder sb = new StringBuilder();
    
    
    /*
    n을 해시맵으로 관리
    m을 입력받고 정렬
    
    m을 순서대로 해시맵에 있는지 확인
    */
    
    
  public static void main(String args[]) throws IOException {
    
    st = new StringTokenizer(br.readLine().trim());
    
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    
    int count = 0;
    
    
    for(int idx=0; idx<n; idx++){
        String input = br.readLine();
        
        nList.put(input, input);
    }
    
    mList = new String[m];
    
    for(int idx=0; idx<m; idx++){
        String input = br.readLine();
        
        mList[idx] = input;
    }
    
    Arrays.sort(mList);
    
   
    for(int idx=0; idx<m; idx++){
        String name= mList[idx];
        
        if(nList.get(name) != null){
            count++;
            sb.append(name).append("\n");
        }
    }
    
    System.out.println(count);
    System.out.println(sb);
    
  }
}