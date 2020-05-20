/**
 * Test regular account
 */
import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestCheckingAccount.
 *
 * @author  (Haidar && Waleed)
 * @version (2019)
 */
public class TestCheckingAccount
{
    /**
     * Default constructor for test class TestRegularAccount
     */
    public TestCheckingAccount()
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
        Money myBalance = new Money (300,50);
        Money overdraftMaximum = new Money (10,50);
        Checking myAccount = new Checking("Haidar", 1052382734, myBalance, overdraftMaximum);
        
        assertEquals("Error in testCreate", "Haidar", myAccount.getName());
        assertEquals("Error in testCreate", 1052382734 , myAccount.getAcctNumber());
        assertEquals("Error in testCreate", myBalance, myAccount.getBalance());
        assertEquals("Error in testCreate", overdraftMaximum, myAccount.getOverdraftMaximum());
    }
    
    /**
     * Test the deposit method 
     */
    @Test
    public void testDeposit()
    {
        Money myBalance = new Money(300,50);
        Money overdraftMaximum = new Money (10,50);
        Checking myAccount = new Checking("Haidar", 1052382734, myBalance, overdraftMaximum);
        
        Money myDeposit = new Money (100, 0);

        myAccount.deposit(myDeposit.getTotalCents());

        // Expected result is $400.50
        Money expectedAmount = new Money (400, 50);
        Money actualAmount = myAccount.getBalance();

        assertTrue ("Error in testDeposit", actualAmount.equals(expectedAmount));
        
    }
    
    /**
     * Test the withdraw method 
     */
    @Test
    public void testWithdraw()
    {
        Money myBalance = new Money(400,50);
        Money overdraftMaximum = new Money (10,50);
        Checking myAccount = new Checking("Haidar", 1052382734, myBalance, overdraftMaximum);
        Money myWithdraw = new Money (100, 0);
        
        myAccount.withdraw(myWithdraw.getTotalCents(), 0);

        Money expectedAmount = new Money (300, 50);
        Money actualAmount = myAccount.getBalance();

        assertTrue ("Error in testWithdraw", actualAmount.equals(expectedAmount));
        
        //withdraw more
        myWithdraw = new Money (300, 50);
        myAccount.withdraw(myWithdraw.getTotalCents(), 0);

        expectedAmount = new Money (0, 0);
        actualAmount = myAccount.getBalance();
        
        assertTrue ("Error in testWithdraw", actualAmount.equals(expectedAmount));
        
       //withdraw more
        myWithdraw = new Money (10,50);
        myAccount.withdraw(myWithdraw.getTotalCents(), 0);

        expectedAmount = new Money (-10, -50);
        actualAmount = myAccount.getBalance();
        
        assertTrue ("Error in testWithdraw", actualAmount.equals(expectedAmount));
    }
}