
/**
 * Write a description of class Checking here.
 *
 * @author (Haidar && Waleed)
 * @version (2019)
 */
public class Checking extends Account
{
    /**
     * overdraft maximum
     */
    private Money overdraftMaximum;

    public Checking(String theName, long theAccNumber, Money theBalance, Money max ){
        super(theName, theAccNumber, theBalance);
        this.overdraftMaximum = max;
    }

    //-----------------------------------------------------------------
    //  Withdraws the specified amount from the account and applies
    //  the fee. Returns the new balance.
    //-----------------------------------------------------------------
    public Money withdraw (long theCents, int fee)
    {
        Money totalWithdrawOverdraft = super.getBalance().add(this.overdraftMaximum);

        if(totalWithdrawOverdraft.compareTo(new Money(theCents)) == -1)
        {
            throw new InsufficientFundsException("");
        }
        else
        {
            balance = balance.sub(new Money(theCents + fee));

            return this.balance;
        }
    }

    /**
     * get overdraft maximum
     * @return overdraft maximum
     */
    public Money getOverdraftMaximum() {
        return overdraftMaximum;
    }

}
