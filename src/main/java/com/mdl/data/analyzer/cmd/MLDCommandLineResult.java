package com.mdl.data.analyzer.cmd;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * The class contains information about each executed operation. 
 * 
 * @author angelo
 *
 */
public class MLDCommandLineResult implements  MLDResult {
	
	private Integer statusCode;
	private String description;
	private String errorMsg;
	private Map<String, String> detailedResult;
	private LocalDate excutionTime;
	
	private MLDCommandLineResult(CmdResultBuilder builder){
		this.statusCode = builder.statusCode;
		this.description = builder.description;
		this.errorMsg = builder.errorMsg;
		this.detailedResult = builder.dataResult;
		this.excutionTime = builder.excutionTime;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public Map<String, String> getDataResult() {
		return detailedResult;
	}

	public LocalDate getExcutionTime() {
		return excutionTime;
	}

	@Override
    public String toString() {
		StringBuilder output = new StringBuilder();

		output.append("Execution result :");
		output.append("\n ");
		output.append("Status code : ");
		output.append(statusCode);
		output.append("\n ");
		output.append("Operation executed :");
		output.append(description);
		output.append("\n ");
		if (errorMsg != null){
			output.append("Error message : ");
			output.append(errorMsg);
			output.append("\n ");
		}
		output.append("Execution time : ");
		output.append(excutionTime);
		output.append("\n ");


		if (detailedResult != null && detailedResult.size()>0){
			for (Map.Entry<String, String> entry : detailedResult.entrySet())
			{
				output.append("\n ");
				output.append(entry.getKey() + " ----> " + entry.getValue());
				output.append("\n ");

			}
		}

		return output.toString();
	}
	
	/**
	 * This is the builder class that allow the contruction of {@link MLDCommandLineResult} object.
	 * 
	 * @author Angelo
	 *
	 */
	public static class CmdResultBuilder{
		private final Integer statusCode;
		private final String description;
		private  String errorMsg;
		private  Map<String, String> dataResult;
		private  LocalDate excutionTime;
		
		public CmdResultBuilder (@NotNull final Integer code, 
				@NotNull final String  desc){
			 this.statusCode = code;
			 this.description = desc;
		}
		public CmdResultBuilder time(@NotNull final LocalDate excTime){
			 this.excutionTime = excTime;
			 return this;
		}
		
		public CmdResultBuilder errorMsg(@NotNull final String errMsg){
			 this.errorMsg = errMsg;
			 return this;
		}
		
		public CmdResultBuilder dataResult(@NotNull final Map<String, String> data){
			 this.dataResult = data;
			 return this;
		}
		
		
		public MLDCommandLineResult build(){
			return new MLDCommandLineResult(this);
		}
		
	}
	
}


