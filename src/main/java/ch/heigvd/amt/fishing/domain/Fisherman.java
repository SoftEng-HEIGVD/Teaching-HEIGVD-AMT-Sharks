package ch.heigvd.amt.fishing.domain;

import ch.heigvd.amt.fishing.domain.equipment.Bait;
import ch.heigvd.amt.fishing.domain.equipment.Boots;
import ch.heigvd.amt.fishing.domain.equipment.FishingRod;

import java.util.Optional;
import java.util.logging.Logger;

public class Fisherman {

  public final static int SALARY = 1000;

  private Boots boots;
  private FishingRod fishingRod;
  private Bait bait;

  public void equip(Boots boots, FishingRod fishingRod, Bait bait) {
    Logger.getAnonymousLogger().info("Crew member is being equipped");
    this.boots = boots;
    this.fishingRod = fishingRod;
    this.bait = bait;
  }

  public void getReady() {
    Logger.getAnonymousLogger().info("Crew member puts boots on");
    boots.putOn();
  }

  public Optional<Shark> tryToCatchTheShark() {
    Logger.getAnonymousLogger().info("Crew member tries his luck...");
    bait.takeOutOfCan();
    bait.putOnHook();
    fishingRod.throwInTheAir();
    fishingRod.pullStrongly();
    if (Math.random()*1000 < 200) {
      Logger.getAnonymousLogger().info("We got on!!!!");
      return Optional.of(new Shark());
    } else {
      Logger.getAnonymousLogger().info("Missed it...");
      return Optional.empty();
    }
  }


}
