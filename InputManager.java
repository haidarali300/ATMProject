/**
 * Manage input to be read from either keyboard or file.
 * 
 *  author (Haidar && Waleed)
 * version (2019)
 */
import java.util.Scanner;

public class InputManager
{
	// Method: readOneAccountFrom
    // Precondition: inputSource is a Scanner object, already set up
    // to read from a text file or standard input source (keyboard).
    // Postcondition: returns an Account with the data read for its attributes.
    // Assumption: Account data will be in the format of: name,id,balance
    public static Account readOneAccountFrom (Scanner inputSource)
    {
    	 Account oneAccount = null;
    	 
        // Read one line of account data into oneLine
        //System.out.println ("Reading: name,id,balance");
        String oneLine = inputSource.nextLine();
        
        // Parse line of account data, separated by commas.
        Scanner lineTokenizer = new Scanner (oneLine);
        lineTokenizer.useDelimiter (",");
        
        // Get account data (i.e. name, accountNum and balance) from oneLine
        long accountNum = lineTokenizer.nextLong ();
        String name = lineTokenizer.next ();         
        long balance = lineTokenizer.nextLong();
        String type = lineTokenizer.next ();       
        
        if (!type.equals("c")){ //regular account
        	// Create and return an Account object with the data read for one   account.
            oneAccount = new Account (name, accountNum, new Money(balance));
        }else{
        	long overdraftMaximum = lineTokenizer.nextLong();
        	oneAccount = new Checking(name, accountNum, new Money(balance), new Money(overdraftMaximum));
        }
        
        //System.out.println ("Account read: " + oneAccount);
        
        return oneAccount;
    } // end readOneAccountFrom
    
} // end InputManager