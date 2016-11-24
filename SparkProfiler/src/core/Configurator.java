package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Configurator {

	public static int coreStarter=1;
	public static int sparkExecutorCores;
	public static int sparkExecutorMemory;
	public static int sparkCoresMax;
	public static int sparkExecutorsPerWorker;
	public static ArrayList<String> submittAppList=null;
	public static int applicationCounter=0;
	
	public static void generateAppConfig()
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("applicationConfig.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(Configuration.profilerLevel.equalsIgnoreCase("low"))
		{
			coreStarter=Configuration.executorCoresLimit;
		}
		
		for(int i=coreStarter;i<=Configuration.executorCoresLimit;i++)
		{
			sparkExecutorCores=i;
			sparkExecutorsPerWorker=Configuration.workerCores/sparkExecutorCores;
			sparkExecutorMemory=Configuration.workerMemory/sparkExecutorsPerWorker;
			
			for(int j=sparkExecutorCores,k=1;k<=(Configuration.workerCores/sparkExecutorCores)*Configuration.workerNumbers;j+=sparkExecutorCores,k++)
			{
				sparkCoresMax=j;
				//System.out.println("***************");
				//System.out.println("spark.executor.cores="+sparkExecutorCores+"\nsparkExecutorMemory="+sparkExecutorMemory+"\nspark.cores.max="+sparkCoresMax);
				 
				try {
					fw.write(sparkExecutorCores+" "+sparkExecutorMemory+" "+sparkCoresMax+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 
				
			}
			
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generateSparkSubmitList()
	{
		submittAppList = new ArrayList<String>();
		FileReader fr = null;
		try {
			fr = new FileReader("applicationConfig.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		BufferedReader bufr = new BufferedReader(fr); 
		String line = null;
		try {
			line = bufr.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		while(line != null)
		{ 
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(line);
		    String core= "\"spark.executor.cores="+Integer.toString(sc.nextInt())+"\"";
		    String mem= "\"spark.executor.memory="+Integer.toString(sc.nextInt())+"\"";
		    String maxcore= "\"spark.cores.max="+Integer.toString(sc.nextInt())+"\"";
			
			submittAppList.add("."+Configuration.sparkHome+"/bin/spark-submit"+
			" --conf "+core+
			" --conf "+mem+
			" --conf "+maxcore+
			" --class "+Configuration.applicationClass+
			" "+Configuration.applicationJar+
			" "+Configuration.inputPath+
			" "+Configuration.outputPath+"/"+Integer.toString(applicationCounter++));
			
			try {
				line = bufr.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	
	}
}
