package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogParser {
	public static void parseLog() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("/home/tawfiq/sp/spark-2.0.1/logs/app-20161124151631-0003"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = br.readLine()) != null) {
				if (line.contains("Complete Population")){
					// do something
					break; // breaks the while loop
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// we reached the section with numbers
		try {
			while ((line = br.readLine()) != null) {
				// use String.split to split the line, then convert 
				//the values to double and process them.  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
