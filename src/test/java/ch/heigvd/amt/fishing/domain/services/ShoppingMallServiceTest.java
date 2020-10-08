package ch.heigvd.amt.fishing.domain.services;

import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.equipment.FishingRod;
import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ShoppingMallServiceTest {

  @Mock
  Wallet wallet;

  ShoppingMallService shoppingMallService = new ShoppingMallService();

  @Test
  void iCanPurchaseAFishingRodIfIHaveEnoughCash() throws NoMoreMoneyInWalletException {

    shoppingMallService.purchaseFishingRod(wallet);

    verify(wallet).takeCashOut(FishingRod.PRICE);
  }

  @Test
  void iCannotPurchaseAFishingRodIfIHaveEnoughCash() throws NoMoreMoneyInWalletException {

    doThrow(NoMoreMoneyInWalletException.class).when(wallet).takeCashOut(FishingRod.PRICE);

    assertThrows(NoMoreMoneyInWalletException.class, () -> {
      shoppingMallService.purchaseFishingRod(wallet);
    });
  }

}