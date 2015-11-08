package com.luyue.acm.codejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class GBalloon {
	public static void input(){
		String pathName = "/Users/xgy/Downloads/B-small-attempt3.in";
		try{
			File file = new File(pathName);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			BufferedReader br = new BufferedReader(reader);
			
			String line = br.readLine();
			int T = Integer.parseInt(line);
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<T;i++){
				line = br.readLine();
				String[] nmq = line.split(" ");
				int N = Integer.parseInt(nmq[0]);
				int M = Integer.parseInt(nmq[1]);
				int Q = Integer.parseInt(nmq[2]);
				int[] V = new int[M];
				line = br.readLine();
				String[] vs = line.split(" ");
				for(int j=0;j<M;j++){
					V[j] = Integer.parseInt(vs[j]);
				}
				int[] P = new int[N];
				int[] H = new int[N];
				int[] HNew = new int[N];
				double[] time = new double[N]; 
				boolean possible = true;
				
				for(int j=0;j<N;j++){
					line = br.readLine();
					String[] ph = line.split(" ");
					P[j] = Integer.parseInt(ph[0]);
					H[j] = Integer.parseInt(ph[1]);
					HNew[j] = H[j];
					if(P[j]==0){
						time[j]=0;
					}else if(P[j]*V[H[j]]<0){
						time[j] = Math.abs(P[j])/Math.abs(V[H[j]]);
					}else{
						int up = 0;
						int down = 0;
						while(H[j]+up<M&&P[j]*V[H[j]+up]>0){
							up++;
						}
						while((H[j]-down>=0&&P[j]*V[H[j]-down]>0)){
							down++;
						}
						if ((H[j]+up<M&&P[j] * V[H[j] + up] < 0) && (H[j]-down>=0&&P[j] * V[H[j] - down] < 0)) {
							HNew[j] = H[j] + (down > up ? up : -down);
							Q = Q - (down > up ? up : down);
						}else if(H[j]+up<M&&P[j] * V[H[j] + up] < 0){
							HNew[j] = H[j] +  up;
							Q = Q - up;
						}else if(H[j]-down>=0&&P[j] * V[H[j] - down] < 0){
							HNew[j] = H[j] -  down;
							Q = Q - down;
						}else{
							possible = false;
						}
						if(possible)
							time[j] = Math.abs(P[j])/Math.abs(V[HNew[j]]);
					}
				}
				
				int index = 0;
				double max = Double.MIN_VALUE;
				if(Q<0){
					possible = false;
				}else if(possible){
					boolean flag = true;
					while(Q>0&&flag){
						for(int k=0;k<N;k++){
							if(time[k]>max){
								max = time[k];
								index = k;
							}
						}
						Q = Q+Math.abs(HNew[index]-H[index]);
						int up = 0;
						int down = 0;
						while(H[index]+up<M&&!(P[index]*V[H[index]+up]<0&&time[index]>(int) Math.ceil(Math.abs(P[index])/Math.abs(V[H[index]+up])))){
							up++;
						}
						while(H[index]-down>=0&&!(P[index]*V[H[index]-down]<0&&time[index]>(int) Math.ceil(Math.abs(P[index])/Math.abs(V[H[index]-down])))){
							down++;
						}
						if ((H[index]+up<M&&P[index] * V[H[index] + up] <= 0) && (H[index]-down>=0&&P[index] * V[H[index] - down] <= 0)) {
							HNew[index] = H[index] + (down > up ? up : -down);
							Q = Q - (down > up ? up : down);
						}else if(H[index]+up<M&&P[index] * V[H[index] + up] <= 0){
							HNew[index] = H[index] +  up;
							Q = Q - up;
						}else if(H[index]-down>=0&&P[index] * V[H[index] - down] <= 0){
							HNew[index] = H[index] -  down;
							Q = Q - down;
						}else{
							flag = false;
						}
						if(Q>=0){
							time[index] = (int) Math.ceil(Math.abs(P[index])/Math.abs(V[HNew[index]]));
						}
					}
				}
				if(possible){
					max = Integer.MIN_VALUE;
					for(int k=0;k<N;k++){
						if(time[k]>max){
							max = time[k];
							index = k;
						}
					}
					sb.append(String.format("Case #%d: %d\n", i+1,(int)Math.ceil(max)));
				}else{
					
					sb.append(String.format("Case #%d: IMPOSSIBLE\n", i+1));
				}
			}
			output(sb.toString());
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void output(String out){
		String pathName = "/Users/xgy/Downloads/B-small-attempt3.out";
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
