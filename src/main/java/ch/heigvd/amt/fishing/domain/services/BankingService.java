package ch.heigvd.amt.fishing.domain.services;

import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.exceptions.WithdrawalLimitReachedException;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BankingService {

  public final static int MAX_WITHDRAWAL_AMOUNT = 1000000;

  public void fillWalletWithCash(Wallet wallet, int budget) throws WithdrawalLimitReachedException {
    Logger.getAnonymousLogger().info("Trying to get " + budget + " francs out of the magic bank into the wallet...");
    if (budget > MAX_WITHDRAWAL_AMOUNT) {
      Logger.getAnonymousLogger().info("Ooops");
      throw new WithdrawalLimitReachedException();
    }
    Logger.getAnonymousLogger().info("Yeah!");
    wallet.putCashIn(budget);
  }

}
