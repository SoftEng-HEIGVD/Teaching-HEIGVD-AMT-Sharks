package ch.heigvd.amt.fishing.domain.services;

import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.equipment.Boat;
import ch.heigvd.amt.fishing.domain.equipment.BoatType;
import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BoatManufacturingService {

  public Boat purchaseBoat(BoatType boatType, Wallet wallet) throws NoMoreMoneyInWalletException {
    Logger.getAnonymousLogger().info("Taking cash to buy boat: " + boatType);
    wallet.takeCashOut(boatType.getPrice());
    Logger.getAnonymousLogger().info("Purchasing a boat: " + boatType);
    return Boat.builder()
      .type(boatType)
      .build();
  }

}
