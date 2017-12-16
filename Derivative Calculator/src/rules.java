import java.util.*;
public class rules extends postfix
{
	public String constant(ArrayList<String> postfix)
	{
		return"0";
	}
	public String sum(ArrayList<String> postfix)
	{
		if(postfix.indexOf("x") !=0) //if x has a coefficient
			{
				if(Integer.parseInt(postfix.get(postfix.indexOf("^")-1))-1 ==1)//if x is raised to 1
					{ 
						int locOfExponent= postfix.indexOf("^");
						postfix.remove(locOfExponent);
						postfix.remove(locOfExponent-1);
						return (postfix.get(0)+postfix.get(2)+postfix.get(1));
					}
				else if(Integer.parseInt(postfix.get(postfix.indexOf("^")-1))-1 ==0)//if x is raised to 0
					{
						int locOfExponent= postfix.indexOf("^");
						int locOfX= postfix.indexOf("x");
						postfix.remove(locOfExponent);
						postfix.remove(locOfExponent-1);
						postfix.remove(locOfX);
						return (postfix.get(0));
					}
				else
					{
						int newCoefficient= (Integer.parseInt(postfix.get(postfix.indexOf("^")-1))*(Integer.parseInt(postfix.get(0))));
						postfix.set(postfix.indexOf("^")-1, Integer.toString(Integer.parseInt(postfix.get(postfix.indexOf("^")-1))-1));
						postfix.set(0, Integer.toString(newCoefficient));
						return (postfix.get(0)+postfix.get(2)+postfix.get(1)+postfix.get(4)+postfix.get(3)); 
					}
				
			}
		else
			{ 
				postfix.add("1");
				Collections.rotate(postfix, 1);
				postfix.add("*");
				Collections.rotate(postfix.subList(2,5), 1);
				
				int newCoefficient= (Integer.parseInt(postfix.get(postfix.indexOf("^")-1))*(Integer.parseInt(postfix.get(0))));
				postfix.set(postfix.indexOf("^")-1, Integer.toString(Integer.parseInt(postfix.get(postfix.indexOf("^")-1))-1));
				postfix.set(0, Integer.toString(newCoefficient));
				return (postfix.get(0)+postfix.get(2)+postfix.get(1)+postfix.get(4)+postfix.get(3)); 
				
			}
	}
	
}