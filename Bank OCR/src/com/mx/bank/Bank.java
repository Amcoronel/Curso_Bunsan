package com.mx.bank;

import java.util.List;

public class Bank {

	
	public static void main(String[] args) {
		AccountReader reader = new AccountReader(args[0]);
		List<Account> accounts = reader.readAccounts();
		for(Account account: accounts) {
			System.out.println(account.getFormatedAccount());
		}
		
	}

	

	
	

	
}
