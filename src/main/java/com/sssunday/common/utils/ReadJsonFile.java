package com.sssunday.common.utils;

import java.io.*;

import org.apache.log4j.Logger;

public class ReadJsonFile {

	Logger log = Logger.getLogger(this.getClass());
	public String ReadFile(String path){
		
		BufferedReader reader = null;
	    String returnStr = "";
	    try {
	    	InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path),"UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempStr = null;
			while((tempStr = reader.readLine()) != null){
				returnStr += tempStr;
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e2) {
			log.error(e2.getMessage(),e2);
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e3) {
					log.error(e3.getMessage(),e3);
				}
			}
		}
		return returnStr;
	}
}
