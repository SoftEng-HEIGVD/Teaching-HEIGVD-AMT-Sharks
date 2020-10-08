package ch.heigvd.amt.fishing.domain.services;

import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.exceptions.WithdrawalLimitReachedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BankingServiceTest {

  @Mock
  Wallet wallet;

  BankingService bankingService = new BankingService();

  @Test
  void itDoesNotLetMeTakeOutCashBeyondLimit() {
    assertThrows(WithdrawalLimitReachedException.class, () -> {
      bankingService.fillWalletWithCash(wallet, BankingService.MAX_WITHDRAWAL_AMOUNT + 1);
    });
  }

  @Test
  void itLetsMeWithdrawMoney() {
    assertDoesNotThrow(() -> {
      bankingService.fillWalletWithCash(wallet, BankingService.MAX_WITHDRAWAL_AMOUNT );
    });
    verify(wallet).putCashIn(BankingService.MAX_WITHDRAWAL_AMOUNT);
  }

}