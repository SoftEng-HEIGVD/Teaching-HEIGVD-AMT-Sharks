package ch.heigvd.amt.fishing.domain.equipment;

import java.util.logging.Logger;

public class Bait {

  public final static int PRICE = 10;

  public void takeOutOfCan() {
    Logger.getAnonymousLogger().info("bait is taken out of can");
  }

  public void putOnHook() {
    Logger.getAnonymousLogger().info("bait is put on hook");
  }

}
