package ch.heigvd.amt.fishing.domain.services;

import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.equipment.Bait;
import ch.heigvd.amt.fishing.domain.equipment.Boots;
import ch.heigvd.amt.fishing.domain.equipment.FishingRod;
import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class ShoppingMallService {

  public FishingRod purchaseFishingRod(Wallet wallet) throws NoMoreMoneyInWalletException {
    Logger.getAnonymousLogger().info("Preparing cash...");
    wallet.takeCashOut(FishingRod.PRICE);
    Logger.getAnonymousLogger().info("Purchasing a fishing rod");
    return new FishingRod();
  }

  public Boots purchaseBoots(Wallet wallet) throws NoMoreMoneyInWalletException {
    Logger.getAnonymousLogger().info("Preparing cash...");
    wallet.takeCashOut(Boots.PRICE);
    Logger.getAnonymousLogger().info("Purchasing boots");
    return new Boots();
  }

  public Bait purchaseBait(Wallet wallet) throws NoMoreMoneyInWalletException {
    Logger.getAnonymousLogger().info("Preparing cash...");
    wallet.takeCashOut(Bait.PRICE);
    Logger.getAnonymousLogger().info("Purchasing bait");
    return new Bait();
  }

}
