package core;

import java.io.IOException;

/*
 * Deploys the profiler... actually it launches the application using each generated configs,
 * uses only the part of input user specified for profiling only
 * 
 */

public class ProfilerDeployer {
	
	static void runCommand(String cmd)
	{
		Runtime run = Runtime.getRuntime();
		Process pr = null;
		try {
			pr = run.exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pr.waitFor();
			//break;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void submitApps()
	{
		for(int i=0;i<Profiler.configList.size();i++)
		{
			System.out.println(Profiler.configList.get(i).getSubmitStr());
			String cmd = Profiler.configList.get(i).getSubmitStr();
			runCommand(cmd);	
		}
	}
}
