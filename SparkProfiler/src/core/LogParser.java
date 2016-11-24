package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.*;

public class LogParser {
	public static void parseLog() {

		BufferedReader br = null;
		long startTime=0;
		long endTime = 0;
		try {
			br = new BufferedReader(new FileReader("/home/tawfiq/sp/spark-2.0.1/logs/app-20161124151631-0003"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				JSONObject obj = null;
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

		System.out.println(endTime-startTime);

	}

}
