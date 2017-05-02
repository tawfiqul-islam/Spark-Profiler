package core;

import java.io.FileWriter;
import java.io.IOException;

/*
 * Generates configurations (depending on profiling level[high/low] and 
 * generates application submission commands for Spark)
 * 
 * @author: Muhammed Tawfiqul Islam
 */
public class ConfigurationGenerator {

    int coreStarter=1;
	int sparkExecutorCores;
	int sparkExecutorMemory;
	int sparkCoresMax;
	int sparkExecutorsPerWorker;
	
	public static int applicationCounter=0;
	
	public void generateAppConfig()
	{
		int Ce, Emax, Me, E;
		FileWriter fw = null;
		try {
			fw = new FileWriter("applicationConfig.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Settings.profilerLevel.equalsIgnoreCase("low"))
		{
			coreStarter=Settings.executorCoresLimit;
			System.out.println("yes");
		}
		else
		{
			coreStarter=1;
			System.out.println("no");
		}
		for(int i=coreStarter;i<=Settings.executorCoresLimit;i++)
		{
			Ce=i;
			Me=Ce*(Settings.workerMemory/Settings.workerCores);
			Emax=Settings.workerNumbers*(Settings.workerCores/Ce);
			for(E=1;E<=Emax;E++)
			{
				try {
					Configurations configObj = new Configurations();
					configObj.setCore(Ce);
					configObj.setMemory(Me);
					configObj.setMaxCore(Ce*E);	
					
					configObj.setTotalExecs(E);
					configObj.setTotalCores(Ce*E);
					configObj.setTotalMemory(Me*E);
					configObj.setCost(Settings.coreCost*Ce*E);
					Profiler.configList.add(configObj);
					fw.write(Ce+" "+Me+" "+E+"\n");
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
	/*
	public void generateAppConfig()
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("applicationConfig.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=1;i<=Settings.workerCores/Settings.executorCoresLimit;i++)
		{
			for(int k=1;k<=Settings.workerMemory/Settings.workerCores;k++)
			{
				try {
					Configurations configObj = new Configurations();
					configObj.setCore(Settings.executorCoresLimit);
					configObj.setMemory(Settings.executorCoresLimit*k);
					configObj.setMaxCore(Settings.executorCoresLimit*i);
									
					
					configObj.setTotalCores(Settings.executorCoresLimit*i);
					configObj.setTotalExecs(i);
					configObj.setTotalMemory(i*Settings.executorCoresLimit*k);
					Profiler.configList.add(configObj);
					fw.write(Settings.executorCoresLimit+" "+Settings.executorCoresLimit*k+" "+Settings.executorCoresLimit*i+"\n");
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
	
	public  void generateAppConfig()
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("applicationConfig.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(Settings.profilerLevel.equalsIgnoreCase("low"))
		{
			coreStarter=Settings.executorCoresLimit;
		}
		
		for(int i=coreStarter;i<=Settings.executorCoresLimit;i++)
		{
			sparkExecutorCores=i;
			sparkExecutorsPerWorker=Settings.workerCores/sparkExecutorCores;
			sparkExecutorMemory=Settings.workerMemory/sparkExecutorsPerWorker;
			
			for(int j=sparkExecutorCores,k=1;k<=(Settings.workerCores/sparkExecutorCores)*Settings.workerNumbers;j+=sparkExecutorCores,k++)
			{
				sparkCoresMax=j;
				//System.out.println("***************");
				//System.out.println("spark.executor.cores="+sparkExecutorCores+"\nsparkExecutorMemory="+sparkExecutorMemory+"\nspark.cores.max="+sparkCoresMax);
				 
				try {
					Configurations configObj = new Configurations();
					configObj.setCore(sparkExecutorCores);
					configObj.setMemory(sparkExecutorMemory);
					configObj.setMaxCore(sparkCoresMax);
					configObj.setTotalCores(sparkCoresMax);
					configObj.setTotalExecs(sparkCoresMax/sparkExecutorCores);
					configObj.setTotalMemory(sparkExecutorMemory*(sparkCoresMax/sparkExecutorCores));
					Profiler.configList.add(configObj);
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
	*/
	/*public  void generateAppConfig()
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter("applicationConfig.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(Settings.profilerLevel.equalsIgnoreCase("low"))
		{
			coreStarter=Settings.executorCoresLimit;
		}
		
		for(int i=coreStarter;i<=Settings.workerCores;i++)
		{
			
			sparkExecutorCores=i;
			int memPerCore = Settings.workerMemory/Settings.workerCores;
			sparkExecutorMemory=memPerCore*sparkExecutorCores;
			 
			try {
				Configurations configObj = new Configurations();
				configObj.setCore(sparkExecutorCores);
				configObj.setMemory(sparkExecutorMemory);
				configObj.setMaxCore(sparkExecutorCores);
				configObj.setTotalCores(sparkExecutorCores);
				configObj.setTotalExecs(1);
				configObj.setTotalMemory(sparkExecutorMemory);
				Profiler.configList.add(configObj);
				Profiler.configList.add(configObj);
				Profiler.configList.add(configObj);
				Profiler.configList.add(configObj);
				Profiler.configList.add(configObj);
				fw.write(sparkExecutorCores+" "+sparkExecutorMemory+" "+sparkExecutorCores+"\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
			
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void generateSparkSubmitList()
	{
		
		
		//read the config from applicationConfig.txt and make application submit list
		/*
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
		*/
		
		//read the config from config class list (located in profiler class) and make application submit string
		for(int i=0;i<Profiler.configList.size();i++)
		{ 
		    String core= "--executor-cores "+Integer.toString(Profiler.configList.get(i).getCore())+" ";
		    String mem= "--executor-memory "+Integer.toString(Profiler.configList.get(i).getMemory())+"G ";
		    String maxcore= "--total-executor-cores "+Integer.toString(Profiler.configList.get(i).getMaxCore());
			
			Profiler.configList.get(i).setSubmitStr(Settings.sparkHome+"/bin/spark-submit "+
			core+
			mem+
			maxcore+
			" --class "+Settings.applicationClass+
			" "+Settings.applicationJar+
			" "+Settings.inputPathProfiler+
			" "+Settings.outputPath+"/"+Integer.toString(applicationCounter++));
			
		}
	}
}
