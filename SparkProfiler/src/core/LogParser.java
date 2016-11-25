package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.*;

public class LogParser {
	
	public static void parseLog() {

		System.out.println(Configuration.sparkHome+"/logs");
		File folder = new File(Configuration.sparkHome+"/logs");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				BufferedReader br = null;
				long startTime=0;
				long endTime = 0;
				try {
					br = new BufferedReader(new FileReader(Configuration.sparkHome+"/logs/"+listOfFiles[i].getName()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(listOfFiles[i].getName().contains("spark"))
					continue;
				String line;
				try {
					while ((line = br.readLine()) != null) {
						JSONObject obj = null, obj1=null;
						try {
							obj = new JSONObject(line);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							String n = obj.getString("Event");
							if(n.equalsIgnoreCase("SparkListenerApplicationStart"))
							{
								startTime=obj.getLong("Timestamp");
							}
							if(n.equalsIgnoreCase("SparkListenerApplicationEnd"))
							{
								endTime=obj.getLong("Timestamp");
							}
							if(n.equalsIgnoreCase("SparkListenerEnvironmentUpdate"))
							{
		
								obj1=obj.getJSONObject("Spark Properties");
								System.out.println("App ID: "+listOfFiles[i].getName());
								System.out.println("Executor Cores="+obj1.getInt("spark.executor.cores"));
								System.out.println("Executor Memory="+obj1.getString("spark.executor.memory"));
								System.out.println("Max Cores="+obj1.getInt("spark.cores.max"));

							}
								
							//System.out.println(n);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Application Completion Time="+(endTime-startTime)/1000+"s"+"\n\n");

			} 
		}

	}

}
