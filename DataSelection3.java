package FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataSelection3 {
	static String inputLoc = "/home/kaibian/下载/FinalProjectML/parkingLot-BJ.csv";
	static String outputLoc = "/home/kaibian/下载/FinalProjectML/parkingLot-BJ2.csv";
	static double[][][] sumCar = new double[12][13][2];
	static int[][][] count = new int[12][13][2];
	
	public static void main(String[] args){
		for(int i=0;i<count.length;i++)
			for(int j=0;j<count[i].length;j++)
				for(int k=0;k<count[i][j].length;k++)
				count[i][j][k]=0;
		for(int i=0;i<count.length;i++)
			for(int j=0;j<count[i].length;j++)
				for(int k=0;k<count[i][j].length;k++)
				sumCar[i][j][k]=0;
		try{
			getUsefulData(inputLoc);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			output();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void output() throws Exception{
		File file = new File(outputLoc);// if file does not exist, create one.
		if(!file.exists())
			file.createNewFile();
		FileWriter writer = new FileWriter(outputLoc,true);
		for(int i=0;i<sumCar.length;i++){
			writer.write("Parking Lot: " + i +"\r\n");
			for(int j=0;j<sumCar[i].length;j++){
		//		if(sumCar[i][j][0]!=0)
					writer.write(j + ", First, "+sumCar[i][j][0]/count[i][j][0]+"\r\n");
		//		if(sumCar[i][j][1]!=0)
					writer.write(j + ", Second, "+sumCar[i][j][1]/count[i][j][1]+"\r\n");
			}
		}
		writer.flush();
		writer.close();
	}
	public static void getUsefulData(String title)throws Exception{
		FileReader fr = new FileReader(title);
		BufferedReader br = new BufferedReader(fr);
		String line= br.readLine();
		line = br.readLine();
		while(line!=null){
			String[] str = line.split(",");
			int year = Integer.parseInt(str[6]);
			int month = Integer.parseInt(str[4]);
			int day = Integer.parseInt(str[5]);
			int parkinglot = Integer.parseInt(str[9]);
			System.out.println("Parking Lot "+parkinglot+", Month: "+ month +", day: "+day);
			if(year==2014){
				if(month==1){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==2){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==3){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==4){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==5){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==6){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==7){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==8){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==9){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==10){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==11){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}else if(month==12){
					if(day<=15){
						sumCar[parkinglot][month][0] += Double.parseDouble(str[1]); 
						count[parkinglot][month][0] ++;
					}else{
						sumCar[parkinglot][month][1] += Double.parseDouble(str[1]);
						count[parkinglot][month][1] ++;
					}
				}
			}
			line = br.readLine();
			
		}
		br.close();
		fr.close();
	}
}
