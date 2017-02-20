package FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataSelection2 {
	static int[] zip = {98188,98125,98105,98101,
						98072,98052,98036,98032,
						98027,98008,98004,98003};
	static String outputLoc = "/home/kaibian/下载/FinalProjectML/Temp.csv";
	static String inputLoc = "/home/kaibian/下载/FinalProjectML/House Data.csv";
	
	public static void main(String[] args){
		try{
			getUsefulData(inputLoc);
		}catch(Exception e){
			e.getMessage();
		}
		
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
	
	public static void getUsefulData(String title)throws Exception{
		FileReader fr = new FileReader(title);
		BufferedReader br = new BufferedReader(fr);
		String line= br.readLine();
		line = br.readLine();
		while(line!=null){
			String[] str = line.split(",");
			for(int i=0;i<zip.length;i++){
				if(str[13].startsWith(zip[i]+""))
					output(line);
			}
			line = br.readLine();
		}
		br.close();
		fr.close();
	}
}
