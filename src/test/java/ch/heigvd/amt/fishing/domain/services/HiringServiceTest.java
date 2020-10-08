package ch.heigvd.amt.fishing.domain.services;

import ch.heigvd.amt.fishing.domain.Fisherman;
import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HiringServiceTest {

  @Mock
  Wallet wallet;

  HiringService hiringService = new HiringService();

  @Test
  void iCanPurchaseAFishingRodIfIHaveEnoughCash() throws NoMoreMoneyInWalletException {
    hiringService.hire(wallet);
    verify(wallet).takeCashOut(Fisherman.SALARY);
  }

  @Test
  void iCannotPurchaseAFishingRodIfIHaveEnoughCash() throws NoMoreMoneyInWalletException {

    doThrow(NoMoreMoneyInWalletException.class).when(wallet).takeCashOut(Fisherman.SALARY);

    assertThrows(NoMoreMoneyInWalletException.class, () -> {
      hiringService.hire(wallet);
    });
  }


}