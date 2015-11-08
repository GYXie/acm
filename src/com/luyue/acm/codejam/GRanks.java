package com.luyue.acm.codejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GRanks {
	
	private int T;
	private int P;
	private int[] S;
	private int[] W;
	private int M;
	private Map<String, List<Integer>> records = new HashMap<>();
	
	public void input() throws Exception{
		String pathName = "";
		try{
			File file = new File(pathName);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();
			T = Integer.parseInt(line);
			for(int i=0;i<T;i++){
				line = br.readLine();
				P = Integer.parseInt(line);
				line = br.readLine();
				String[] pointArr = line.split(" ");
				S = new int[pointArr.length];
				for(int j=0;j<pointArr.length;j++){
					S[j] = Integer.parseInt(pointArr[j]);
				}
				line = br.readLine();
				int N = Integer.parseInt(line);
				W = new int[N];
				for(int j=0;j<N;j++){
					line = br.readLine();
					String[] strs = line.split(" ");
					W[j] = Integer.parseInt(strs[0]);
					for(int k=1;k<=P;k++){
						String name = strs[k];
						List<Integer> scores = records.get(name);
						if(scores!=null){
							scores.add(W[j]*S[k]);
						}else{
							scores = new ArrayList<>();
							scores.add(W[j]*S[k]);
							records.put(name, scores);
						}
					}
				}
				for(String name: records.keySet()){
//					Integer[]  pt = Arrays.sort(records.get(name).toArray());
				}
			}
			br.close();
		}catch(Exception e){
			System.out.println("error!");
			e.printStackTrace();
		}	
	}

	public void output(){
		String pathName = "";
		try {
			File file = new File(pathName);
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
