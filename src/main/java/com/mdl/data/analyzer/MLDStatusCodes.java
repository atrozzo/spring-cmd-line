package com.mdl.data.analyzer;



/**
 * Define some possible status codes. 
 * 
 * @author angelo
 *
 */
public enum MLDStatusCodes {	
	INFO(100),  			// info commands like help. 
	ERROR(200), 			// any possible error 
	COMPLETED(300), 		// for succesful elaborations 
	NOT_AVAILABLE(400); 	// for possible formulas or command not available. 
	
	int statusCode;
	public int getCode(){
		return statusCode;
	}
	
	MLDStatusCodes(int status){
		statusCode = status;
	}
}
