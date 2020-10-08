package ch.heigvd.amt.fishing.domain;

import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

  private Wallet wallet;

  @BeforeEach
  public void prepareWallet() {
    this.wallet = new Wallet();
  }

  @Test
  void iCanTakeOutWhatIPutIn() {
    wallet.putCashIn(1000);
    assertDoesNotThrow(() -> {
      wallet.takeCashOut(1000);
    });
  }

  @Test
  void iCannotTakeOutMoreThanWhatIPutIn() {
    wallet.putCashIn(1000);
    assertThrows(NoMoreMoneyInWalletException.class, () -> {
      wallet.takeCashOut(1001);
    });
  }

  @Test
  void itKeepsACorrectBalance() throws NoMoreMoneyInWalletException {
    wallet.putCashIn(1000);
    wallet.takeCashOut(100);
    wallet.takeCashOut(10);
    wallet.putCashIn(1);
    assertEquals(891, wallet.getCashAmount());
  }

}