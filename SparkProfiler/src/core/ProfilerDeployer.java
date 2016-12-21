package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.spark.launcher.SparkLauncher;

/*
 * Deploys the profiler... actually it launches the application using each generated configs,
 * uses only the part of input user specified for profiling only
 * 
 */

public class ProfilerDeployer {

	void runCommand(String cores, String memory,  String coresMax, int outputIndex)
	{
		Process pr = null;
		try {
					pr = new SparkLauncher()
				    .setSparkHome(Settings.sparkHome)
				    .setAppResource(Settings.applicationJar)
				    .setConf("spark.executor.memory", memory)
				    .setConf("spark.executor.cores", cores)
				    .setConf("spark.cores.max",  coresMax)
				    .addAppArgs(Settings.inputPathProfiler)
				    .addAppArgs(Settings.outputPath+"/"+Integer.toString(outputIndex))
				    .setMainClass(Settings.applicationClass).setMaster("spark://tawfiq-unimelbPC:7077").launch();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		InputStreamReaderRunnable inputStreamReaderRunnable = new InputStreamReaderRunnable(pr.getInputStream(), "input");
		Thread inputThread = new Thread(inputStreamReaderRunnable, "LogStreamReader input");
		inputThread.start();

		InputStreamReaderRunnable errorStreamReaderRunnable = new InputStreamReaderRunnable(pr.getErrorStream(), "error");
		Thread errorThread = new Thread(errorStreamReaderRunnable, "LogStreamReader error");
		errorThread.start();
		try {
			pr.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class InputStreamReaderRunnable implements Runnable {

	    private BufferedReader reader;

	    private String name;

	    public InputStreamReaderRunnable(InputStream is, String name) {
	        this.reader = new BufferedReader(new InputStreamReader(is));
	        this.name = name;
	    }

	    public void run() {
	        System.out.println("InputStream " + name + ":");
	        try {
	            String line = reader.readLine();
	            while (line != null) {
	                System.out.println(line);
	                line = reader.readLine();
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public void submitApps(int outputIndex)
	{
		for(int i=0;i<Profiler.configList.size();i++)
		{	
			runCommand(Integer.toString(Profiler.configList.get(i).getCore()),Integer.toString(Profiler.configList.get(i).getMemory())+"g",Integer.toString(Profiler.configList.get(i).getMaxCore()),outputIndex++);	
		}
	}
}
