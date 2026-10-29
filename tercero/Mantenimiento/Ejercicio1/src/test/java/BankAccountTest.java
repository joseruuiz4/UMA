import bank.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class BankAccountTest {
    private static BankAccount bank;

    @BeforeEach
    void setUp() {
        bank = new BankAccount(25);
    }

    @Test
    void WithdrawLowerAmountThanBalance(){
        assertTrue(bank.withdraw(10));
    }

    @Test
    void WithdrawHigherAmountThanBalance(){
        assertFalse(bank.withdraw(30));
    }

    @Test
    void DepositPositiveAmount(){
        int amount = 50;
        int expected = bank.getBalance();
        expected += amount;
        int actual =  bank.deposit(amount);
        assertEquals(expected, actual);
    }

    @Test
    void DepositNegativeAmount(){
        assertThrows(IllegalArgumentException.class, () -> bank.deposit(-10));

    }

    @Test
    void TestGetBalance(){
        int balance = 200;
        bank = new BankAccount(balance);
        int actual = bank.getBalance();
        assertEquals(balance, actual);
    }

    @Test
    void TestPayment(){
        //Arrange
        double amount = 1000.0;
        double inte = 0.05;
        int npayments = 12;
        //Act
        double expected = amount*(inte*Math.pow((1+inte), npayments)/(Math.pow((1+inte), npayments)-1));
        double actual = bank.payment(amount, inte, npayments);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void PaymentNegativeAmount(){
        //Arrange
        double amount = -1000.0; //Deberia de controlar que no se pudiera pagar un total_amount negativo
        double inte = 0.05;
        int npayments = 12;
        //Act
        double expected = amount*(inte*Math.pow((1+inte), npayments)/(Math.pow((1+inte), npayments)-1));
        double actual = bank.payment(amount, inte, npayments);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void PaymentWithNoInterest(){
        //Arrange
        double amount = 1000.0;
        double inte = 0.0;
        int npayments = 12;
        //Act
        double expected = amount/npayments;
        double actual = bank.payment(amount, inte, npayments);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void PaymentWithNoNPayments(){
        //Arrange
        double amount = 1000.0;
        double inte = 0.1;
        int npayments = 0;
        //Act
        double expected = 0;
        double actual = bank.payment(amount, inte, npayments);
        //Assert
        assertEquals(expected, actual);
    }


    @Test
    void PendingAmountIn0Months(){
        double amount = 1000.0;
        double inte = 0.05;
        int npayments = 12;
        int month = 0;
        assertEquals(bank.pending(amount, inte, npayments, month), amount);
    }


    @Test
    void PendingAmount(){
        double amount = 1000.0;
        double inte = 0.05;
        int npayments = 12;
        int month = 4;
        assertTrue(amount > bank.pending(amount, inte, npayments, month));
    }

}
