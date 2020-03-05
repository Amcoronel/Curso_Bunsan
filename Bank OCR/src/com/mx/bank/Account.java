package com.mx.bank;


public class Account {

	private String accountNumber;
	private String status;
	
	public Account(String accountNumber) {
		
		this.accountNumber = accountNumber;
		this.status = calculateStatus();
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}
	

	private String calculateStatus() {
		
		if(hasUnknownNumbers()) {
			return "ILL";
		}
		else {
			return validateNumber();
		}
	}
	
	private String validateNumber() {
		int checkSum = 0;
		String[] splitedAccount = accountNumber.split("");
		
		for(int i = splitedAccount.length; i > 0; i--) {
			checkSum += (Integer.parseInt(splitedAccount[i - 1]) * i);
		}
		
		if(checkSum % 11 == 0)
			return "OK";
		else
			return "ERR";
			
		
	}
	
	private boolean hasUnknownNumbers() {
		
		if(accountNumber.contains(AccountDecoder.UNKNOWN_DIGIT))
			return true;
		else 
			return false;
	}
	
	public String getFormatedAccount() {
		
		return accountNumber + " " + status;
	}
	
}
