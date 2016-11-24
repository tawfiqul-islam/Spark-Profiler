package core;

/*@author: Muhammed Tawfiqul Islam
 * 
*/
public class Profiler {

	public static void main(String[] args) {
		
		//Load Configurations
		ConfigurationLoader.loadConfig();
	    //Configuration.printConfig();
	    Configurator.generateAppConfig();
	    //Prepare Input Using MicroBenachMarking Suits
	    
	    //For Real Application Scenario, Do something to get a portion of the original input
	    //call ProfilerInputManager
	    
	    //Generate configurations of application for profiler
	    
	    Configurator.generateSparkSubmitList();
	    
	    for(int i=0;i<Configurator.submittAppList.size();i++)
	    {
	    	System.out.println(Configurator.submittAppList.get(i));
	    }
	    //start profiling of application with generated configurations

	}
}
