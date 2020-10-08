package ch.heigvd.amt.fishing.domain.services;

import ch.heigvd.amt.fishing.domain.Fisherman;
import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class HiringService {

  public Fisherman hire(Wallet wallet) throws NoMoreMoneyInWalletException {
    Logger.getAnonymousLogger().info("Preparing cash for salary");
    wallet.takeCashOut(Fisherman.SALARY);
    Logger.getAnonymousLogger().info("Hiring a crew member");
    return new Fisherman();
  }

}
