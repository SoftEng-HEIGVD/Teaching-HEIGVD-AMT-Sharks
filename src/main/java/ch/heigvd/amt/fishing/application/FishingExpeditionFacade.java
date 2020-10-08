package ch.heigvd.amt.fishing.application;

import ch.heigvd.amt.fishing.domain.Expedition;
import ch.heigvd.amt.fishing.domain.Fisherman;
import ch.heigvd.amt.fishing.domain.Shark;
import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.equipment.Bait;
import ch.heigvd.amt.fishing.domain.equipment.Boat;
import ch.heigvd.amt.fishing.domain.equipment.Boots;
import ch.heigvd.amt.fishing.domain.equipment.FishingRod;
import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;
import ch.heigvd.amt.fishing.domain.exceptions.WithdrawalLimitReachedException;
import ch.heigvd.amt.fishing.domain.services.BankingService;
import ch.heigvd.amt.fishing.domain.services.BoatManufacturingService;
import ch.heigvd.amt.fishing.domain.services.HiringService;
import ch.heigvd.amt.fishing.domain.services.ShoppingMallService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Set;

@ApplicationScoped
public class FishingExpeditionFacade {

  @Inject
  BankingService bankingService;

  @Inject
  ShoppingMallService shoppingMallService;

  @Inject
  BoatManufacturingService boatManufacturingService;

  public ExpeditionOutcomeDTO launchExpedition(LaunchExpeditionCommand launchExpeditionCommand) {
    Expedition expedition = null;

    try {
      expedition = purchaseEquipmentAndHireCrew(launchExpeditionCommand);
    } catch (WithdrawalLimitReachedException e) {
      return ExpeditionOutcomeDTO.builder()
        .message("Expedition aborted because your banker was not happy... nothing is lost.")
        .build();
    } catch (NoMoreMoneyInWalletException e) {
      return ExpeditionOutcomeDTO.builder()
        .message("Expedition aborted because of careless budgeting... you have purchased stuff that you cannot use, what a waste!")
        .build();
    }

    Set<Shark> capturedSharks = expedition.goToSeaAndReturnWithSharks();
    String message;
    if (capturedSharks.size() == launchExpeditionCommand.getCrewSize()) {
      message = "A-MA-ZING!!!!";
    } else if (capturedSharks.size() > 0) {
      message = "Nice!";
    } else {
      message = "What a shame!";
    }
    return ExpeditionOutcomeDTO.builder()
      .message(message)
      .numberOfCapturedSharks(capturedSharks.size())
      .build();
  }

  private Expedition purchaseEquipmentAndHireCrew(LaunchExpeditionCommand launchExpeditionCommand) throws WithdrawalLimitReachedException, NoMoreMoneyInWalletException {

    Wallet wallet = new Wallet();
    bankingService.fillWalletWithCash(wallet, launchExpeditionCommand.getBudget());

    Boat boat = boatManufacturingService
      .purchaseBoat(launchExpeditionCommand.getBoatType(), wallet);

    Expedition.ExpeditionBuilder expeditionBuilder = Expedition.builder()
      .boat(boat);

    for (int i=0; i<launchExpeditionCommand.getCrewSize(); i++) {
      Boots boots = shoppingMallService.purchaseBoots(wallet);
      FishingRod fishingRod = shoppingMallService.purchaseFishingRod(wallet);
      Bait bait = shoppingMallService.purchaseBait(wallet);
      Fisherman crewMember = new Fisherman();
      crewMember.equip(boots, fishingRod, bait);
      expeditionBuilder.crewMember(crewMember);
    }

    return expeditionBuilder.build();
  }



}
