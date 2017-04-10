package univ.lecture.riotapi.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import univ.lecture.riotapi.model.Operator;

public class Calculate {
    private Stack<Character> _oStack;
    private Stack<Double> _vStack;
    private char[] _infix;
    private ArrayList<String> _postfix;
    
    public Calculate()
    {
    	this._oStack = null;
    	this._postfix = null;
        this._infix = null;
        this._vStack = null;
    }
    
    public void setInfix(String anInfix)
    {
    	this._infix = anInfix.toCharArray(); // String 문자열을 char로 변환후 배열로 반환해주는 메소드
    }
    
    public String infix()
    {
    	if (this._infix != null)
    	    return String.valueOf(this._infix);
    	else return null;
    }
    
    public String postfix()
    {
    	if(this._postfix != null)
    	    return String.valueOf(this._postfix);
    	else return null;
    }
    
    public boolean infixToPostfix()
    {
    	int i;
    	int p;
    	char curToken, poppedToken, topToken;
    	this._oStack = new Stack<Character>();
    	this._postfix = new ArrayList();
    	
    	i = 0;
    	p = 0;
    	
    	while (i<this._infix.length)
    	{
    		curToken =_infix[i++];
    		if (Character.isLetter(curToken)){
    			System.out.print("\n문자를 입력하셨습니다. 옳바른 값을 입력해주세요.");
    			System.exit(0);
    		}
    		else if(Character.isDigit(curToken)) {
    			StringBuilder buf = new StringBuilder(Character.toString(curToken)); 
    			while(i<_infix.length && Character.isDigit(_infix[i])){
    				buf.append(_infix[i]);
    				i++; 
    			}
    			_postfix.add(buf.toString());
    		}
    		else{
    			if( curToken == ')' ){
    				if (! _oStack.isEmpty() ){
    					poppedToken = (char)_oStack.pop();
    				}
    				else 
    					return false;
    				
    				while(poppedToken != '(' ){
    					_postfix.add(Character.toString(poppedToken));
    					if (! _oStack.isEmpty() ){
    						poppedToken = (char) _oStack.pop();
    					}
    					else 
    						return false;
    				}
    			}
    			else {
    				int inComingP = inComingPrecedence(curToken);
    				if (! _oStack.isEmpty() ){
    					topToken = (char) _oStack.peek();
    					while(inStackPrecedence(topToken) >= inComingP) {
    						poppedToken = (char) _oStack.pop();
    						_postfix.add(Character.toString(poppedToken));
    						if( !_oStack.isEmpty() )
    							topToken = (char)_oStack.peek();
    						else
    							break;
    					}
    				}
    				this._oStack.push(curToken);
    				
    			}
    		}
    	}
    	if ( !this._oStack.isEmpty()){
    	this._postfix.add(Character.toString(this._oStack.pop()));
    	}
        return true;
    	}
    
    private int inComingPrecedence(char aToken) {
    	if(aToken == '+')
    		return 12;
    	else if (aToken == '-')
    		return 12;
    	else if (aToken == '(')
    		return 20;
    	else if (aToken == ')')
    		return 19;
    	else if (aToken == '*')
    		return 13;
    	else if (aToken == '/')
    		return 13;
    	else if(aToken == '%')
    		return 13;
    	else if(aToken == '^')
    		return 17;
    	else if(aToken == '$')
    		return 0;
    	else 
    		return -1;
    }
    
    private int inStackPrecedence(char aToken) 
    {
    	if(aToken == '+')
    		return 12;
    	else if (aToken == '-')
    		return 12;
    	else if (aToken == '(')
    		return 0;
    	else if (aToken == ')')
    		return 19;
    	else if (aToken == '*')
    		return 13;
    	else if (aToken == '/')
    		return 13;
    	else if(aToken == '%')
    		return 13;
    	else if(aToken == '^')
    		return 16;
    	else if(aToken == '$')
    		return 0;
    	else 
    		return -1;
    }
    
    public double evalPostfix()
    {
    	int p;
    	String curToken;
    	this._vStack = new Stack<Double>();
    	Iterator<String> iter = this._postfix.iterator();
    	
    	p = 0;
    	
    	while(iter.hasNext())
    	{
    		curToken = iter.next();
    		if (Character.isDigit(curToken.charAt(0))){
    			this._vStack.push(Double.parseDouble(String.valueOf(curToken)));
  
    		}
    		else {
    			if(this._vStack.size() >= 2){
    			double cur = this._vStack.pop();
    			double pre = this._vStack.pop();
    			double result = 0;
    			
    			final Operator operator = Operator.findOperator(curToken.charAt(0));
    			try{
    			result = operator.evaluate(pre, cur);
    			} catch(ArithmeticException e){
    				System.out.print("\n"+e);
    				System.exit(0);
    			}
    			
    			this._vStack.push(result);
    			}
    			else
    				break;
    			
    		}
    	}
    	double result2 = this._vStack.pop();
    	return result2;
    	
    	
    }


}
