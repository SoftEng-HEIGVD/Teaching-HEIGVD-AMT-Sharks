package ch.heigvd.amt.fishing.domain.equipment;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.logging.Logger;

@Builder
@EqualsAndHashCode
@Getter
public class Boat {

  private BoatType type;

  public void putOnWater() {
    Logger.getAnonymousLogger().info("boat goes on water");
  }

  public void startTheEngine() {
    Logger.getAnonymousLogger().info("boat engine is started");
  }

}
