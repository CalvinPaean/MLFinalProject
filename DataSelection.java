package FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataSelection {
	static String outputLoc = "/home/kaibian/下载/FinalProjectML/House Data Edited2.csv";
	static String inputLoc = "/home/kaibian/下载/FinalProjectML/House_data_outlierRemoved.csv";
	static String parkingLotAddr = "/home/kaibian/下载/FinalProjectML/parkinglot unique address.csv";
	static String parkingLot[][] = new String[12][4];
	
	public static void main(String[] args){
		try{
			getParkingLotData(parkingLotAddr);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			getUsefulData(inputLoc);
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	public static void getParkingLotData(String title) throws Exception{
		FileReader fr = new FileReader(title);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		int index=0;
		while(line!=null){
			String[] str = line.split(",");
			
			for(int i=0;i<str.length;i++){
				parkingLot[index][i] = str[i];
			}
			line = br.readLine();
			index++;
		}
		br.close();
		fr.close();
	}
	public static void output(String str) throws Exception{
		File file = new File(outputLoc);// if file does not exist, create one.
		if(!file.exists())
			file.createNewFile();
		FileWriter writer = new FileWriter(outputLoc,true);
		writer.write(str+"\r\n");
		writer.flush();
		writer.close();
	}
	public static double[] computeMinDist(String[][] parkingLot, String[] str){
		double minIndex[] = new double[2];
		minIndex[0]=Math.sqrt((Double.parseDouble(parkingLot[0][2])-Double.parseDouble(str[15]))*
				 (Double.parseDouble(parkingLot[0][2])-Double.parseDouble(str[15]))+
				 (Double.parseDouble(parkingLot[0][3])-Double.parseDouble(str[16]))*
				 (Double.parseDouble(parkingLot[0][3])-Double.parseDouble(str[16])));
		minIndex[1]=0;
		for(int i=1;i<parkingLot.length;i++){
			double mean = Math.sqrt((Double.parseDouble(parkingLot[i][2])-Double.parseDouble(str[15]))*
					 (Double.parseDouble(parkingLot[i][2])-Double.parseDouble(str[15]))+
					 (Double.parseDouble(parkingLot[i][3])-Double.parseDouble(str[16]))*
					 (Double.parseDouble(parkingLot[i][3])-Double.parseDouble(str[16])));
			if(minIndex[0]>mean){
				minIndex[0] = mean;
				minIndex[1] = i;
			}
		}
		return minIndex;
	}
	public static void getUsefulData(String title)throws Exception{
		FileReader fr = new FileReader(title);
		BufferedReader br = new BufferedReader(fr);
		String line= br.readLine();
		
		while(line!=null){
			String[] str = line.split(",");
			double minDistance = computeMinDist(parkingLot, str)[0];
			int minDistanceIndex = (int)computeMinDist(parkingLot,str)[1];
			output(line + ","+minDistance+","+ minDistanceIndex);
			line = br.readLine();
		}
		br.close();
		fr.close();
	}
}
