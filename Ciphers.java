/**
 * 
 */

/**
 * @author dilip
 *
 */
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*; // Allows user to input message

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.io.*;

public class Ciphers {
	
	public Ciphers () {
		
	}
	
	

	public static void main(String[] args) throws FileNotFoundException, InvalidKeyException {
		
		System.out.println("Welcome to the Evolution of Secret Ciphers.");
		System.out.println("\nFor this program, you will learn about 4 different ciphers, "
				+ "each more complicated than the last");
		System.out.println("\nYou will learn about the ciphers in this order: \n1. Caesar Cipher \n2. "
				+ "Vigenere Cipher \n3. Polygram Cipher \n4. RSA Cryptography");
		
		
		
		
		
		//class instances, file declaration, scanners, printstreams
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nAre you ready to begin? Type yes if you are ready, or no if you are not: ");
		String readyMessage = input.nextLine();
		if (readyMessage.equalsIgnoreCase("no")) {
			System.out.println("\nThank you for playing the Evolution of Secret Ciphers!");	
			System.exit(0);
		}
		
		CaesarCipher c = new CaesarCipher();
		VigenereCipher v = new VigenereCipher();
		Scanner caesarInput = new Scanner(new File("caesar.in"));
		File caesarOutput = new File("caesar.out");
		PrintStream caesarFilePrinter = new PrintStream(new File(caesarOutput.toString()));
		Scanner vigenereInput = new Scanner(new File("vigenere.in"));
		File vigenereOutput = new File("vigenere.out");
		PrintStream vigenereOutputPrinter = new PrintStream(new File(vigenereOutput.toString()));
		Polygram p = new Polygram();
		Scanner polyInput = new Scanner(new File("polygram.in"));
		PrintStream polyOutput = new PrintStream(new File("polygram.out"));
		KeyGenerator gen = null;
		
		
		String exitMessage = "";
		String moveOnMessage = "";
		int finalCount = 0;
		
		while (!exitMessage.equalsIgnoreCase("quit")) {		//loop for user input, exit if user types 'quit'
			
		
				
				c.cipherInfo(caesarFilePrinter);
				String messageFromFile = "";
				int shiftFromFile = 0;
				String encodedCaesarFileMessage = "";
				
				while (caesarInput.hasNext()) {
					messageFromFile = c.getMessageFromFile(caesarInput, caesarFilePrinter);
					shiftFromFile = c.getShiftFromFile(caesarInput, caesarFilePrinter);
					encodedCaesarFileMessage = c.encodeCaesar(messageFromFile, shiftFromFile, caesarFilePrinter);
					c.decryptCaesar(encodedCaesarFileMessage, shiftFromFile, caesarFilePrinter);
					
					if (caesarInput.hasNext()) {
						System.out.println("\nType 'yes' if you would like to view the next example, or type 'no' to try this cipher yourself! ");
						moveOnMessage = input.nextLine();
						
						if (moveOnMessage.equalsIgnoreCase("no")) {
							break;
						}
					}
				}
				
						String message = c.getMessageFromUser(input, caesarInput, caesarFilePrinter);
						exitMessage = message;
						if (exitMessage.equalsIgnoreCase("quit")) {
							break;
						} else {
							finalCount++;
							int key = c.getShiftFromUser(input, caesarFilePrinter);
							System.out.println("\nCheck the caesar.out file if you need to review the examples and explanations");
							String encodedCaesarMessage = c.encodeCaesar(message, key, caesarFilePrinter);	
							c.decryptCaesar(encodedCaesarMessage, key, caesarFilePrinter);
						}
						
			
			 System.out.println("Would you like to quit, or go to the next cipher? ");
			 exitMessage = input.nextLine();
			 if (exitMessage.equalsIgnoreCase("quit")) {
				 System.out.println("\nThank you for playing the Evolution of Secret Ciphers!");
				 System.exit(0);
			 }
			
			
			String vigenereMessage = "";
			String vigenereKeyword = "";
			StringBuilder encodedVigenereMessage = new StringBuilder();
			char[][] table = new char[26][26];
			StringBuilder key2 = new StringBuilder();
			v.cipherOrigin(vigenereOutputPrinter);
			while (vigenereInput.hasNext()) {
				vigenereMessage = v.getVigenereMessageFromFile(vigenereInput, vigenereOutputPrinter);
				vigenereKeyword = v.getKeywordFromFile(vigenereInput, vigenereOutputPrinter);
				table = v.buildTable(vigenereOutputPrinter);
				key2 = v.keywordAndMessage(vigenereMessage, vigenereKeyword, vigenereOutputPrinter);
				key2 = v.formatKeyword(vigenereMessage, vigenereOutputPrinter);
				encodedVigenereMessage = v.encodeVigenereCipher(vigenereMessage, vigenereOutputPrinter);
				v.decodeVigenereCipher(vigenereKeyword, finalCount, vigenereOutputPrinter);
				
				if (vigenereInput.hasNext()) {
					System.out.println("\nType 'yes' if you would like to view the next example, or type 'no' to try this cipher yourself! ");
					moveOnMessage = input.nextLine();
					
					if (moveOnMessage.equalsIgnoreCase("no")) {
						break;
					}
				}
				
			}
			
			 vigenereMessage = v.getVigenereMessage(input, vigenereOutputPrinter);
			 exitMessage = vigenereMessage;
			 if (exitMessage.equalsIgnoreCase("quit")) {
					break;
				} else {
					finalCount++;
					vigenereKeyword = v.getKeyword(input, vigenereOutputPrinter);
					 System.out.println("\nCheck the vigenere.out file to review example messages and explanations");
					 table = v.buildTable(vigenereOutputPrinter);
					 key2 = v.keywordAndMessage(vigenereMessage, vigenereKeyword, vigenereOutputPrinter);
					 key2 = v.formatKeyword(vigenereMessage, vigenereOutputPrinter);
					 encodedVigenereMessage = v.encodeVigenereCipher(vigenereMessage, vigenereOutputPrinter);
					 v.decodeVigenereCipher(vigenereKeyword, 0, vigenereOutputPrinter);
					 
					 
					
				}
			 
			 
			 System.out.println("Would you like to quit, or go to the next cipher? ");
			 exitMessage = input.nextLine();
			 if (exitMessage.equalsIgnoreCase("quit")) {
				 System.out.println("\nThank you for playing the Evolution of Secret Ciphers!");
				 System.exit(0);
			 }
			
			String polyMessage = "";
			String polyShifts = "";
			p.cipherInfo(polyOutput);
			while (polyInput.hasNext()) {
				polyMessage = p.getPolygramMessage(polyInput, polyOutput);
				polyShifts = p.getShifts(polyInput);
				StringBuilder encryptedPolyMessage = p.encryptPolygram(polyMessage, polyShifts, polyOutput);
				p.decryptPolygram(polyOutput, polyShifts, encryptedPolyMessage);
				
				if (polyInput.hasNext()) {
					System.out.println("\nType 'yes' if you would like to view the next example, or type 'no' to try this cipher yourself! ");
					moveOnMessage = input.nextLine();
					
					if (moveOnMessage.equalsIgnoreCase("no")) {
						break;
					}
				}
			}
			polyMessage = p.getUserPolyMessage(input);
			exitMessage = polyMessage;
			if (exitMessage.equalsIgnoreCase("quit")) {
				break;
			} else {
				System.out.println("\nCheck the polygram.out file if you need those example messages again!");
				polyShifts = p.getUserShifts(input);
				StringBuilder encryptedPolyMessage = p.encryptPolygram(polyMessage, polyShifts, polyOutput);
				p.decryptPolygram(polyOutput, polyShifts, encryptedPolyMessage);
				finalCount++;
			}
			 
			
			
			
			
			try {
				gen = new KeyGenerator();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				exitMessage = gen.quit(input);
				if (exitMessage.equalsIgnoreCase("quit")) {
					break;
				} else {
					gen.invoker(null);
					finalCount++;
				}
				
				
			} catch (FileNotFoundException | NoSuchPaddingException | IllegalBlockSizeException
					| BadPaddingException | NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (finalCount == 4) {		//if all ciphers learned, end loop automatically
				break;
			}
			
		}
		
		System.out.println("\nThank you for playing the Evolution of Secret Ciphers!");		//thank you message
	}
	




}

