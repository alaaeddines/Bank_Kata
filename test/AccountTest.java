import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void testDepositIncreasesBalance() {
        Account account = new Account();
        int depositAmount = 100;

        account.deposit(depositAmount);

        assertEquals(depositAmount, account.getBalance());
    }

    @Test
    public void testDepositThrowsExceptionForNegativeAmount() {
        Account account = new Account();
        int invalidAmount = -50;

        assertThrows(IllegalArgumentException.class, () -> account.deposit(invalidAmount));
    }

    @Test
    public void testWithdrawDecreasesBalance() {
        Account account = new Account();
        int initialBalance = 1000;
        int withdrawAmount = 200;

        account.deposit(initialBalance);
        account.withdraw(withdrawAmount);

        assertEquals(initialBalance - withdrawAmount, account.getBalance());
    }

    @Test
    public void testWithdrawThrowsExceptionForNegativeAmount() {
        Account account = new Account();
        int invalidAmount = -50;

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(invalidAmount));
    }

    @Test
    public void testWithdrawThrowsExceptionForInsufficientFunds() {
        Account account = new Account();
        int insufficientAmount = 1000;

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(insufficientAmount));
    }

    @Test
    public void testPrintStatementShowsTransactions() {
        //to capture the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        Account account = new Account();
        int deposit1 = 1000;
        int deposit2 = 500;
        int withdrawal = 200;

        account.deposit(deposit1);
        account.deposit(deposit2);
        account.withdraw(withdrawal);


        account.printStatement();
        System.setOut(originalOut);

        String capturedOutput = baos.toString();

        // Assertions
        assertTrue(capturedOutput.contains("1000"));
        assertTrue(capturedOutput.contains("1500"));
        assertTrue(capturedOutput.contains("-200"));
        assertTrue(capturedOutput.contains("1300"));


    }
}
