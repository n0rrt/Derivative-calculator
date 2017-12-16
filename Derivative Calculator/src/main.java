
public class main 
{
	
	public static void main(String args[])
	{
		postfix blah= new postfix();
		rules run= new rules();
		input in= new input();
		String next= in.input();
		System.out.println(run.sum(blah.Convert(next)));
	}
}