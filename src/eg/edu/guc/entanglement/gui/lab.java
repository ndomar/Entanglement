package eg.edu.guc.entanglement.gui;
import java.util.*;

public class lab {
	
	public static void main (String[]args)
	{
		Scanner sc = new Scanner(System.in);
		double x = sc.nextDouble();
		double counter = 1;
		double result = 1;
		double num = 1;
		while (counter <= x )
		{
			System.out.println("num = " +num);
			System.out.println("Counter = " +counter);
			System.out.println("Counter2 = " +counter * counter);
			result = result *( ((2 * counter ) - 1) / (counter * counter));
			System.out.println("Result = " +result);
			num = num + 2;
			counter++;
		}
		System.out.println(result);
		counter--;
		System.out.println((2 * counter) - 1);
	}
}
