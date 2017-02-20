package FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataSelection4 {
	static String outputLoc = "/home/kaibian/下载/FinalProjectML/FinalHouseData.csv";
	static String inputLoc = "/home/kaibian/下载/FinalProjectML/House Data Edited2.csv";
	static String parkingLotAverageCar = "/home/kaibian/下载/FinalProjectML/ParkingLotAverageCarsNumber.csv";
	static double[] parkingLot = new double[12];//the number of cars in each parking lot
	
	public static void main(String[] args){
		try{
			getParkingLotData(parkingLotAverageCar);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			updateHouseData(inputLoc,outputLoc);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void outputFile(String output, String line, double num) throws Exception{
		File file = new File(output);// if file does not exist, create one.
		if(!file.exists())
			file.createNewFile();
		FileWriter writer = new FileWriter(output,true);
	
		writer.write(line+","+num+"\r\n");
		writer.flush();
		writer.close();
	}
	public static void updateHouseData(String input,String output) throws Exception{
		FileReader fr = new FileReader(input);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while(line!=null){
			String[] str = line.split(",");
		
			if(Integer.parseInt(str[21])==0)
				outputFile(output, line, parkingLot[0]);
			else if(Integer.parseInt(str[21])==1)
				outputFile(output, line, parkingLot[1]);
			else if(Integer.parseInt(str[21])==2)
				outputFile(output, line, parkingLot[2]);
			else if(Integer.parseInt(str[21])==3)
				outputFile(output, line, parkingLot[3]);
			else if(Integer.parseInt(str[21])==4)
				outputFile(output, line, parkingLot[4]);
			else if(Integer.parseInt(str[21])==5)
				outputFile(output, line, parkingLot[5]);
			else if(Integer.parseInt(str[21])==6)
				outputFile(output, line, parkingLot[6]);
			else if(Integer.parseInt(str[21])==7)
				outputFile(output, line, parkingLot[7]);
			else if(Integer.parseInt(str[21])==8)
				outputFile(output, line, parkingLot[8]);
			else if(Integer.parseInt(str[21])==9)
				outputFile(output, line, parkingLot[9]);
			else if(Integer.parseInt(str[21])==10)
				outputFile(output, line, parkingLot[10]);
			else
				outputFile(output, line, parkingLot[11]);
			line = br.readLine();
		}
		fr.close();
		br.close();
	}
	public static void getParkingLotData(String title)throws Exception{
		FileReader fr = new FileReader(title);
		BufferedReader br = new BufferedReader(fr);
		String line= br.readLine();
		int index=0;
		while(line!=null){
			parkingLot[index++] = Double.parseDouble(line);
			line = br.readLine();
		}
		br.close();
		fr.close();
	}
}
