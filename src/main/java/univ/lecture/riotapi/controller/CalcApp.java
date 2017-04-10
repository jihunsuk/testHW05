package univ.lecture.riotapi.controller;

import java.util.Arrays;

/**
 * Calculator application
 */
public class CalcApp {
	public double calc(String[] tokens){
		final Calculate cal = new Calculate();
		cal.setInfix(tokens[0]);
		cal.infixToPostfix();
		return cal.evalPostfix();
	}
	
	public static void main(String[] args){
		final CalcApp app = new CalcApp();
		
		final StringBuilder outputs = new StringBuilder();
		Arrays.asList(args).forEach(value -> outputs.append(value + " "));
		System.out.print("Addition of values: " + outputs + " = ");
		
		System.out.println(app.calc(args));
	}
}
