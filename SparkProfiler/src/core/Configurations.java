package core;

/*
 * Holds the configurations needed for launching a Spark application on the cluster
 * It is also responsible for holding completion time of application with each config
 * 
 * @author: Muhammed Tawfiqul Islam
 */
public class Configurations {
	
	private String appID;
	private int core;
	private int memory;
	private int maxCore;
	private double completionTime;
	private int priority;
	private String submitStr;	
	private boolean isSuccessful;
	
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public int getCore() {
		return core;
	}
	public void setCore(int core) {
		this.core = core;
	}
	public int getMemory() {
		return memory;
	}
	public void setMemory(int memory) {
		this.memory = memory;
	}
	public int getMaxCore() {
		return maxCore;
	}
	public void setMaxCore(int maxCore) {
		this.maxCore = maxCore;
	}
	public double getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(double completionTime) {
		this.completionTime = completionTime;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public boolean isSuccessful() {
		return isSuccessful;
	}
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public String getSubmitStr() {
		return submitStr;
	}
	public void setSubmitStr(String submitStr) {
		this.submitStr = submitStr;
	}
	
	public void printConfig()
	{
		System.out.println("\n\n*Application ID: "+appID);
		System.out.println("Cores: "+core);
		System.out.println("Memory: "+memory);
		System.out.println("Max Cores: "+maxCore);
		System.out.println("Completion Time: "+ completionTime+"ms");
		System.out.println("Submission Command: "+submitStr);
		
	}

}
