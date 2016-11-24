package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationLoader {
  public static void loadConfig() {

	Properties prop = new Properties();
	InputStream input = null;

	try {

		//specify properties file with full path... or only file name if it's in current directory
		input = new FileInputStream("profiler.conf");

		// load a properties file
		prop.load(input);

		Configuration.workerNumbers=Integer.parseInt(prop.getProperty("worker.numbers"));
		Configuration.workerCores=Integer.parseInt(prop.getProperty("worker.cores"));
		Configuration.workerMemory=Integer.parseInt(prop.getProperty("worker.memory"));
		Configuration.executorCoresLimit=Integer.parseInt(prop.getProperty("executor.cores.limit"));
		Configuration.profilerInputSize=Integer.parseInt(prop.getProperty("profiler.input.size"));
		Configuration.profilerLevel=prop.getProperty("profiler.level");
		Configuration.sparkHome=prop.getProperty("spark.home");
		Configuration.inputPath=prop.getProperty("profiler.input.path");
		Configuration.applicationJar=prop.getProperty("application.jar.path");
		Configuration.applicationClass=prop.getProperty("application.class");
		Configuration.outputPath=prop.getProperty("application.outputPath");
		
	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

  }

}