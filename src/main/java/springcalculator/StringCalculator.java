package springcalculator;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
	
	String sep = ",";
	
	/*
	 * Method to see if optional separator line exists
	 * If it exists extract separator and return the numbers
	 * with all newlines replaced with a delimiter.
	 * @param string that will be checked if a non-default delimiter is to be used.
	 * @return returns a String with any newlines removed and just numbers separated by given delimiter.
	 */
	private String checkSeperator(String string) {		
		String trimmedString;		
		if (string.contains("//")) {
			if(string.contains("[")) {
				sep = string.substring(string.indexOf("[") + 1,string.indexOf("]"));
			} else {
				sep = string.substring(2,string.indexOf("\n"));
			}
			System.out.println("New String = " + string.substring(string.indexOf("\n") + 1).replaceAll("\n", sep)  );
			return trimmedString = "\\" + string.substring(string.indexOf("\n") + 1).replaceAll("\n", sep);
		} else {
			return trimmedString = string.replaceAll("\n", sep);
		}
	}
	
	/*
	 * Method to take the digits from the string and add them together.
	 * @param string that contains numbers separated by a delimiter.
	 * @return returns the total value of the numbers within the string as an int.
	 * @throws IllegalArgumentException if string contains negative numbers.
	 */
	public int add(String string) {
		if (string.length() == 1) {
			return Integer.parseInt(string);
		} else if (string.length() > 2) {
			int sum = 0;
						
			String[] numbers = checkSeperator(string).split(sep);		
			
			for(String number : numbers) {
				if(Integer.parseInt(number) < 0) {
					List<Integer> num = new ArrayList<>();
					num.add(Integer.parseInt(number));
					if(numbers.length == -1) {
						throw new IllegalArgumentException("negatives not allowed " + num.toString());
					}					
				} else if (Integer.parseInt(number) > 1000) {
					continue;
				}
				number.replaceAll("\n", "");
				sum += Integer.parseInt(number) ;
			}			
			return sum;			
		} else {
			return 0;
		}
	}
}
