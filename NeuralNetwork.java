package FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class NeuralNetwork {
	static final double LEARNRATE = 0.1;//learning rate
	static final double THRESHOLD = 10;//threshold
	static final int K = 8;//number of features
	static double[][] weight;//weight of each edge
	static double[][] originalHouseData;//store original data
	static double[][] normalizedHouseData;//each value divided by the largest value in that column
	static String originalHouseDataLoc = "/home/kaibian/下载/FinalProjectML/FinalHouseData.csv";
	static String weightOutputLoc = "/home/kaibian/下载/FinalProjectML/weightOutput.txt";
	static String normalizedDataOutputLoc = "/home/kaibian/下载/FinalProjectML/wNormalizedData.csv";
	static String totalErrorOutputLoc = "/home/kaibian/下载/FinalProjectML/wTotalError.txt";
	
	public static void main(String[] args){
		try{
			initializeData(originalHouseDataLoc);
		}catch(Exception e){
			e.printStackTrace();
		}
		normalize();
		//evaluate the data
		double trainData[][] = new double[normalizedHouseData.length-20][K];
		double evaluateData[][] = new double[20][K];
		for(int i=0;i<normalizedHouseData.length;i++){
			for(int j=0;j<K;j++){
				if(i<normalizedHouseData.length-20)
					trainData[i][j] = normalizedHouseData[i][j];
				else
					evaluateData[i-(normalizedHouseData.length-20)][j] = normalizedHouseData[i][j];
			}
		}
		//--evaluate the data
	//	backPropLearning(normalizedHouseData);
		backPropLearning(trainData);
		predictResult(evaluateData);
	}
	//evaluate the result
	public static void predictResult(double[][] data){
		double[] in = new double[11];
		double[] out = new double[11];
		for(int i=0;i<data.length;i++){
			for(int k=0;k<7;k++){//the first layer node input
				in[k] = data[i][k];
				out[k] = data[i][k];
			}//end-for
			for(int k=7;k<10;k++){
				in[k] = inSum1(k, out,weight);
				out[k] = g(in[k]);
			}//end-for
			in[10] = inSum2(10,out,weight);
			out[10] = g(in[10]);
			System.out.println(out[10]+", ");
		}
	}
	public static void backPropLearning(double[][] houseData){
		initializeWeightsRandomly();
		double[] in = new double[11];
		double[] out = new double[11];
		double[] delta = new double[11];
		int count=0;
		double pre=Integer.MAX_VALUE;
		
		while(true){
			
			double totalError = 0;
			
			for(int i=0;i<houseData.length;i++){
				for(int k=0;k<7;k++){//the first layer node input
					in[k] = houseData[i][k];
					out[k] = houseData[i][k];
				}//end-for
				for(int k=7;k<10;k++){
					in[k] = inSum1(k, out,weight);
					out[k] = g(in[k]);
				}//end-for
				in[10] = inSum2(10,out,weight);
				out[10] = g(in[10]);
				//******************back propagation******************
				delta[10] = out[10]*(1-out[10])*(houseData[i][7]-out[10]);
				totalError += (houseData[i][7]-out[10])*(houseData[i][7]-out[10]);
				
				for(int k=7;k<10;k++){
					delta[k] = out[k]*(1-out[k])*(delta[10]*weight[k][10]);
				}//end-for
				for(int k=0;k<7;k++){
					delta[k] = out[k]*(1-out[k])*outSum(k, delta, weight);
				}//end-for
				for(int m=0;m<weight.length;m++){
					for(int n=0;n<weight[m].length;n++){
						weight[m][n] += LEARNRATE*in[m]*delta[n];
					}//end-for
				}//end-for
			}//end-for
			
			try{//output the values of total error into file
				File file = new File(totalErrorOutputLoc);// if file does not exist, create one.
				if(!file.exists())
					file.createNewFile();
				FileWriter writer = new FileWriter(totalErrorOutputLoc,true);
				writer.write(totalError/2+"\n");
				writer.flush();
				writer.close();
			}catch(Exception e){
				e.getMessage();
			}//end-try-catch
			
			if(totalError/2<=THRESHOLD || pre<=totalError){
				try{//output the results of weights into file
					File file = new File(weightOutputLoc);// if file does not exist, create one.
					if(!file.exists())
						file.createNewFile();
					FileWriter writer = new FileWriter(weightOutputLoc,true);
					for(int i=0;i<7;i++){
						writer.write(weight[i][7] +","+ weight[i][8] + "," + weight[i][9]+"\r\n");
					}//end-for
					for(int i=7;i<10;i++)
						writer.write(weight[i][10]+"\r\n");
					writer.write("*******************\r\n");
					writer.flush();
					writer.close();
				}catch(Exception e){
					e.getMessage();
				}//end-try-catch
				break;
			}//end-if
			pre = totalError;
		}//end-while
	}//end-backPropLearning
	public static double outSum(int index, double[] delta, double[][] weight){
		double total=0;
		for(int k=7;k<10;k++){
			total += delta[k]*weight[index][k];
		}
		return total;
	}
	public static double g(double in){
		return 1/(1+Math.exp(-in));
	}
	//for the third layer input sum
	public static double inSum2(int k, double[] out, double[][] weight){
		double total=0;
		for(int i=7;i<10;i++){
			total += weight[i][k]*out[i];
		}
		return total;
	}
	//for the second layer input sum
	public static double inSum1(int k, double[] out, double[][] weight){
		double total=0;
		for(int i=0;i<7;i++){
			total += weight[i][k]*out[i];
		}
		return total;
	}
	public static void initializeWeightsRandomly(){
		weight = new double[10][11];
		Random rand = new Random();
		for(int i=0;i<weight.length;i++){
			for(int j=0;j<weight[i].length;j++){
				weight[i][j] = rand.nextDouble();
			}
		}
	}
	public static void initializeData(String title) throws Exception{
		FileReader fr = new FileReader(title);
		BufferedReader br = new BufferedReader(fr);
		String line= br.readLine();
		int num=0;
		while(line!=null){
			num++;
			line = br.readLine();
		}
		br.close();
		fr.close();
		originalHouseData = new double[num][K];//the remaining 20 data for evaluation
		normalizedHouseData = new double[num][K];
		readData(title);
		
	}
	public static void readData(String title) throws Exception{
		FileReader fr = new FileReader(title);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		int index=0;
		while(line!=null){
			String[] str = line.split(",");
			originalHouseData[index][0] = Double.parseDouble(str[4]);
			originalHouseData[index][1] = Double.parseDouble(str[5]);
			originalHouseData[index][2] = Double.parseDouble(str[6]);
			originalHouseData[index][3] = Double.parseDouble(str[11]);
			originalHouseData[index][4] = Double.parseDouble(str[12]);
			originalHouseData[index][5] = Double.parseDouble(str[13]);
			originalHouseData[index][6] = Double.parseDouble(str[22]);
			originalHouseData[index][7] = Double.parseDouble(str[19]);// price per square feet
			index++;
			line = br.readLine();
		}
		br.close();
		fr.close();
	}
	public static void normalize(){
		double[] maxValue = max();
		for(int i=0;i<originalHouseData.length;i++){
			for(int j=0;j<originalHouseData[i].length;j++){
				normalizedHouseData[i][j] = originalHouseData[i][j]/maxValue[j];
			}
		}
		try{//output the normalized house data into file
			File file = new File(normalizedDataOutputLoc);// if file does not exist, create one.
			if(!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(normalizedDataOutputLoc,true);
			for(int i=0;i<normalizedHouseData.length;i++){
				for(int j=0;j<normalizedHouseData[i].length;j++){
					writer.write(normalizedHouseData[i][j]+",");
				}//end-for
				writer.write("\n");
			}//end-for
			writer.flush();
			writer.close();
		}catch(Exception e){
			e.getMessage();
		}//end-try-catch
	}//end-normalize
	public static double[] max(){
		double[] maxValue = new double[K];
		for(int i=0;i<K;i++)//set the value of maxValue to be the first row of originalData
			maxValue[i] = originalHouseData[0][i];
		for(int i=0;i<K;i++){
			for(int j=1;j<originalHouseData.length;j++){
				if(maxValue[i]<originalHouseData[j][i])
					maxValue[i] = originalHouseData[j][i];
			}
		}
		return maxValue;
	}
}
