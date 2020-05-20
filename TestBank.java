/**
 * Test Bank
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBank {
	
	 /**
     * Default constructor for test class TestBank
     */
    public TestBank()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {

    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testToCreate()
    {
        Bank myBank = new Bank("Bank of America");   
        assertEquals("Error in testCreate", "Bank of America", myBank.getName());
    }

    /**
     * Test the addAccount method 
     */
    @Test
    public void testAddAccount()
    {
        Bank myBank = new Bank("Bank of America");
        Money myBalance = new Money(400,50);
        Account myAccount = new Account("Haidar", 1052382734, myBalance);
        myBank.addAccount(myAccount);

        assertEquals("Error in testAddAccount", myAccount, myBank.search(1052382734));
    }

    /**
     * Test the search method 
     */
    @Test
    public void testSearch()
    {

        Bank myBank = new Bank("Bank of America");
        Money myBalance = new Money(400,50);
        Account myAccount = new Account("Haidar", 1052382734, myBalance);
        myBank.addAccount(myAccount);

        Account accountJasur = new Account("Jasur", 1234567892, new Money(500,00));
        
        myBank.addAccount(accountJasur);
        Account actual = myBank.search(1052382734);
        Account expected = myAccount;

        assertEquals(expected, actual);

        assertEquals("Error in search", myAccount, myBank.search(1052382734));
    }

    /**
     * Test the deposit method 
     */
    @Test
    public void testDeposit()
    {
        Bank myBank = new Bank("Bank of America");
        Money myBalance = new Money(400,50);
        Account myAccount = new Account("Haidar", 1052382734, myBalance);
        myBank.addAccount(myAccount);
        Money myDeposit = new Money(200,50);
        myBank.deposit(1052382734, myDeposit.getTotalCents());
        myAccount.deposit(myDeposit.getTotalCents());

        assertEquals("Error in search", myAccount, myBank.search(1052382734));
    }

    /**
     * Test the withdraw method 
     */
    @Test
    public void testWithdraw()
    {
        Bank myBank = new Bank("Bank of America");
        Money myBalance = new Money(400,50);
        Account myAccount = new Account("Haidar", 1052382734, myBalance);
        myBank.addAccount(myAccount);
        Money myWithdraw = new Money(200,50);
        myBank.withdraw(1052382734, myWithdraw.getTotalCents());
        myAccount.deposit(myWithdraw.getTotalCents());

        assertEquals("Error in search", myAccount, myBank.search(1052382734));

    }

    /**
     * Test remove account
     */
    @Test
    public void testRemoveAccount()
    {
        Bank myBank = new Bank("Bank of America");
        Money myBalance = new Money(400,50);
        Account myAccount = new Account("Haidar", 1052382734, myBalance);
        Account myAccount2 = new Account("Zahrah2", 1052382735, myBalance);
        
        //checking account
        Checking myAccount3 = new Checking("Wasn3", 1052382736 ,myBalance, new Money(400,50));
        
        myBank.addAccount(myAccount);
        myBank.addAccount(myAccount2);
        myBank.addAccount(myAccount3);

        myBank.removeAccount(1052382734);
        assertNull(myBank.search(1052382734)); 
        
        myBank.removeAccount(1052382736);
        assertNull(myBank.search(1052382736)); 
    }
}
