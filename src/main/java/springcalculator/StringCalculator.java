package springcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Pattern regex = Pattern.compile("//(\\[(\\D+)])+\n(.*)");
		Matcher match = regex.matcher(string);
		
		if (string.contains("//")) {
			String s = "";
			if(regex.matcher(string).matches()) {
				StringBuilder sb = new StringBuilder();
				
				while(match.find()) {		
					if(string.contains("][")) {
						
						String sepSplit  = "";						
						String[] separator = match.group(2)
								.replaceAll("\\]\\[",",")
								.split(",");
						StringBuilder multi = new StringBuilder();
						for(int i = 0; i < separator.length; i++) {							
							if(i == separator.length - 1) {								
								sepSplit += "\\" + separator[i];								
							} else if(separator[i].length() > 1) {								
								for(int j = 0; j < separator[i].length(); j++) {									
									multi.append("\\" + separator[i].charAt(j));	
								}
								sepSplit += multi.toString() + "|";
							} else {								
								sepSplit += "\\" + separator[i] + "|";
							}							
						}
						System.out.println(sepSplit);
						//create string with numbers and commas
						String[] numbers = match.group(3).split(sepSplit);
						
						for(int i = 0; i < numbers.length;i++) {
							if (i != numbers.length - 1) {								
								sb.append(numbers[i] + ",");								
							} else {								
								sb.append(numbers[i]);
							}
						}
						
						//return new string
						s = sb.toString();
					} else {
						
						int lengthOfDelimitter = string.substring(string.indexOf("[") + 1, string.indexOf("]")).length();
						String workingString = string.substring(string.indexOf("[") + 1, string.indexOf("]"));
						
						for(int i = 0; i < lengthOfDelimitter ;i++) {
							
							sb.append("\\" + workingString.charAt(i));
						}
						
						sep = sb.toString();
						s = string.substring(string.indexOf("\n") + 1);
					}
				}			
				return s;
			} else {
				sep = string.substring(2,string.indexOf("\n"));
				return string.substring(string.indexOf("\n") + 1);
			}
			
		} else {
			return string.replaceAll("\n", sep);
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
			//System.out.println(checkSeperator(string));
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
