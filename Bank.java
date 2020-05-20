/**
 * Bank represents the Bank ATM
 */
public class Bank {

    //name of the bank
    private String nameOfBank;
    
    //list of accounts

    private ListReferenceBased accounts;

    //number of accounts in banks
    private int numOfAccounts; 

    /**
     * Constructor for objects of class Bank
     */
    public Bank(String theBank)
    {
        // initialise instance variables
        numOfAccounts=0;
        this.nameOfBank = theBank;
        accounts = new ListReferenceBased();
    }

    /**
     * add account to end of list
     * @param newAccount new account
     */
    public boolean addAccount(Account newAccount)
    {
        //check duplicate
        if (search(newAccount.getAcctNumber()) != null){
            return false;
        }
        accounts.add(numOfAccounts, newAccount);
        numOfAccounts++;

        return true;
    }

    /**
     * remove account
     * @param account number
     */
    public boolean removeAccount(long accountNumber)
    {
        for(int i = 0; i< numOfAccounts; i++)
        {
            Account acc = (Account)accounts.get(i);

            if(accountNumber == acc.getAcctNumber()){
                accounts.remove(i);
                numOfAccounts--;
                return true;
            }
        }
        return false; //not found
    }

    /**
     * get the name of bank
     * @return bank name
     */
    public String getName()
    {
        return this.nameOfBank;
    }

    /**
     * search account by account number
     * @param accNumber account number
     * @return account with account number or null if not found
     */
    public Account search(long accNumber)
    {
        for(int i = 0; i< numOfAccounts; i++)
        {
            Account acc = (Account)accounts.get(i);

            if(accNumber == acc.getAcctNumber()){
                return acc;
            }
        }
        return null; //not found
    }

    /**
     * deposit into account with account number
     * @param accNumber account number
     * @param depositMoney money
     * @return true/false
     */
    public boolean deposit(long accNumber, long depositMoney)
    {
        //check positive
        if (depositMoney < 0){
            return false;
        }

        Account acc = search(accNumber);
        if(acc == null)
        {
            //do nothing
            return false;
        }
        else{

            Money balance = acc.getBalance();

            //true if balance is different
            if (balance.equals(acc.deposit(depositMoney))){
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     * withdraw from account with account number
     * @param accNumber account number
     * @param depositMoney money
     */
    public boolean withdraw(long accNumber, long withdrawMoney) throws InsufficientFundsException
    {
        //check positive
        if (withdrawMoney < 0){
            return false;
        }

        Account acc = search(accNumber);
        if(acc == null)
        {
            //do nothing
            return false;
        }
        else{

            Money balance = acc.getBalance();

            //true if balance is different
            if (balance.equals(acc.withdraw(withdrawMoney, 0))){
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     * get number of accounts
     * @return number of accounts
     */
    public int getNumOfAccounts() {
        return numOfAccounts;
    }

    /**
     * sort and return array of sorted accounts
     * @return array of sorted accounts
     */
    public Account[] getSortedAccounts(){

        Account[] accountArray = new Account[numOfAccounts];

        //put account to array
        for (int i = 0; i < accountArray.length; i++){
            accountArray[i] = (Account)accounts.get(i);
        } 

        //sort
        SortsClass.bubbleSort(accountArray, accountArray.length);

        return accountArray;
    }
} 
