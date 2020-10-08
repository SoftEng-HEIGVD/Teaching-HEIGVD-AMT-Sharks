package ch.heigvd.amt.fishing.domain;

import ch.heigvd.amt.fishing.domain.equipment.Boat;
import lombok.Builder;
import lombok.Singular;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Builder
public class Expedition {

  private Boat boat;

  @Singular("crewMember")
  private List<Fisherman> crew;

  public void prepare() {
    Logger.getAnonymousLogger().info("Get people ready to leave!");
    for (Fisherman crewMember : crew) {
      crewMember.getReady();
    }
  }

  public Set<Shark> goToSeaAndReturnWithSharks() {
    Logger.getAnonymousLogger().info("Let's go and get those sharks!");
    Set<Shark> capturedSharks = new HashSet<>();

    boat.putOnWater();
    boat.startTheEngine();

    for (Fisherman crewMember : crew) {
      Optional<Shark> shark = crewMember.tryToCatchTheShark();
      if (!shark.isEmpty()) {
        capturedSharks.add(shark.get());
      }
    }
    return capturedSharks;
  }

}
