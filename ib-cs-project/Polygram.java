/**
 * 
 */
import java.util.*;
import java.io.*;
/**
 * @author dilip
 *
 */
public class Polygram {
	
	String originalMessage;
	StringBuilder encryptedMessage;
	StringBuilder decryptedMessage;
	String shifts;
	
	public Polygram () {
		
	}
	
	public void cipherInfo(PrintStream polyOutput) {                        //historical background on Polygram Cipher
		System.out.println("\nPOLYGRAM CIPHER OVERVIEW\n");
		System.out.println("The polygram cipher is a cipher invented in 1929");
		System.out.println("Instead of taking individual characters to encrypt, it encrypts by "
				+ "GROUPS of letters!");
		System.out.println("This makes the cipher more complicated, since now the same SET of "
				+ "characters can be encrypted in multiple ways!");
		System.out.println("Also, with this cipher, now SPECIAL CHARACTERS and SPACES could be encrypted in addition to letters!");
		System.out.println("A version of this cipher was invented by Lester Hill, now known as the Hill Cipher");
		System.out.println("\nLet's TRY IT OUT now!\n");
	}
	
	public String getPolygramMessage(Scanner polyInput, PrintStream polyOutput) {     //get message from file
		originalMessage = polyInput.nextLine();
		polyOutput.println("\n\nORIGINAL MESSAGE: " + originalMessage + "\n");
		
		System.out.println("\n\nORIGINAL MESSAGE: " + originalMessage + "\n");
		return originalMessage;
	}
	
	public String getShifts(Scanner polyInput) {          //get input file shifts
		shifts = polyInput.nextLine();
		return shifts; 
	}
	
	public String getUserPolyMessage (Scanner input) {            //get user console message
		System.out.println("\nEnter your message for the Polygram Cipher, or enter quit to exit: ");
		String userMessage = input.nextLine();
		System.out.println("\nYOUR MESSAGE: " + userMessage + "\n");
		return userMessage;
	}
	
	public String getUserShifts(Scanner input) {                //get user shifts
		System.out.println("\nYour shifts should be numbers between 0 and 26, separated by a space."
				+ " You can have as many as you want!\n");
		System.out.println("\nEnter your shifts here: ");
		String userShifts = input.nextLine();
		System.out.println("\nYOUR SHIFTS: " + userShifts + "\n");
		
		return userShifts;
	}
	
	
	
	
	public StringBuilder encryptPolygram(String polyMessage, String polyShifts, PrintStream polyOutput) {        //encryption algorithm
		encryptedMessage = new StringBuilder();
		String[] splitter = polyShifts.split(" ");			//split shifts string by spaces so we can access shifts individually
		polyOutput.println("\nMessage length: " + polyMessage.length() + " characters");
		polyOutput.println("\nShifts: " + Arrays.toString(splitter));
		
		System.out.println("\nMessage length: " + polyMessage.length() + " characters");
		System.out.println("\nShifts: " + Arrays.toString(splitter));
		int messageLen = polyMessage.length();
		int numGroups = splitter.length;       
		
														//number of given shifts is number how many groups of characters exist
		polyOutput.println("\nNumber of groups: " + numGroups);
		
		System.out.println("\nNumber of groups: " + numGroups);
		int lenOfSubstring = messageLen / numGroups;                    //length of each group found by divding message length by number of groups
		polyOutput.println("\nLength of each group: " + lenOfSubstring);
		
		System.out.println("\nLength of each group: " + lenOfSubstring);
		String substring = "";
		String[] groups = new String[numGroups];
		int start = 0;								//set start index to 0, set end index to length of subtstring because
		int end = lenOfSubstring;					// length of substring is the length of each group
		int count = 0;
		
		while (count < splitter.length - 1) {
				substring = polyMessage.substring(start, end);       //get snippet of string equal to length of group
				groups[count] = substring;                           //add to array
				count++;                                             //increase count to avoid infinite loop
				start += lenOfSubstring;                             
				end += lenOfSubstring;                               //increase both start and end indexes by group length to get group of letters
				
			
			
		}
		substring = polyMessage.substring(start, polyMessage.length());         //get last, longer group of letters
		groups[count] = substring;                                              //number of shifts may not divide message length exactly, 
																				//so must break loop early to 
																				//group the entire message
		
		polyOutput.println("\n\n\nSTEP-BY-STEP EXPLANATION - ENCRYPTION\n");                  //step by step explanation of encryption
		
		polyOutput.println("\n1. First, group your characters by dividing the number of characters in your message by the number of given shifts.");
		polyOutput.println("\n2. Then, shift EACH CHARACTER (including spaces and special characters) by the shift that corresponds to that group");
		
		polyOutput.println("Each group has " + lenOfSubstring + " characters, except for the last group");
		polyOutput.println("Here are the groups: " + Arrays.toString(groups));
		
		polyOutput.println("\nLet's encrypt a few characters!");
		polyOutput.println("\nOn the right are characters from your original message, "
				+ "and on the left are encrypted characters");
		
		System.out.println("\n\n\nSTEP-BY-STEP EXPLANATION - ENCRYPTION\n");                  //step by step explanation of encryption
		
		System.out.println("\n1. First, group your characters by dividing the number of characters in your message by the number of given shifts.");
		System.out.println("\n2. Then, shift EACH CHARACTER (including spaces and special characters) by the shift that corresponds to that group");
		
		System.out.println("Each group has " + lenOfSubstring + " characters, except for the last group");
		System.out.println("Here are the groups: " + Arrays.toString(groups));
		
		System.out.println("\nLet's encrypt a few characters!");
		System.out.println("\nOn the right are characters from your original message, "
				+ "and on the left are encrypted characters");
		int g = 0; char e; int break_count = 0;
		for (int t = 0; t < groups.length; t++) {
			for (int y = 0; y < groups[t].length(); y++) {
				e = groups[t].charAt(y);
				g = e + Integer.valueOf(splitter[t]);
				
				System.out.println("\n" + e + " ---> " + (char) g);
				break_count++;
				if (break_count == 5) {
					break;  //break loop after 5 characters
				}
				
			}
			if (break_count == 5) {
				break;  //break loop after 5 characters
			}
			
			
		}
		
		for (int a = 0; a < groups.length; a++) {              //loop through each group
			for (int b = 0; b < groups[a].length(); b++) {     //loop through each character of each group
				char w = groups[a].charAt(b);					//individual group characters
				int s = w + Integer.valueOf(splitter[a]);		// encryption: add shift to character ASCII value
				encryptedMessage.append((char) s);              //add character and its associated shift, append to end of string
			}
		}
		
		polyOutput.println("\nFinally your encrypted characters are reversed");
		
		System.out.println("\nFinally your encrypted characters are reversed");
		
		encryptedMessage = encryptedMessage.reverse();                      //reverse string
		
		polyOutput.println("\nYOUR ENCRYPTED MESSAGE: " + encryptedMessage + "\n");      
		
		System.out.println("\nYOUR ENCRYPTED MESSAGE: " + encryptedMessage + "\n");      

		
		
		return encryptedMessage;                       //output and return message
	}
	
	
	public void decryptPolygram(PrintStream polyOutput, String polyShifts, StringBuilder encryptedPolyMessage) {		//decryption algorithm
		polyOutput.println("\nLet's decrypt your message now!");
		System.out.println("\nLet's decrypt your message now!");

		decryptedMessage = new StringBuilder();
		StringBuilder temp = new StringBuilder();                      //create temporary StringBuilder to un-reverse message
		polyOutput.println("\nFirst, we have to un-reverse your message");
		System.out.println("\nFirst, we have to un-reverse your message");

		temp = encryptedPolyMessage.reverse(); 						  //un-reverse encrypted message
		polyOutput.println("\nUn-reversed message: " + temp);
		polyOutput.println("\nWe use the same grouping system to decrypt as well!");
		
		System.out.println("\nUn-reversed message: " + temp);
		System.out.println("\nWe use the same grouping system to decrypt as well!");
		String[] splitter = polyShifts.split(" ");
		polyOutput.println("\nMessage length: " + encryptedPolyMessage.length());
		polyOutput.println("\nShifts: " + Arrays.toString(splitter));
		
		System.out.println("\nMessage length: " + encryptedPolyMessage.length());
		System.out.println("\nShifts: " + Arrays.toString(splitter));
		
		
		int messageLen = encryptedPolyMessage.length();                   
		int numGroups = splitter.length;						//number of given shifts is the number of groups needed
		
		polyOutput.println("\nNumber of groups: " + numGroups);
		
		System.out.println("\nNumber of groups: " + numGroups);

		int lenOfSubstring = messageLen / numGroups;			//length of each group found by dividing message length by number of groups
		
		polyOutput.println("\nLength of each group: " + lenOfSubstring);
		
		System.out.println("\nLength of each group: " + lenOfSubstring);

		String substring = "";
		String[] groups = new String[numGroups];
		int start = 0;
		int end = lenOfSubstring;
		int count = 0;
		polyOutput.println("\n\n\nSTEP-BY-STEP EXPLANATION - DECRYPTION\n");               //decryption step-by-step explanation
		
		polyOutput.println("\n1. First, group your characters by dividing the number of characters in your message by the number of given shifts.");
		polyOutput.println("\n2. Then, shift EACH CHARACTER (including spaces and special characters) by the shift that corresponds to that group");
		
		System.out.println("\n\n\nSTEP-BY-STEP EXPLANATION - DECRYPTION\n");               //decryption step-by-step explanation
		
		System.out.println("\n1. First, group your characters by dividing the number of characters in your message by the number of given shifts.");
		System.out.println("\n2. Then, shift EACH CHARACTER (including spaces and special characters) by the shift that corresponds to that group");
		
			while (count < splitter.length - 1) {			//loop through almost all of the message
				substring = temp.substring(start, end);		
															//get snippet of string and add it to array
				groups[count] = substring;
				count++;
				start += lenOfSubstring;
				end += lenOfSubstring;					//increase start and end by length for each group to get next set of characters
		
		}
			substring = encryptedPolyMessage.substring(start, encryptedPolyMessage.length());		//not all shifts will divide message into EXACTLY equal
																									//groups, so end loop one iteration early to get 
																									//last needed group
			groups[count] = substring;
	
			polyOutput.println("\nEach group has " + lenOfSubstring + " characters, except for the last group\n");
			polyOutput.println("\nHere are the groups: " + Arrays.toString(groups) + "\n");
			
			System.out.println("\nEach group has " + lenOfSubstring + " characters, except for the last group\n");
			System.out.println("\nHere are the groups: " + Arrays.toString(groups) + "\n");
			
			int g1 = 0; int break_count = 0;
			
			for (int t = 0; t < groups.length; t++) {
				for (int y = 0; y < groups[t].length(); y++) {
					char l = groups[t].charAt(y);
					g1 = l - Integer.valueOf(splitter[t]);
					polyOutput.println("\n" + l + " ---> " + (char) g1);
					
					System.out.println("\n" + l + " ---> " + (char) g1);

					break_count++;
					
					if (break_count == 5) {
						break;
					}
					
				}
				
				if (break_count == 5) {
					break;
				}
				
				
			}	
		
		for (int e = 0; e < groups.length; e++) {		//loop through array of groups
			for (int f = 0; f < groups[e].length(); f++) {		//loop through each character per group
				char d = groups[e].charAt(f);
				int q = d - Integer.valueOf(splitter[e]);
				decryptedMessage.append((char) q);		//decrease each character by shift, add to end of decrypted StringBuilder
			}
		}
		polyOutput.println("\nYOUR DECRYPTED MESSAGE: " + decryptedMessage + "\n");	
		
		System.out.println("\nYOUR DECRYPTED MESSAGE: " + decryptedMessage + "\n");		//output decrypted message
	}
	
	

}

