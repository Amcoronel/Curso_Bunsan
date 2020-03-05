package com.mx.bank;

import java.util.HashMap;
import java.util.Map;

public class AccountDecoderBinaryImpl implements AccountDecoder {
	
	Map<String,String> codes;

	private final int DIGITS_PER_ACCOUNT = 9;
	private final String ON_SEGMENT = "1";
	private final String OFF_SEGMENT = "0";
	
	public AccountDecoderBinaryImpl() {
		initializeCodes();
	}

	private void initializeCodes() {
		this.codes = new HashMap<String,String>();
		codes.put("010101111", "0");
		codes.put("000001001", "1");
		codes.put("010011110", "2");
		codes.put("010011011", "3");
		codes.put("000111001", "4");
		codes.put("010110011", "5");
		codes.put("010110111", "6");
		codes.put("010001001", "7");
		codes.put("010111111", "8");
		codes.put("010111011", "9");
	
		
	
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
	
	private String getNextDigit(int columnPointer,String[] codedLines) {
		
		String segmentCode = "";
		
		for(int line = 0; line < DIGIT_SQUARE_SIZE; line++) {
			
			String lineSegment =codedLines[line].substring(columnPointer,columnPointer + DIGIT_SQUARE_SIZE);
			String[] splitedLine = lineSegment.split("");
			
			for(int column = 0; column < DIGIT_SQUARE_SIZE; column++ ) {
				segmentCode += getSegmentCode(column,splitedLine[column]);
			}
		}
		
		return decodeDigit(segmentCode);
	}
	
	private String decodeDigit(String segmentCode) {
		String digit = codes.get(segmentCode);
		if(digit == null)
			digit = UNKNOWN_DIGIT;
		return digit;
	}
	
	private String getSegmentCode(int columnNumber, String character) {
		if(isValidCharacter(character)) {
			if(character.equals("_") || character.equals("|"))
				return ON_SEGMENT;
			else
				return OFF_SEGMENT;
		}
		else {
			throw new RuntimeException("Error reading digit, unknown character");
		}
	}
	
	private boolean isValidCharacter(String character) {
		if(character.equals(" ") || character.equals("_")  || character.equals("|"))
			return true;
		else
			return false;
	}

	
}
