package com.luyue.acm.codejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class DynamicGrid {
	
	public static void input(){
		String pathName = "/Users/xgy/Downloads/A-large.in";
		try{
			File file = new File(pathName);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();
			int T = Integer.parseInt(line);
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<T;i++){
				line = br.readLine();
				String[] rc = line.split(" ");
				int R = Integer.parseInt(rc[0]);
				int C = Integer.parseInt(rc[1]);
				int[][] DG = new int[R][C];
				for(int j=0;j<R;j++){
					line = br.readLine();
					for(int k=0;k<line.length();k++){
						DG[j][k]=line.charAt(k)=='0'?0:1;
					}
				}
				line = br.readLine();
				int N = Integer.parseInt(line);
				sb.append(String.format("Case #%d:\n", i+1));
				for(int j=0;j<N;j++){
					line = br.readLine();
					if(line.startsWith("Q")){
						int count = getCount(DG);
						sb.append(count+"\n");
					}else{//M
						String[] strs = line.split(" ");
						DG[Integer.parseInt(strs[1])][Integer.parseInt(strs[2])] = Integer.parseInt(strs[3]);
					}
				}
			}
			output(sb.toString());
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void copy(int [][] A, int[][] B){
		for(int i=0;i<A.length;i++){
			for(int j=0;j<A[0].length;j++){
				B[i][j] = A[i][j];
			}
		}
	}

	public static int getCount(int[][] B) {
		int[][] A = new int[B.length][B[0].length];
		copy(B,A);
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1) {
                    result++;
                    erase(A, i, j);
                }
            }
        }
        return result;
    }

    public static void erase(int[][] A, int i, int j) {
        A[i][j] = 0;
        while (i - 1 >= 0 && A[i - 1][j] == 1) {
            erase(A, i - 1, j);
        }
        while (i + 1 < A.length && A[i + 1][j] == 1) {
            erase(A, i + 1, j);
        }
        while (j - 1 >= 0 && A[i][j - 1] == 1) {
            erase(A, i, j - 1);
        }
        while (j + 1 < A[0].length && A[i][j + 1] == 1) {
            erase(A, i, j + 1);
        } 
    }
    
    public static void output(String out){
		String pathName = "/Users/xgy/Downloads/A-large.out";
		try {
			File file = new File(pathName);
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(out);
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
