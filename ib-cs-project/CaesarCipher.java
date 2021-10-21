/**
 * 
 */
import java.util.*;
import java.io.*;
/**
 * @author dilip
 *
 */
public class CaesarCipher {
	 
	
	public void cipherInfo (PrintStream caesarFilePrinter) {  //historical background on Caesar Cipher
		System.out.println("\n\n\nCAESAR CIPHER OVERVIEW\n");
		System.out.println("\nThe Caesar Cipher was used by the Romans for secret communication.");
		System.out.println("It was one of the earliest forms of secret ciphers known to history");
		System.out.println("It works like this: each letter of a message is shifted by a given amount to form a new message.");
		System.out.println("\nIt works with: letters only");
		System.out.println("This message cannot be deciphered without knowning the shift that was used.");
		System.out.println("\nNote that when encoding, you have to wrap around the alphabet if you have a shift that goes past the letter \'z'.");
		System.out.println("It\'s the same for decoding, just apply the shift backwards!"); 
		System.out.println("This time, you must wrap around the alphabet the other way if you have to go past \'a'.\n");
	}
	
	
	
	public String getMessageFromUser (Scanner input, Scanner caesarInput, PrintStream caesarFilePrinter) {
		System.out.print("\nYour message for the Caesar Cipher, or type quit to exit: "); //asks for the message to be encoded
		String message = input.nextLine(); 
		message = message.toUpperCase(); 
		caesarFilePrinter.println("\nYour message: " + message + "\n");
		
		
		return message;
		
	}
	
	public int getShiftFromUser (Scanner input, PrintStream caesarFilePrinter) {
		System.out.print("Type the encoding key: "); // Asks for the encoding key
		String shift = input.nextLine();
		caesarFilePrinter.println("\nYour shift: " + shift + "\n");
		return Integer.parseInt(shift);
	}
	
	public String getMessageFromFile(Scanner caesarInput, PrintStream caesarFilePrinter) {
		String messageFromFile = caesarInput.nextLine();     //gets message from file
		caesarFilePrinter.println("\nTHE ORIGINAL MESSAGE: " + messageFromFile + "\n");
		
		System.out.println("\nTHE ORIGINAL MESSAGE: " + messageFromFile + "\n");
		return messageFromFile.toUpperCase();
		
	}
	
	public int getShiftFromFile(Scanner caesarInput, PrintStream caesarFilePrinter) {
		String shiftFromFile = caesarInput.nextLine();     //gets shift from file
		caesarFilePrinter.println("\nTHE SHIFT: " + shiftFromFile + "\n");
		
		System.out.println("\nTHE SHIFT: " + shiftFromFile + "\n");
		return Integer.parseInt(shiftFromFile);
	}
	
	
	public String encodeCaesar(String message, int key, PrintStream caesarFilePrinter) {
		String result = "";
		caesarFilePrinter.println("\nThe shift was " + key + ", so all the letters in the message will be shifted by that amount.");
		caesarFilePrinter.println("\nBased on the shift, the first few letters of the message are transformed like below: \n"); 
		
		System.out.println("\nThe shift was " + key + ", so all the letters in the message will be shifted by that amount.");
		System.out.println("\nBased on the shift, the first few letters of the message are transformed like below: \n"); 
		
		for (int w = 0; w < 5; w++) {
			char c = message.charAt(w);
			int s = message.charAt(w) + key;
			if (message.charAt(w) == ' ') {
				continue; // Checks for spaces and adds spaces to the result
				
			} else if (!Character.isLetter(c)) {
				result += c;
				continue;
			}
			if (s > 90) {
				s = s - 90; 
				s = 65 + (s - 1); // Wraps around the alphabet using ACSII
			}
			caesarFilePrinter.println(c + " ---> " + (char) s);
			
			System.out.println(c + " ---> " + (char) s);
			
		}
		
		for (int i = 0; i < message.length(); i++) { // Examines each char within for loop 
			if (message.charAt(i) == ' ') {
				result = result + " "; // Checks for spaces and adds spaces to the result
				
			} else if (!Character.isLetter(message.charAt(i))) {
				result += message.charAt(i);   //checks for special characters, does not encode them
				continue;
			}
			else {
				int r = message.charAt(i) + key; //adds shift to each character
				if (r > 90) {
					r = r - 90; 
					r = 65 + (r - 1); // Wraps around the alphabet using ACSII
				}
				result = result + (char) r; // Adds each new encrypted character to a final result
			}
			
			
		}
		

		caesarFilePrinter.println("\nYOUR ENCRYPTED MESSAGE: " + result + "\n"); // Prints result
		
		System.out.println("\nYOUR ENCRYPTED MESSAGE: " + result + "\n");
		
		return result; // returns encoded message

	}
	
	
	
	public void decryptCaesar (String encodedCaesarMessage, int key, PrintStream caesarFilePrinter) { 
		String decodedMessage = "";
		caesarFilePrinter.println("\nLet\'s now try to decrypt part of your message!");
		caesarFilePrinter.println("\nBased on the shift of " + key + ", the first few letters of the encoded message are decoded like below: \n");
		
		System.out.println("\nLet\'s now try to decrypt part of your message!");
		System.out.println("\nBased on the shift of " + key + ", the first few letters of the encoded message are decoded like below: \n");
		
		for (int r = 0; r < 5; r++) {
			char h = encodedCaesarMessage.charAt(r);
			int g =  h - key;
			if (encodedCaesarMessage.charAt(r) == ' ') {
				continue; // Checks for spaces and adds spaces to the result
				
			} else if (!Character.isLetter(h)) {
				decodedMessage += h; //checks for special characters, does not decode them
				continue;
			}
			if (g < 65) {
				g -= 65;
				g = 90 - Math.abs((g + 1)); 
				
				// Wraps around the alphabet the other way, using ASCII
			}
			caesarFilePrinter.println(h + " ---> " + (char) g);
			
			System.out.println(h + " ---> " + (char) g);
		}
		
		for (int i = 0; i < encodedCaesarMessage.length(); i++) {
			if (encodedCaesarMessage.charAt(i) == ' ') {  //add spaces if necessary
				decodedMessage += " ";
			} else if (!Character.isLetter(encodedCaesarMessage.charAt(i))) {
				decodedMessage += encodedCaesarMessage.charAt(i);  //skip special characters if needed
			} else {
				int c = encodedCaesarMessage.charAt(i) - key; //decrease encoded characters by the shift
				
				if (c < 65) {
					c -= 65;
					c = 90 - Math.abs((c + 1)); 
					
					// Wraps around the alphabet the other way, using ASCII
				}
				
				decodedMessage += (char) c;  //appends decoded character to end of string
			}
		}
		
		caesarFilePrinter.println("\nYOUR DECRYPTED MESSAGE: " + decodedMessage + "\n");  //print result
		
		System.out.println("\nYOUR DECRYPTED MESSAGE: " + decodedMessage + "\n");
	}
}
	
	
