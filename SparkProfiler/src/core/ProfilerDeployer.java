package core;

import java.io.IOException;

public class ProfilerDeployer {
	
	public static void submitApps()
	{
		for(int i=0;i<Configurator.submittAppList.size();i++)
		{
			
			System.out.println(Configurator.submittAppList.get(i));
			String cmd = Configurator.submittAppList.get(i);
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
				break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
			try {
				while ((line=buf.readLine())!=null) {
				System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
}
