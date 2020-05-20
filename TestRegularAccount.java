/**
 * Test regular account
 */
import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestRegularAccount.
 *
 * @author  (Haidar)
 * @version (2019)
 */
public class TestRegularAccount
{
    /**
     * Default constructor for test class TestRegularAccount
     */
    public TestRegularAccount()
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
        Account myAccount = new Account("Haidar", 1052382734, myBalance);
        
        assertEquals("Error in testCreate", "Haidar", myAccount.getName());
        assertEquals("Error in testCreate", 1052382734 , myAccount.getAcctNumber());
        assertEquals("Error in testCreate", myBalance, myAccount.getBalance());
    }
    
    /**
     * Test the deposit method 
     */
    @Test
    public void testDeposit()
    {
        Money myBalance = new Money(300,50);
        Account myAccount = new Account("Haidar", 1052382734, myBalance);
        
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
        Account myAccount = new Account("Haidar", 1052382734, myBalance);
        Money myWithdraw = new Money (100, 0);
        
        myAccount.withdraw(myWithdraw.getTotalCents(), 0);

        Money expectedAmount = new Money (300, 50);
        Money actualAmount = myAccount.getBalance();

        assertTrue ("Error in testWithdraw", actualAmount.equals(expectedAmount));
        
    }
    
    /**
     * Test the transfer method 
     */
    @Test
    public void testTransfer()
    {
       Money myBalance = new Money(300,50); // This is your initial balance - $300.50
       
       Money zahrahBalance = new Money(200,50); // This Zahrah's balance - $200.50
       
       Money transferMoney = new Money(100,0); // Transfering amount - $100.0
       
       Money expectedMyBalance = new Money(400,50); // Your expected balance after transfering $300.50 - $100.0 = $200.50
       
       Account myAccount = new Account("Haidar", 1052382734 , myBalance ); // Here you created a new account (You just need to send name, id and balance. Note balance should be money object )
       
       Account otherAccount = new Account("Zahrah" , 1234567890, zahrahBalance );
       
       myAccount.transfer(otherAccount, transferMoney.getTotalCents(), 0); // Here you are transfering money from your account to Other Account(in this case other account is your wifi's account)
       
       assertTrue("Error in testTransfer", myAccount.getBalance().equals(expectedMyBalance)); //Test case testing your balance after transfering 
        
       // Create new expected MONEY object for your wife 
       // Create new test case and test otherAccount balance
       
       Money expectedZahrahBalance = new Money(100, 50);
       
       assertTrue("Error in testTransfer", otherAccount.getBalance().equals(expectedZahrahBalance));  
        
    }
    
    /**
     * Test the toString method 
     */
    @Test
    public void testToString()
    {  
       Money myBalance = new Money (300,50);
       Account myAccount = new Account("Haidar", 1052382734, myBalance);
       
       String expected = "1052382734	Haidar	$30,050.00";
        
      
       assertEquals("Error in testToString" ,expected, myAccount.toString());
        
    }
    
    /**
     * Test the compareTo method 
     */
    @Test
    public void testCompareTo()
    {
       Money myBalance = new Money (300,50);
       Account myAccount = new Account("Haidar", 1052382734, myBalance);
       Money otherBalance = new Money (300,50);
       Account otherAccount = new Account("Haidar", 1052382734, myBalance);
       
       assertTrue("Error in testEquals", myAccount.compareTo(otherAccount) == 0);
       
       Money myBalance1 = new Money (300,50);
       Account myAccount1 = new Account("Haidar", 1052382734, myBalance);
       Money otherBalance1 = new Money (200,50);
       Account otherAccount1 = new Account("Zahrah", 123456789, myBalance);
      
       assertFalse("Error in testEquals", myAccount1.compareTo(otherAccount1) < 0);
       
    }
}