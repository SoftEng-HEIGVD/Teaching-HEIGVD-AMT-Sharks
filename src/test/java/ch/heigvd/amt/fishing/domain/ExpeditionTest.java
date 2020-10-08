package ch.heigvd.amt.fishing.domain;

import ch.heigvd.amt.fishing.domain.equipment.Boat;
import ch.heigvd.amt.fishing.domain.equipment.BoatType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ExpeditionTest {

  @Mock
  Fisherman unluckyFisherman;

  @Mock
  Fisherman luckyFisherman;

  @BeforeEach
  private void briefCrew() {
    lenient().when(unluckyFisherman.tryToCatchTheShark()).thenReturn(Optional.empty());
    lenient().when(luckyFisherman.tryToCatchTheShark()).thenReturn(Optional.of(new Shark()));
  }

  @Test
  public void itShouldReturnAnEmptyNetWhenNobodyCatchesAnyShark() {
    Expedition expedition = Expedition.builder()
      .crewMember(unluckyFisherman)
      .boat(Boat.builder().type(BoatType.SMALL).build())
      .build();
    expedition.prepare();
    Set<Shark> outcome = expedition.goToSeaAndReturnWithSharks();
    assertEquals(0, outcome.size());
  }

  @Test
  public void itShouldNotReturnAnEmptyNetWhenSomeoneCatchesAShark() {
    Expedition expedition = Expedition.builder()
      .crewMember(luckyFisherman)
      .boat(Boat.builder().type(BoatType.SMALL).build())
      .build();
    expedition.prepare();
    Set<Shark> outcome = expedition.goToSeaAndReturnWithSharks();
    assertNotEquals(0, outcome.size());
  }

}