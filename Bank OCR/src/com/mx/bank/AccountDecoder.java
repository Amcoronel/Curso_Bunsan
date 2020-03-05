package com.mx.bank;

public interface AccountDecoder {

	static final int DIGIT_SQUARE_SIZE = 3;
	static  final int DIGITS_PER_ACCOUNT = 9;
	static  final String UNKNOWN_DIGIT = "?";
	static final String BLANK_SPACE_REGEX ="\\s";
	
	public Account decode(String[] codedLines);

	
}
