package Divya;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Mock2_removeDupe {
	

	public static void main(String[] args) {
		/* Write a program to remove duplicate characters 
		 * in a given sentence(including space)
		 */
		Scanner input = new Scanner (System.in);
		System.out.println("Enter a string:");
		String s = input.nextLine();
		System.out.println("Sentence w/o duplicates:" + noDuplicate(s));
		input.close();
	}
	
	public static String noDuplicate(String s)
	{
		char[] ch = s.toCharArray();
		Set <String> chSet = new LinkedHashSet<String>();
		for (int i = 0; i < ch.length; i++)
		{
			String uCh = "" + ch[i];
			chSet.add(uCh);
		}
		Iterator<String> it = chSet.iterator();
		String result = "";
		while (it.hasNext())
		{
			result = result + it.next();
		}
		return result;
		
	}

}
