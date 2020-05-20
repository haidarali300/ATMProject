/**
 * Manage output account to file
 * 
 * author (Haidar && Waleed)
 * version (2019)
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputManager
{
	// Method: writeAccountTo
    // Precondition: writer is a PrintWriter object, already set up
    // to write accounts to file
    // Postcondition: accounts are written to file
    // Assumption: Account data will be in the format of: name,id,balance
	// accounts is not null
    public static void writeAccountTo (Account[] accounts, PrintWriter writer) throws IOException
    {
    	for (int i = 0; i < accounts.length; i++){
    		// Get account data (i.e. name, accountNum and balance) from oneLine
            String name = accounts[i].getName();
            long accountNum = accounts[i].getAcctNumber();
            long balance = accounts[i].getBalance().getTotalCents();
            
            if (accounts[i] instanceof Checking){
            	long overdraftMaximum =  ((Checking)accounts[i]).getOverdraftMaximum().getTotalCents();
            	writer.println(accountNum + "," + name + "," + balance
            			 + ",c," + overdraftMaximum);	
            }else{ //regular account
            	writer.println(accountNum + "," + name + "," + balance + ",r");	
            }
    	}
    	
    } // end writeAccountTo
    
} // end OutputManager