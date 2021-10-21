/**
 * 
 */

/**
 * @author dilip
 *
 */
import java.util.*;
import java.io.*;

public class VigenereCipher {
	
	public String vigenereMessageFile; 
	public String keywordFromFile; 
	public String message; 
	public String keyword; 
	public char[][] vigenereTable;
	public int numSpaces;
	public double totalLen; 
	public StringBuilder key; 
	public StringBuilder encodedVigenere;
	public StringBuilder decodedVigenereMessage; 
	
	
	public void cipherOrigin (PrintStream vigenereOutputPrinter) {      //historical background on Vigenere Cipher
		System.out.println("\n\n\nVIGENERE CIPHER OVERVIEW\n");
		System.out.println("The Vigenere Cipher was invented in 1553 by "
				+ "Giovan Battista Bellaso, but was named after Blaise de Vigenere");
		System.out.println("It is a way to encrypt single characters"
				+ " using INTERWOVEN CAESAR CIPHERS, making it more complicated than "
				+ "a simple Caesar Cipher");
		System.out.println("It involves using a table, known "
				+ "as the Vigenere Table, to match up a keyword and a message to encrypt it");
		System.out.println("Now, one character may be encrypted using multiple different shifts");
		System.out.println("NOTE: The Vigenere Cipher DOES NOT work for special characters and numbers, only with letters");
		System.out.println("Let's see how it works!");
	}
	
	
	public String getVigenereMessageFromFile (Scanner vigenereInput, PrintStream vigenereOutputPrinter) {
		vigenereMessageFile = vigenereInput.nextLine();    //gets message from input file
		vigenereOutputPrinter.println("\n\nTHE ORIGINAL MESSAGE: " + vigenereMessageFile + "\n");
		
		System.out.println("\n\nTHE ORIGINAL MESSAGE: " + vigenereMessageFile + "\n");

		return vigenereMessageFile.toUpperCase();
	}
	
	public String getKeywordFromFile(Scanner vigenereInput, PrintStream vigenereOutputPrinter) {
		//gets keyword from file
		
		keywordFromFile = vigenereInput.nextLine();     
		vigenereOutputPrinter.println("\nTHE KEYWORD: " + keywordFromFile + "\n");
		
		System.out.println("\nTHE KEYWORD: " + keywordFromFile + "\n");

		return keywordFromFile.toUpperCase();
	}
	
	public String getVigenereMessage (Scanner input, PrintStream vigenereOutputPrinter) {
		System.out.println("\nYour message for the Vigenere Cipher, or type quit to exit: "); //asks for the message to be encoded
		message = input.nextLine(); 
		System.out.println("\n\nYOUR ORIGINAL MESSAGE: " + message + "\n");
		message = message.toUpperCase(); 
		
		return message;
		
	}
	
	
	public String getKeyword (Scanner input, PrintStream vigenereOutputPrinter) {
		System.out.println("\nType the encoding keyword: "); // Asks for the keyword
		keyword = input.nextLine();
		keyword = keyword.toString().toUpperCase();
		System.out.println("\nYOUR KEYWORD: " + keyword + "\n");
		
		return keyword;
	}
	
	
	
	public char[][] buildTable (PrintStream vigenereOuptutPrinter) {
		int shift = 0; 
		vigenereTable = new char[26][26];
		
		while (shift <= 25) {
			for (int i = 0; i < vigenereTable.length; i++) {
				shift = i; //set shift to i, increase every row by one 
				for (int j = 0; j < vigenereTable[i].length; j++) {
					int d = 65;
					d += shift;
					if (d > 90) {
						d -= 90;
						d = 65 + (d - 1);
						// wraps around the alphabet to display all letters in each row
					}
					shift += 1; // increases the shift by one each column
					vigenereTable[i][j] = (char) d;
				}
				
			}
			
			
		}
		
		vigenereOuptutPrinter.println("\nThe Vigenere Square, used for encryption and decryption with the Vigenere Cipher\n");
		
		System.out.println("\nThe Vigenere Square, used for encryption and decryption with the Vigenere Cipher\n");

		for (int r = 0; r < vigenereTable.length; r++) { 	//loop going through each table row
			for (int c = 0; c < vigenereTable[r].length; c++) {		//loop going through each row's element
				vigenereOuptutPrinter.print(vigenereTable[r][c] + " "); 
				System.out.print(vigenereTable[r][c] + " "); 
																			//print each character separated by a space
			}
			vigenereOuptutPrinter.println();
			System.out.println();
		}
		
		
		return vigenereTable; // return Vigenere Square
	}
	
	
	public StringBuilder keywordAndMessage(String message, String keyword, PrintStream vigenereOutputPrinter) {
		numSpaces = 0;
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == ' ') {  //check for spaces in message, adds one to numSpaces if true
				numSpaces++;
			}
		}
		
		vigenereOutputPrinter.println("\nTo start, "
				+ "we need to match up the keyword with the message.");
		
		System.out.println("\nTo start, "
				+ "we need to match up the keyword with the message.");
		
		
		int origLen = message.length();			//set origLen to message.length
		
		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			if (Character.isWhitespace(c)) {
				origLen--;
				//decreasing value of origLen for each space returns number of characters in message
			} 
			
		}
		
		double wordLen = (double) origLen;
		double keyLen = (double) keyword.length();
		totalLen =  wordLen / keyLen;       //divide new length of message (without spaces) by length 
											//of keyword to find number of times keyword fits into message
		
		vigenereOutputPrinter.println("\nThe keyword " + keyword.toUpperCase() + " fits into your message " + 
		totalLen + " times\n");
		
		System.out.println("\nThe keyword " + keyword.toUpperCase() + " fits into your message " + 
				totalLen + " times\n");
		
		key = new StringBuilder();
		double count = 0.0;
		while (count <= totalLen) {
			key.append(keyword);     //append the keyword on to itself to match the length of the message
			count += 1.0;
			
		}
		
		if (key.length() > message.length() - numSpaces) {     //check if enlongated keyword has more characters than the message
			int start = message.length() - numSpaces;
			int end = key.length();
			key.delete(start, end);         //if keyword is longer than message, delete extra characters so the keyword and message are
											//the same length
		}
		
		
		
		
		
		
		return key; 
		
	}
	
	public StringBuilder formatKeyword(String message, PrintStream vigenereOutputPrinter) {
		
		
		vigenereOutputPrinter.println("\nEach character in the keyword is paired "
				+ "up with each character in your message\n");
		
		System.out.println("\nEach character in the keyword is paired "
				+ "up with each character in your message\n");
		
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == ' ') {      //check for spaces in message
				key.insert(i, " ");              //wherever the message has a space, insert a space in the enlongated keyword
												 //this way, each character in the keyword is matched with each character in the message
			}
		}
		
		vigenereOutputPrinter.println(key.toString().toUpperCase());
		vigenereOutputPrinter.println(message);
		
		System.out.println(key.toString().toUpperCase());
		System.out.println(message);
		
		vigenereOutputPrinter.println("\nAfter you do this, you can start encrypting your message!\n");
		
		System.out.println("\nAfter you do this, you can start encrypting your message!\n");
		
		return key; 
	}
	
	public StringBuilder encodeVigenereCipher(String message, PrintStream vigenereOutputPrinter) {
		encodedVigenere = new StringBuilder();
		char encodedChar = 0;
		char messChar = 0;
		char keyChar = 0;
		
		int value = 65; 
		String line = "";
		while (value <= 90) {
			line += (char) value; //add uppercase letters from A-Z to special line
			value++;
		}
		
		vigenereOutputPrinter.println("\n\n\nSTEP-BY-STEP EXPLANATION - ENCRYPTION\n");
		vigenereOutputPrinter.println("\n1. First, determine the first character in your message");   //step by step explanation of Vigenere Cipher Encryption
		vigenereOutputPrinter.println("\n2. Find the location of that letter "
				+ "in the first row of the Vigenere Table");
		vigenereOutputPrinter.println("\n3. Next, determine the first character of your keyword");
		vigenereOutputPrinter.println("\n4. Find the location of this character "
				+ "in the first column of the Vigenere Table");
		vigenereOutputPrinter.println("\n5. Find the intersection of "
				+ "these two points. The character there is the encrypted character!\n");
		
		System.out.println("\n\n\nSTEP-BY-STEP EXPLANATION - ENCRYPTION\n");
		System.out.println("\n1. First, determine the first character in your message");   //step by step explanation of Vigenere Cipher Encryption
		System.out.println("\n2. Find the location of that letter "
				+ "in the first row of the Vigenere Table");
		System.out.println("\n3. Next, determine the first character of your keyword");
		System.out.println("\n4. Find the location of this character "
				+ "in the first column of the Vigenere Table");
		System.out.println("\n5. Find the intersection of "
				+ "these two points. The character there is the encrypted character!\n");
		
		for (int r = 0; r < 4; r++) {     //a few example characters encrypted for the user
			messChar = message.charAt(r);
			keyChar = key.charAt(r);
			if (messChar == ' ') {     //if message character is a space, skip this character
				continue; 
			} else if (!Character.isLetter(messChar)) {     //if message character is not a letter, skip it
				continue;
			}
			int rowIndex = -1;
			int colIndex = -1;
			colIndex = line.indexOf(messChar);     //find index of each message character in line of characters, 
												  //this is the column index for the Vigenere Table
			rowIndex = line.indexOf(keyChar);     //do the same for the row index
			
			encodedChar = vigenereTable[rowIndex][colIndex];     //find intersection of column index and row index in table to find encrypted character
			vigenereOutputPrinter.println(messChar + " ---> " + encodedChar);   
			
			System.out.println(messChar + " ---> " + encodedChar); 							//demonstrate with arrows how message character is encrypted
			
		}
		
		for (int e = 0; e < message.length(); e++) {		//use of for loop allows each character to be accessed individually
			messChar = message.charAt(e);
			keyChar = key.charAt(e);
			if (messChar == ' ') {
				encodedVigenere.append(" ");   //if message character is a space, add a space on to encrypted message string
				continue; 
			} else if (!Character.isLetter(messChar)) {
				encodedVigenere.append(messChar);     //if message character is a special characrer, add it on to encrypted message string
				continue;
			}
			int rowIndex = -1;
			int colIndex = -1;
			colIndex = line.indexOf(messChar);     //find index of each letter in message, this number is which column to look in
			rowIndex = line.indexOf(keyChar);      //find index of each letter in keyword, this number is which row to look in
			
			encodedChar = vigenereTable[rowIndex][colIndex];         //intersection of column and row in table is encrypted character
			encodedVigenere.append(encodedChar);          //add encrypted character on to end of encrypted message String
			
		}
		
		
		vigenereOutputPrinter.println("\n" + "YOUR ENCRYPTED MESSAGE: " + encodedVigenere + "\n");   
																									//print encrypted message
		System.out.println("\n" + "YOUR ENCRYPTED MESSAGE: " + encodedVigenere + "\n"); 
		
		return encodedVigenere;     //return encrypted message
	}
	
	public void decodeVigenereCipher (String keyword, int numSpaces, PrintStream vigenereOutputPrinter) {
		
		int value = 65; 
		String line = "";
		while (value <= 90) {
			line += (char) value; //add uppercase letters from A-Z to special line
			value++;
		}
		
		decodedVigenereMessage = new StringBuilder(); 
		vigenereOutputPrinter.println("\n\nNow, we are going to decode your message");     //print information needed for decryption
		vigenereOutputPrinter.println("\nYou will need the Vigenere Table, your encrypted message, "
				+ "and your chosen keyword. The table is above, your message and keyword are below:");
		vigenereOutputPrinter.println("\nEncoded message: " + encodedVigenere);
		vigenereOutputPrinter.println("\nKeyword: " + keyword.toUpperCase() + "\n");
		
		System.out.println("\n\nNow, we are going to decode your message");     //print information needed for decryption
		System.out.println("\nYou will need the Vigenere Table, your encrypted message, "
				+ "and your chosen keyword. The table is above, your message and keyword are below:");
		System.out.println("\nEncoded message: " + encodedVigenere);
		System.out.println("\nKeyword: " + keyword.toUpperCase() + "\n");
		
		
		key = keywordAndMessage(encodedVigenere.toString(), keyword, vigenereOutputPrinter);
		
		for (int l = 0; l < key.length(); l++) {
			if (encodedVigenere.charAt(l) == ' ') {    //insert spaces into keyword if encrypted message character is a space
				key.insert(l, " ");
			}
		}
		
		vigenereOutputPrinter.println("\nLet\'s match up the keyword and the encoded message this time\n");
		
		vigenereOutputPrinter.println(key);
		vigenereOutputPrinter.println(encodedVigenere);
		
		System.out.println("\nLet\'s match up the keyword and the encoded message this time\n");
		
		System.out.println(key);
		System.out.println(encodedVigenere);
		
		char realMessageChar = 0;
		
		vigenereOutputPrinter.println("\n\n\nSTEP-BY-STEP EXPLANATION - DECRYPTION\n");
		vigenereOutputPrinter.println("\n1. First, determine the first character in your keyword");     //step by step explanation of decryption
		vigenereOutputPrinter.println("\n2. Find the location of that letter "
				+ "in the first column of the Vigenere Table");
		vigenereOutputPrinter.println("\n3. Next, go down that column until you find the first character of your encrypted message");
		vigenereOutputPrinter.println("\n4. Find the row that this character is in");
		vigenereOutputPrinter.println("\n5. The first letter of this row is your decrypted character");
		
		System.out.println("\n\n\nSTEP-BY-STEP EXPLANATION - DECRYPTION\n");
		System.out.println("\n1. First, determine the first character in your keyword");     //step by step explanation of decryption
		System.out.println("\n2. Find the location of that letter "
				+ "in the first column of the Vigenere Table");
		System.out.println("\n3. Next, go down that column until you find the first character of your encrypted message");
		System.out.println("\n4. Find the row that this character is in");
		System.out.println("\n5. The first letter of this row is your decrypted character");
		
		char encMessChar = 0;
		char keyChar = 0;
		int rowIndex = 0;
		char tempDecodedChar = 0;
		for (int r = 0; r < 4; r++) {          //a few example characters decrypted in this loop
			encMessChar = encodedVigenere.charAt(r);
			keyChar = key.charAt(r);
			rowIndex = 0;
			if (encMessChar == ' ') {      //if encrypted message character is a space, skip character
				continue; 
			} else if (!Character.isLetter(encMessChar)) {     //if encrypted message character is a special character, skip character
				continue;
			} else {
				int colIndex = line.indexOf(keyChar);          //find index of characters in keyword in line (from 'Line' method), this is the column to look in
				while (vigenereTable[rowIndex][colIndex] != encMessChar) {   //traverse through Vigenere Table to find correct row
					if (vigenereTable[rowIndex][colIndex] != encMessChar) {
						rowIndex++;
						
						
						 
					} 
				}
				tempDecodedChar = line.charAt(rowIndex);
			}
			vigenereOutputPrinter.println(encMessChar + " ---> " + tempDecodedChar);
			
			System.out.println(encMessChar + " ---> " + tempDecodedChar);
			
		}
		keyChar = 0;
		encMessChar = 0;
		for (int q = 0; q < encodedVigenere.length(); q++) {     //loop through encrypted message
			keyChar = key.charAt(q);                          //access each character of keyword
			encMessChar = encodedVigenere.charAt(q);          //acces each character of encrypted message
			
			rowIndex = 0;
			if (encMessChar == ' ') {
				decodedVigenereMessage.append(" ");
				continue; 
			} else if (!Character.isLetter(encMessChar)) {
				decodedVigenereMessage.append(encMessChar);            //check for spaces and special charactera, skip them if necessary
				continue;
			} else {
				int colIndex = line.indexOf(keyChar);               //find first index of keyword, this is the column to look in
				while (vigenereTable[rowIndex][colIndex] != encMessChar) {      //loop through each row for given column to find each encrypted character
					if (vigenereTable[rowIndex][colIndex] != encMessChar) {
						rowIndex++;
						
						
						 
					} 
				}
				realMessageChar = line.charAt(rowIndex);         //count value where loop exits is the right row of real message character
				
				
			}
			decodedVigenereMessage.append(realMessageChar);         //add found character to end of decrypted string
			
			
		}
		
		vigenereOutputPrinter.println("\nYOUR DECRYPTED MESSAGE: " + decodedVigenereMessage + "\n");     
		
		System.out.println("\nYOUR DECRYPTED MESSAGE: " + decodedVigenereMessage + "\n");		//output original message
		
	}
	
	

}
