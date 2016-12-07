package core;

import java.util.ArrayList;

/*The main class for the SparkProfiler
 * 
 * @author: Muhammed Tawfiqul Islam
 * 
*/
public class Profiler {
	
	public static ArrayList<Configurations> configList=null;

	
	static void printConfigList()
	{
		for(int i=0;i<configList.size();i++)
		{
			configList.get(i).printConfig();
		}
	}
	
	static void initialize()
	{
		configList = new ArrayList<Configurations>();
	}
	public static void main(String[] args) {
		
		initialize();
		//Load Settings for Profiler
		SettingsLoader.loadSettings();
		
	    Settings.printSettings();
		ConfigurationGenerator configGenObj = new ConfigurationGenerator();
	    //Prepare Input Using MicroBenachMarking Suits
	    
	    //For Real Application Scenario, Do something to get a portion of the original input
	    //call ProfilerInputManager
	    
	    //Generate configurations of application for profiler
		configGenObj.generateAppConfig();
	    configGenObj.generateSparkSubmitList();
	    
	    //start profiling of application with generated configurations
	    //ProfilerDeployer profileDeployerObj = new ProfilerDeployer();
	    //profileDeployerObj.submitApps();

		LogParser logParserObj = new LogParser();
		logParserObj.parseLog();
		
		printConfigList();
	}
}
