package com.mdl.data.analyzer.cmd;


/**
 * The interface define a simple way to execute commands from the menu
 * 
 * @author Angelo Trozzo.
 *
 * @param <T>
 */
public interface MLDCommand<T> {


	/**
	 * Execute a command
	 *
	 * @return
     */
 	T executeCmd();

	/**
	 * Give the chance to print out the result of the command.
	 */
	void printResult();





}
