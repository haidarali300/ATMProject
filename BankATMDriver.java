import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * BankATMDriver is the driver
 * that allows users to login, check balance, deposit, withdraw
 * ...
 */
public class BankATMDriver {

    /**
     * input accounts file name
     */
    private static final String INPUT_FILENAME = "in_accounts.txt";

    /**
     * output accounts file name
     */
    private static final String OUTPUT_FILENAME = "out_accounts.txt";

    /**
     * main method to start Java application
     * @param args main arguments
     */
    public static void main(String[] args) {

        //create a Bank
        Bank bank = new Bank("Bank ATM");

        try {
            //load account
            Scanner inputSource = new Scanner(new File(INPUT_FILENAME));

            //read accounts
            while (inputSource.hasNextLine()){
                bank.addAccount(InputManager.readOneAccountFrom(inputSource));
            }

            //close file
            inputSource.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not read input file " + INPUT_FILENAME);
        }

        do{

            //Ask the user to login by entering id, using a dialog box or standard input.
            Long accNumber = readLong("Please enter account number:");

            //user entered account
            if (accNumber != null){

                //search account of bank
                Account account = bank.search(accNumber);

                //account not found
                if (account == null){
                    JOptionPane.showMessageDialog(null, "Account not found!");
                }else{

                    //display message and read user selection
                    Long userSelection = menu();
                    while (userSelection != null && userSelection != 0){

                        if (userSelection == 1){ //check balance

                            Money money = account.getBalance();

                            JOptionPane.showMessageDialog(null, money);

                        }else if (userSelection == 2){ //deposit

                            Long theCents = readLong("Please enter the amount (cents) to deposit: ");
                            if (theCents != null){
                                if (bank.deposit(accNumber, theCents)){
                                    JOptionPane.showMessageDialog(null, "Deposit successfully!");
                                }else{
                                    JOptionPane.showMessageDialog(null, "Could not deposit!");
                                }
                            }

                        }else if (userSelection == 3){ //withdraw
                            Long theCents = readLong("Please enter the amount (cents) to withdraw: ");
                            if (theCents != null){
                                try{
                                    if (bank.withdraw(accNumber, theCents)){
                                        JOptionPane.showMessageDialog(null, "Withdraw successfully!");
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Could not withdraw!");
                                    }
                                }catch(InsufficientFundsException ex){
                                    JOptionPane.showMessageDialog(null, "There is insufficient fund to withdraw!");
                                }
                            }
                        }

                        //display message and read user selection
                        userSelection = menu();
                    }
                }
            }	

            //ask for new account number
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue with new account?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response != JOptionPane.YES_OPTION) {
                break;
            }
        }while (true);

        //sort
        Account[] accounts = bank.getSortedAccounts();

        //write to output file
        try {
            PrintWriter writer = new PrintWriter(OUTPUT_FILENAME);

            OutputManager.writeAccountTo(accounts, writer);

            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not write to output file " + OUTPUT_FILENAME);
        }
    }

    /**
     * display menu and return user selection
     * @return user selection
     */
    public static Long menu(){

        String message = "Please choice:\n";
        message += "1. Check balance\n";
        message += "2. Deposit\n";
        message += "3. Withdraw\n";
        message += "0. Exit\n";

        return readLong(message);
    }

    /**
     * read a long number from standard input
     * @param message message
     * @return Long or null if invalid input
     */
    public static Long readLong(String message){

        Long number = null; //input number

        String input = JOptionPane.showInputDialog(message);
        while (input != null){
            try{
                number = Long.parseLong(input);
                break;
            }catch(NumberFormatException ex){
                input = JOptionPane.showInputDialog("Invalid input. " + message);
            }
        }
        return number;
    }
}  
