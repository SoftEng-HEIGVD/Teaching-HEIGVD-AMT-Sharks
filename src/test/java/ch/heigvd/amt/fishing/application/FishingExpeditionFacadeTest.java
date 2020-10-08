package ch.heigvd.amt.fishing.application;

import ch.heigvd.amt.fishing.domain.Fisherman;
import ch.heigvd.amt.fishing.domain.equipment.BoatType;
import ch.heigvd.amt.fishing.domain.services.BankingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import static org.junit.jupiter.api.Assertions.*;

class FishingExpeditionFacadeTest {

  static SeContainerInitializer initializer;

  @BeforeAll
  public static void setupCdi() {
    initializer = SeContainerInitializer.newInstance();
  }

  // @Test
  public void iDoNotHaveUnlimitedBudget() {
    try (SeContainer container = initializer.initialize()) {
      FishingExpeditionFacade expeditionFacade = container.select(FishingExpeditionFacade.class).get();
      ExpeditionOutcomeDTO outcome = expeditionFacade.launchExpedition(LaunchExpeditionCommand.builder()
        .boatType(BoatType.SMALL)
        .budget(BankingService.MAX_WITHDRAWAL_AMOUNT + 1)
        .crewSize(4)
        .build());
      assertNotNull(outcome);
      assertEquals("Expedition aborted because your banker was not happy... nothing is lost.", outcome.getMessage());
    }
  }

  // @Test
  public void ifIDoNotManageMyBudgetCarefullyThenIHaveProblems() {
    try (SeContainer container = initializer.initialize()) {
      FishingExpeditionFacade expeditionFacade = container.select(FishingExpeditionFacade.class).get();
      ExpeditionOutcomeDTO outcome = expeditionFacade.launchExpedition(LaunchExpeditionCommand.builder()
        .boatType(BoatType.SMALL)
        .budget(Fisherman.SALARY * 4 - 1)
        .crewSize(4)
        .build());
      assertNotNull(outcome);
      assertEquals("Expedition aborted because of careless budgeting... you have purchased stuff that you cannot use, what a waste!", outcome.getMessage());
    }

  }

}