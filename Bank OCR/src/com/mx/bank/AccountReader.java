package com.mx.bank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountReader {


	private Scanner reader;
	private AccountDecoder decoder;
	
	public AccountReader(String fileName) {
		this.reader = getScanner(fileName);
		this.decoder = new AccountDecoderImpl();
	}

	
	public List<Account> readAccounts() {
		
		List<Account> accounts = new ArrayList<Account>();
		
		while(reader.hasNext()) {
			accounts.add(readNextAccount());
			readSeparator();
		}
		
		reader.close();
		return accounts;
	}
	
	public Account readNextAccount() {
		String[] codedLines = readCodedLines();
		Account account = decoder.decode(codedLines);
		return account;
	}
	
	private void readSeparator() {
		if(reader.hasNextLine())
			reader.nextLine();
	}

	
	private String[] readCodedLines(){
		
		String[] codedLines = new String[AccountDecoder.DIGIT_SQUARE_SIZE];
		
		try {
			
			for(int i = 0; i < AccountDecoder.DIGIT_SQUARE_SIZE; i++){
				codedLines[i] = reader.nextLine();
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Error al leer cuenta");
		}
		return codedLines;
	}
	
	private  Scanner getScanner(String fileName) {
		Scanner reader;
		
		try {
			reader = new Scanner(new FileInputStream("file.txt"));
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
			throw new RuntimeException("Archivo no encontrado");
		}
		
		return reader;
	
	}
	
}
