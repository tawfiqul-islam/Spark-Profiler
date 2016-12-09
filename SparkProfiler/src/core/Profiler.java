package core;

import java.util.ArrayList;
import java.util.Collections;

/*The main class for the SparkProfiler
 * 
 * @author: Muhammed Tawfiqul Islam
 * 
*/
public class Profiler {
	
	public static ArrayList<Configurations> configList=new ArrayList<Configurations>();

	
	static void printConfigList()
	{
		for(int i=0;i<configList.size();i++)
		{
			configList.get(i).printConfig();
		}
	}
	
	public static void main(String[] args) {
		
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
		
		//printConfigList();
		
		Collections.sort(configList, new Configurations());
		printConfigList();
		
		
	}
}
