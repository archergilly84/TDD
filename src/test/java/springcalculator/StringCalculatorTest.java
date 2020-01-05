package springcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;



class StringCalculatorTest {

	@Test
	void returnsZeroIfStringIsEmpty_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("");
		//Assert
		assertEquals(0,sum);
	}
	
	@Test
	void returnsSumIfStringHasOneNumber_One_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("1");
		//Assert
		assertEquals(1,sum);
	}
	
	@Test
	void returnsSumIfStringHasOneNumber_Seven_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("7");
		//Assert
		assertEquals(7,sum);
	}
	
	@Test
	void returnSumIfStringHasTwoNumbers_One_And_Two_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("1,2");
		//Assert
		assertEquals(3,sum);
	}
	
	@Test
	void returnSumIfStringHasTwoNumbers_Three_And_Forty_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("3,40");
		//Assert
		assertEquals(43,sum);
	}
	
	@Test
	void returnSumIfStringHasUnknownNumbers_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("3,40,3,4,5,6,7");
		//Assert
		assertEquals(68,sum);
	}
	
	@Test
	void returnSumIfStringHasNewLineChar_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("1\n2,3");
		//Assert
		assertEquals(6,sum);
	}
	
	@Test
	void returnSumUsingchangingDelimitter_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("//;\n1;2");
		//Assert
		assertEquals(3,sum);
	}
	
	@Test
	void throwExceptionIfNumberIsNegative_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		try {
			//Arrange
			stringCalculator.add("-1,2");
		} catch (IllegalArgumentException e) {
			//Assert
			assertEquals("negatives not allowed -1", e.getMessage());
		}

	}
	
	@Test
	void throwExceptionIfNumberIsNegative_Mulitple_Negatives_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		try {
			//Arrange
			stringCalculator.add("-1,2,-3,5");
		} catch (IllegalArgumentException e) {
			//Assert
			assertEquals("negatives not allowed -1,-3", e.getMessage());
		}

	}
	
	@Test
	void ignoreNumbersBiggerThanOneThousand_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("1001,2");
		//Assert
		assertEquals(2,sum);
	}
	
	@Test
	void delimitterCanBeOfAnyLength_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("//[***]\n1***2***3");
		//Assert
		assertEquals(6,sum);		
	}
	
	@Test
	void twoDelimittersCanBeUsed_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("//[*][%]\n1*2%3");
		//Assert
		assertEquals(6,sum);	
	}
	
	@Test
	void multipleSeparateDelimittersCanBeUsed_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("//[*][%][^]\n1*2%3^4");
		//Assert
		assertEquals(10,sum);	
	}
	
	@Test
	void multipleDelimittersOfAnyLengthCanBeUsed_Test() {
		//Act
		StringCalculator stringCalculator = new StringCalculator();
		//Arrange
		int sum = stringCalculator.add("//[***][%][^]\n1^2%3***4");
		//Assert
		assertEquals(10,sum);	
	}
	
	

}
