package core;

import java.util.Comparator;

/*
 * Holds the configurations needed for launching a Spark application on the cluster
 * It is also responsible for holding completion time of application with each config
 * 
 * @author: Muhammed Tawfiqul Islam
 */
public class Configurations implements Comparator<Configurations>, Comparable<Configurations>{

	private String appID;
	private int core;
	private int memory;
	private int maxCore;
	private double completionTime[] = new double[3];
	private int numCompletionTime=0;
	private int priority;
	private int totalMemory;
	private int totalCores;
	private int totalExecs;
	private String submitStr;	
	private boolean isSuccessful;
	private double p1;
	private double p2;
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
	public double getCompletionTime(int i) {
		return completionTime[i];
	}
	public void setCompletionTime(double completionTime) {
		this.completionTime[numCompletionTime++] = completionTime;
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

	public int getTotalMemory() {
		return totalMemory;
	}
	public void setTotalMemory(int totalMemory) {
		this.totalMemory = totalMemory;
	}
	public int getTotalCores() {
		return totalCores;
	}
	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	public int getTotalExecs() {
		return totalExecs;
	}
	public void setTotalExecs(int totalExecs) {
		this.totalExecs = totalExecs;
	}
	
	public double getP1() {
		return p1;
	}
	public void setP1(double p1) {
		this.p1 = p1;
	}
	public double getP2() {
		return p2;
	}
	public void setP2(double p2) {
		this.p2 = p2;
	}
	
	public int getNumCompletionTime() {
		return numCompletionTime;
	}
	public void setNumCompletionTime(int numCompletionTime) {
		this.numCompletionTime = numCompletionTime;
	}
	// Overriding the compareTo method
	public int compareTo(Configurations d) {
		return (this.appID).compareTo(d.appID);
	}

	// Overriding the compare method to sort the age 
	public int compare(Configurations d, Configurations d1) {
		return (int)(d.completionTime[0] - d1.completionTime[0]);
	}

	public void printConfig()
	{
		System.out.println("\n\n*Application ID: "+appID);
		System.out.println("Cores: "+core);
		System.out.println("Memory: "+memory);
		System.out.println("Max Cores: "+maxCore);
		for(int i=0;i<numCompletionTime;i++)
			System.out.println("Completion Time "+(i+1)+": "+completionTime[i]+"ms");
		System.out.println("Total Cores: "+totalCores);
		System.out.println("Total Memory: "+totalMemory);
		System.out.println("Total Executors: "+totalExecs);
		System.out.println("Num of Completion Times: "+numCompletionTime);
		System.out.println("submission String: "+submitStr);

	}

}
