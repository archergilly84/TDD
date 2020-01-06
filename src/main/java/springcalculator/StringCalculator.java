package springcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StringCalculator {
	
	String sep = ",";
	Separator separator;
	/*
	 * Method to see if optional separator line exists
	 * If it exists extract separator and return the numbers
	 * with all newlines replaced with a delimiter.
	 * @param string that will be checked if a non-default delimiter is to be used.
	 * @return returns a String with any newlines removed and just numbers separated by given delimiter.
	 */
	private String checkSeperator(String string) {			 
		if (string.contains("//")) {
			
			separator = new Separator(string);
			String returnedString = separator.formatSeparator();
			
			if(separator.getSeparator() == null) {				
				sep = separator.getSeparators();				
			} else {
				sep = separator.getSeparator();
			}			
			return returnedString;		 
		} else if (string.contains("\n")) {
			return string.replaceAll("\n", sep);
		} else {			
			return string;
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
			String createdString = checkSeperator(string);			
			String[] numbers = createdString.split(sep);
			
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
				sum += Integer.parseInt(number);
			}
			
			return sum;			
		} else {
			return 0;
		}
	}
}
