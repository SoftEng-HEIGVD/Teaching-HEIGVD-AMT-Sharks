package ch.heigvd.amt.fishing.application;


import ch.heigvd.amt.fishing.domain.equipment.BoatType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LaunchExpeditionCommand {
  private final int budget;
  private final int crewSize;
  private final BoatType boatType;
}
