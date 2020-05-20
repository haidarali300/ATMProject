//********************************************************************
//  Account.java       Author: Lewis/Loftus
//
//  Represents a bank account with basic services such as deposit
//  and withdraw.
//  Update:
//  The account can be compared together by account number
//  This account is regular account
//
//  author (Haidar && Waleed)
//  version (2019)
//********************************************************************

import java.text.NumberFormat;

public class Account implements Comparable<Account>
{
   private static final double RATE = 0.035;  // interest rate of 3.5%

   /**
    * account number
    */
   private long acctNumber;
   
   /**
    * current balance
    */
   protected Money balance;
   
   /**
    * account name
    */
   private String name;

   //-----------------------------------------------------------------
   //  Sets up the account by defining its owner, account number,
   //  and initial balance.
   //-----------------------------------------------------------------
   public Account (String owner, long accountNum, Money initial)
   {
      this.name = owner;
      this.acctNumber = accountNum;
      this.balance = initial;
   } 

   //-----------------------------------------------------------------
   //  This was not required for the homework, but is just to demonstrate that you may define more than one constructor.
   //  Note this constructor has 2 parameters whereas the previous constructor has 3 parameters.
   //  Sets up the account by defining its owner and account number based on values received as parameters;
   //  initial balance is set to 0.00 .
   //-----------------------------------------------------------------
   public Account (String owner, long accountNum)
   {
      this.name = owner;
      this.acctNumber = accountNum;
      this.balance = new Money(0);
   } 
   
   //-----------------------------------------------------------------
   //  Deposits the specified amount into the account. Returns the
   //  new balance.
   //-----------------------------------------------------------------
   public Money deposit (long theCents)
   {
	  balance = balance.add(new Money(theCents));

      return this.balance;
   } 

   //-----------------------------------------------------------------
   //  Withdraws the specified amount from the account and applies
   //  the fee. Returns the new balance.
   //-----------------------------------------------------------------
   public Money withdraw (long theCents, int fee)
   {
	  if(balance.compareTo(new Money(theCents)) == -1)
      {
           throw new InsufficientFundsException("");
      }
	   
	  balance = balance.sub(new Money(theCents + fee));

      return this.balance;
   } 

   //-------------------------------------------------------------------------------------------
   //  Transfers the specified amount from the account specified as a parameter to this account, 
   //  by performing a withdrawal on the account specified as a paramater,
   //  and a deposit on this account.
   //  The specified fee is charged to the account from which the amount is withdrawn. 
   //-------------------------------------------------------------------------------------------
   public void transfer (Account acctToWithdrawFrom, long amountToTransfer, int fee) 
   {
       acctToWithdrawFrom.withdraw (amountToTransfer, fee);
       
       this.deposit (amountToTransfer);
   }
   
   //-----------------------------------------------------------------
   //  Adds interest to the account and returns the new balance.
   //-----------------------------------------------------------------
   public Money addInterest ()
   {
	  Money itererest = new Money((long)(balance.getTotalCents() * RATE));
      this.balance = balance.add(itererest);
      
      return this.balance;
   }

   //-----------------------------------------------------------------
   //  Returns the current balance of the account.
   //-----------------------------------------------------------------
   public Money getBalance ()
   {
      return this.balance;
   }

   //-----------------------------------------------------------------
   //  Returns a one-line description of the account as a string.
   //-----------------------------------------------------------------
   public String toString ()
   {
      NumberFormat fmt = NumberFormat.getCurrencyInstance();

      return (this.acctNumber + "\t" + this.name + "\t" + fmt.format(this.balance.getTotalCents()));
   }

	@Override
	public int compareTo(Account o) {
		if (acctNumber < o.acctNumber){
			return -1;
		}else if (acctNumber > o.acctNumber){
			return 1;
		}else{
			return 0;
		}
	}

	/**
	 * getter of account number
	 * @return account number
	 */
	public long getAcctNumber() {
		return acctNumber;
	}

	/**
	 * get name of account
	 * @return the name of account
	 */
	public String getName() {
		return name;
	}
} 
 