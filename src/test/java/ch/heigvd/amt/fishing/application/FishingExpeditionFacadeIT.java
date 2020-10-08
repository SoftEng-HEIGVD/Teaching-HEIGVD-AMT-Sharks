package ch.heigvd.amt.fishing.application;

import ch.heigvd.amt.fishing.domain.equipment.BoatType;
import ch.heigvd.amt.fishing.domain.services.BankingService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Arquillian.class)
public class FishingExpeditionFacadeIT {

  private final static String WARNAME = "arquillian-managed.war";

  @Inject
  FishingExpeditionFacade fishingExpeditionFacade;

  @Deployment(testable = true)
  public static WebArchive createDeployment() {
    WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
      .addPackages(true, "ch.heigvd.amt");
    return archive;
  }

  @Test
  public void launchExpeditionWithProperBudgeting() {
    LaunchExpeditionCommand launchExpeditionCommand = LaunchExpeditionCommand.builder()
      .budget(BankingService.MAX_WITHDRAWAL_AMOUNT)
      .crewSize(10)
      .boatType(BoatType.SMALL)
      .build();
    ExpeditionOutcomeDTO outcome = fishingExpeditionFacade.launchExpedition(launchExpeditionCommand);
    assertFalse(outcome.getMessage().startsWith("Expedition aborted"));
  }

  @Test
  public void launchExpeditionWithoutEnoughMoneyInTheBank() {
    LaunchExpeditionCommand launchExpeditionCommand = LaunchExpeditionCommand.builder()
      .budget(BankingService.MAX_WITHDRAWAL_AMOUNT + 1)
      .crewSize(1)
      .boatType(BoatType.SMALL)
      .build();
    ExpeditionOutcomeDTO outcome = fishingExpeditionFacade.launchExpedition(launchExpeditionCommand);
    assertEquals("Expedition aborted because your banker was not happy... nothing is lost.", outcome.getMessage());
  }

  @Test
  public void launchExpeditionWithCarelessBudgeting() {
    LaunchExpeditionCommand launchExpeditionCommand = LaunchExpeditionCommand.builder()
      .budget(1)
      .crewSize(10)
      .boatType(BoatType.SMALL)
      .build();
    ExpeditionOutcomeDTO outcome = fishingExpeditionFacade.launchExpedition(launchExpeditionCommand);
    assertEquals("Expedition aborted because of careless budgeting... you have purchased stuff that you cannot use, what a waste!", outcome.getMessage());
  }

}