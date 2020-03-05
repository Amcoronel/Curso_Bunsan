package com.mx.bank;

import java.util.HashMap;
import java.util.Map;

public class AccountDecoderImpl implements AccountDecoder {
	
	Map<String,String> codes;
	
	public AccountDecoderImpl() {
		initializeCodes();
	}

	private void initializeCodes() {
		this.codes = new HashMap<String,String>();
		codes.put("_|||_|","0");
		codes.put("||","1");
		codes.put("__||_","2");
		codes.put("__|_|","3");
		codes.put("|_||","4");
		codes.put("_|__|","5");
		codes.put("_|_|_|","6");
		codes.put("_||","7");
		codes.put("_|_||_|","8");
		codes.put("_|_|||","9");
	
	}
	
	public Account decode(String[] codedLines) {
		
		Account account;
		
		String accountNumber = "";
		int columnPointer = 0;
		
		for(int i = 0; i < DIGITS_PER_ACCOUNT; i++) {
			accountNumber += getNextDigit(columnPointer,codedLines);
			columnPointer +=  DIGIT_SQUARE_SIZE;
		}
		
		account = new Account(accountNumber);
		return account;
	}
	
	private String getNextDigit(int column,String[] codedLines) {
		
		String digitCode = "";
		
		for(int line = 0; line < DIGIT_SQUARE_SIZE; line++) {
			String currentLine = codedLines[line];
			String lineSegment =currentLine.substring(column,column + DIGIT_SQUARE_SIZE);
			digitCode += trimLine(lineSegment);			
		}
		
		return decodeDigit(digitCode);
	}

	private String trimLine(String line) {
		return line.replaceAll(BLANK_SPACE_REGEX, "");
		
	}
	
	private String decodeDigit(String segmentCode) {
		String digit = codes.get(segmentCode);
		if(digit == null)
			digit = UNKNOWN_DIGIT;
		return digit;
	}
	
	
}
