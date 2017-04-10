package univ.lecture.riotapi.controller;

import java.util.Arrays;

/**
 * Calculator application
 */
public class CalcApp {
	double result = 0;
	
	public double calc(String[] tokens){
		final Calculate cal = new Calculate();
		cal.setInfix(tokens[0]);
		cal.infixToPostfix();
		return cal.evalPostfix();
	}
	
	public CalcApp(String sentence){
		final StringBuilder outputs = new StringBuilder();
		String array[] = new String[1];
		array[0] = sentence;
		Arrays.asList(array).forEach(value -> outputs.append(value + " "));
		System.out.print("Addition of values: " + outputs + " = ");
		
		result = calc(array);
	}
	
	public double getResult(){
		return result;
	}
}
