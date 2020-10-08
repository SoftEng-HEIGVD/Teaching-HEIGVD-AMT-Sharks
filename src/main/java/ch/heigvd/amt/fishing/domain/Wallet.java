package ch.heigvd.amt.fishing.domain;

import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.logging.Logger;

@EqualsAndHashCode
@Getter
public class Wallet {

  private int cashAmount;

  public void putCashIn(int amount) {
    Logger.getAnonymousLogger().info("Putting " + amount + " into wallet");
    cashAmount += amount;
  }

  public void takeCashOut(int amount) throws NoMoreMoneyInWalletException {
    Logger.getAnonymousLogger().info("Trying to get " + amount + " out of money");
    if (cashAmount < amount) {
      throw new NoMoreMoneyInWalletException();
    }
    cashAmount -= amount;
  }

}
