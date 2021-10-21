/**
 * 
 */
import java.util.*;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.security.*;
/**
 * @author dilip
 *
 */
public class KeyGenerator {
	
	public PublicKey publicKey; 
	public PrivateKey privateKey; 
	public String message; 
	public byte [] encryptedText;
	public byte [] decryptedText;
	
	public KeyGenerator() throws NoSuchAlgorithmException {		//initialize KeyPairGenerator, specify which algorithm to use
		KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
		gen.initialize(2048);
		KeyPair keyPair = gen.generateKeyPair();		//generate key pair
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
	}		//get and assign public and private keys
	
	public void cipherInfo (PrintStream RSAprint) {		//RSA Cipher background
		System.out.println("\n\n\nRSA CIPHER OVERVIEW\n");
		System.out.println("\nThe RSA Cipher encryption was invented in the 1970s, but is still widely used today for software encryption");
		System.out.println("It relies on asymmetric key encryption/decryption: encryption with a public key, and decryption with your private key");
		System.out.println("It is the most complicated cipher you will learn about; it works for all types of characters");
		System.out.println("It is a combination of the Polygram Cipher and the Vigenere Cipher in some ways: it has a key like the "
			+ "Vigenere Cipher, but encrypts all characters like the Polygram Cipher");
		System.out.println("\nLet's see how it works!\n");
	}
	
	
	public String getMessage(Scanner RSAinput) {		//get file message
		message = RSAinput.nextLine();
		return message;
	}
	
	public String getUserMessage (Scanner console, PrintStream RSAprint) {		//get user
		System.out.println("\nEnter your message for the RSA Cipher, or type quit to exit: ");
		message = console.nextLine();
		RSAprint.println("\nYOUR ORIGINAL MESSAGE " + message + "\n");
		
		return message;
	}
	
	public PublicKey getPublic (PublicKey publicKey, PrintStream RSAprint) {		//get Public Key and output to file
		RSAprint.println("\nThis is the public key. It can be used by anyone (hence the name "
				+ "\'public\'), and is used to electronically encrypt messages.\n");
		RSAprint.println("\nPUBLIC KEY: \n");
		System.out.println("\nThis is the public key. It can be used by anyone (hence the name "
				+ "\'public\'), and is used to electronically encrypt messages.\n");
		System.out.println("\nPUBLIC KEY: \n");
		RSAprint.println("\n" + publicKey.toString() + "\n");
		System.out.println("\n" + publicKey.toString() + "\n");
		return publicKey;
		
	}
	
	public PrivateKey getPrivate (PrivateKey privateKey, PrintStream RSAprint) {		//get Private Key and output to file
		RSAprint.println("\nThis is your private key. It is known only to you (hence the name "
				+ "\'private\'), and is used to electronically decrypt messages you receive.\n");
		RSAprint.println("\nPRIVATE KEY: \n");
		
		System.out.println("\nThis is your private key. It is known only to you (hence the name "
				+ "\'private\'), and is used to electronically decrypt messages you receive.\n");
		System.out.println("\nPRIVATE KEY: \n");
		
		RSAprint.println("\n" + privateKey.toString() + "\n");
		System.out.println("\n" + privateKey.toString() + "\n");
		return privateKey; 
	}
	
	
	public String encrypt(String message2, PublicKey publicKey, PrintStream RSAprint) throws InvalidKeyException {		//encryption algorithm
		Cipher encryption = null;
		encryptedText = null;
		RSAprint.println("\n\nTHE PLAINTEXT MESSAGE: " + message2 + "\n");
		RSAprint.println("\n\n\nSTEP-BY-STEP EXPLANATION - ENCRYPTION\n");
		RSAprint.println("\n1. First, acquire your public key and message (public key shown above)");
		RSAprint.println("\n2. Next the RSA Algorithm does the work for you!\n");
		
		
		System.out.println("\n\nTHE PLAINTEXT MESSAGE: " + message2 + "\n");
		System.out.println("\n\n\nSTEP-BY-STEP EXPLANATION - ENCRYPTION\n");
		System.out.println("\n1. First, acquire your public key and message (public key shown above)");
		System.out.println("\n2. Next the RSA Algorithm does the work for you!\n");
		try {
			encryption = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		encryption.init(Cipher.ENCRYPT_MODE, publicKey);
		
		try {
			 encryptedText = encryption.doFinal(this.message.getBytes("UTF-8"));		//get bytes of each character in original message
			 RSAprint.println("\nThe RSA encryption algorithm gets the bytes of your message. "
			 		+ "The bytes are how the computer reads your text\n");
			 RSAprint.println("\nYour message's bytes: " + Arrays.toString(encryptedText) + "\n");
			 
			 System.out.println("\nThe RSA encryption algorithm gets the bytes of your message. "
				 		+ "The bytes are how the computer reads your text\n");
				 System.out.println("\nYour message's bytes: " + Arrays.toString(encryptedText) + "\n");
		} catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RSAprint.println("\nYOUR ENCRYPTED MESSAGE: " + Base64.getEncoder().encodeToString(encryptedText) + "\n");
		
		System.out.println("\nYOUR ENCRYPTED MESSAGE: " + Base64.getEncoder().encodeToString(encryptedText) + "\n");
		
		return Base64.getEncoder().encodeToString(encryptedText);		//return encrypted byte array in string form...all in one line
		
		
	}
	
	
	public byte[] decrypt(PrivateKey privateKey, PrintStream RSAprint, String encryptedText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
		//decryption algorithm
		RSAprint.println("\nNow, let's decrypt your message!");
		
		System.out.println("\nNow, let's decrypt your message!");
		
		decryptedText = Base64.getDecoder().decode(encryptedText);		//get encrypted String, returns a byte array
		RSAprint.println("\n\n\nSTEP-BY-STEP EXPLANATION - DECRYPTION\n");
		RSAprint.println("\n1. First, get your private key. It is only known to you");
		RSAprint.println("\n2. Now, watch the RSA decryption algorithm go to work!\n");
		
		System.out.println("\n\n\nSTEP-BY-STEP EXPLANATION - DECRYPTION\n");
		System.out.println("\n1. First, get your private key. It is only known to you");
		System.out.println("\n2. Now, watch the RSA decryption algorithm go to work!\n");

        Cipher decryption = Cipher.getInstance("RSA");
        decryption.init(Cipher.DECRYPT_MODE, privateKey);		//decrypt using Private key
        
        RSAprint.println("\nThe algorithm gets the bytes from your encrypted message and get's THEIR bytes");
        RSAprint.println("\nEncrypted message bytes: " + Arrays.toString(decryptedText) + "\n");
        
        System.out.println("\nThe algorithm gets the bytes from your encrypted message and get's THEIR bytes");
        System.out.println("\nEncrypted message bytes: " + Arrays.toString(decryptedText) + "\n");
        
        return decryption.doFinal(decryptedText);		//return decrypted bytes
        
	}
	
	public String quit (Scanner console) {		//quit feature question
		System.out.println("\nThere is one more cipher left. If you would like to exit, type 'quit'. If not type 'continue': ");
		String quit = console.nextLine();
		return quit;
	}
	
	public void invoker(String[] args) throws FileNotFoundException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException {
		Scanner console = new Scanner(System.in);
		KeyGenerator gen = null;
		Scanner RSAinput = new Scanner (new File("rsa.in"));
		File RSAoutput = new File("rsa.out");
		PrintStream RSAprint = new PrintStream(RSAoutput.toString());
		gen = new KeyGenerator();
		//String exitMessage = gen.quit(console);
		gen.cipherInfo(RSAprint);
		while (RSAinput.hasNext()) {		//loop for file messages
			try {
				gen = new KeyGenerator();
				message = gen.getMessage(RSAinput);
				RSAprint.println("\nORIGINAL MESSAGE: " + message + "\n");
				System.out.println("\nORIGINAL MESSAGE: " + message + "\n");
				gen.getPublic(publicKey, RSAprint);
				gen.getPrivate(privateKey, RSAprint);
				try {
					String encryptedTextString = gen.encrypt(message, publicKey, RSAprint);
					byte[] originalMessage = gen.decrypt(privateKey, RSAprint, encryptedTextString);
					String a = new String(originalMessage, StandardCharsets.US_ASCII);		//take in decrypted byte array, take ASCII values
																							//return a string, output to file
					RSAprint.println("\nFinally, the algorithm converts the bytes to ASCII, and converts each ASCII value into a character!\n");
					RSAprint.println("\n\nYOUR DECRYPTED MESSAGE: " + a + "\n\n");
					
					System.out.println("\nFinally, the algorithm converts the bytes to ASCII, and converts each ASCII value into a character!\n");
					System.out.println("\n\nYOUR DECRYPTED MESSAGE: " + a + "\n\n");
					
					if (RSAinput.hasNext()) {
						System.out.println("\nType 'yes' if you would like to view the next example, or type 'no' to try this cipher yourself! ");
						String moveOnMessage = console.nextLine();
						
						if (moveOnMessage.equalsIgnoreCase("no")) {
							break;
						}
					}
				} catch (InvalidKeyException | NullPointerException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			//re-call methods for user console message
		message = gen.getUserMessage(console, RSAprint);
		System.out.println("\nCheck the rsa.out if you need the explanations again!");
		RSAprint.println("\nORIGINAL MESSAGE: " + message + "\n");
		gen.getPublic(publicKey, RSAprint);
		gen.getPrivate(privateKey, RSAprint);
		String encryptedTextString = gen.encrypt(message, publicKey, RSAprint);
		byte[] originalMessage = null;
		try {
			originalMessage = gen.decrypt(privateKey, RSAprint, encryptedTextString);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String a = new String(originalMessage, StandardCharsets.US_ASCII);
		RSAprint.println("\nFinally, the algorithm converts the bytes to ASCII, and converts each ASCII value into a character!\n");
		RSAprint.println("YOUR DECRYPTED MESSAGE: " + a + "\n");
		
		System.out.println("\nFinally, the algorithm converts the bytes to ASCII, and converts each ASCII value into a character!\n");
		System.out.println("YOUR DECRYPTED MESSAGE: " + a + "\n");
		
		
		
		
		
	}

}
