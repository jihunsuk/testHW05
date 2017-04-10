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
		Arrays.asList(sentence.split("")).forEach(value -> outputs.append(value + " "));
		System.out.print("Addition of values: " + outputs + " = ");
		
		result = calc(sentence.split(""));
	}
	
	public double getResult(){
		return result;
	}
}
