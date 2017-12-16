import java.util.*;
public class postfix
{
	String addSub= "+-";
	String multDiv= "*/";
	String openParen= "(";
	String closeParen= ")";
	String exponent= "^";
	String digits= "1234567890";
	String letters= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public ArrayList<String> Convert(String prefix)
	{
		Stack<String> operatorStack= new Stack<String>();
		ArrayList<String> postfix= new ArrayList<String>();
		
		
		boolean currentCharIsDigit= false;
		boolean currentCharIsLetter= false;
		boolean priorCharIsDigit= false;
		boolean priorCharIsLetter= false;
		String numberString= "";
		
		for (char c : prefix.toCharArray()) 
		{
			currentCharIsDigit= false;
			currentCharIsLetter= false;
			
			if (digits.indexOf(c) >= 0)
			{
				currentCharIsDigit= true;
				numberString= charIsDigit(c, numberString, priorCharIsDigit);
			}
			else 
			{
				if (priorCharIsDigit) 
				{
					postfix.add(numberString);
					numberString="";
				}
				if(letters.indexOf(c) >= 0)
				{
					currentCharIsLetter= true;
					charIsLetter(c, operatorStack, postfix, priorCharIsDigit, priorCharIsLetter);
				}
				else if (addSub.indexOf(c) >= 0) 
				{
					charIsOperator(c, operatorStack, postfix);
				}
				else if (multDiv.indexOf(c) >= 0) 
				{
					charIsOperator(c, operatorStack, postfix);
				}
				else if (exponent.indexOf(c) >= 0)
				{
					charIsOperator(c, operatorStack, postfix);
				}
			}
			priorCharIsLetter= currentCharIsLetter;
			priorCharIsDigit = currentCharIsDigit;
		}
		if (priorCharIsDigit)
		{
			postfix.add(numberString);
		}
		while (operatorStack.size() > 0)
		{
			postfix.add(operatorStack.pop());
		}
		return postfix;
	}
	private String charIsDigit(char c, String numberString, boolean priorCharIsDigit)
	{
		if (priorCharIsDigit)
		{
			numberString= numberString+c;
		}
		else 
		{
			numberString= Character.toString(c);
		}
		return numberString;
	}
	private void charIsLetter(char c, Stack<String> operatorStack, ArrayList<String> postfix, boolean priorCharIsDigit, boolean priorCharIsLetter)
	{
		if (priorCharIsDigit || priorCharIsLetter)
		{
			boolean continueCheck= true;
			while (continueCheck)
			{
				if (operatorStack.size() > 0)
				{
					if (multDiv.indexOf(operatorStack.peek()) > 0)
					{
						postfix.add(operatorStack.pop());
					}
					else
					{
						continueCheck= false;
					}
				}
				else
				{
					continueCheck= false;
				}
			}
			operatorStack.push("*");
		}
		postfix.add(Character.toString(c));
	}
	private void charIsOperator(char c, Stack<String> operatorStack, ArrayList<String> postfix)
	{
		boolean continueCheck= true;
		while (continueCheck)
		{
			if (operatorStack.size() > 0)
			{
				// if the stack has an operator at the top then pop that operator to the postfix list
				if ((addSub.indexOf(operatorStack.peek()) >= 0) || (multDiv.indexOf(operatorStack.peek()) >= 0) || exponent.indexOf(operatorStack.peek()) >=0)
				{
					postfix.add(operatorStack.pop());
				}
				else
				{
					continueCheck= false;
				}
			}
			else
			{
				continueCheck= false;
			}
		}
		operatorStack.push(Character.toString(c));
	}
	
}

