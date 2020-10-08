package ch.heigvd.amt.fishing.domain.services;

import ch.heigvd.amt.fishing.domain.Wallet;
import ch.heigvd.amt.fishing.domain.equipment.Boat;
import ch.heigvd.amt.fishing.domain.equipment.BoatType;
import ch.heigvd.amt.fishing.domain.exceptions.NoMoreMoneyInWalletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BoatManufacturingServiceTest {

  @Mock
  Wallet wallet;

  @Test
  void iCanPurchaseABoatAndPayTheRightPrice() throws NoMoreMoneyInWalletException {

    BoatManufacturingService boatManufacturingService = new BoatManufacturingService();
    Boat boat = boatManufacturingService.purchaseBoat(BoatType.MEDIUM, wallet);

    assertNotNull(boat);
    assertEquals(BoatType.MEDIUM, boat.getType());
  }

}