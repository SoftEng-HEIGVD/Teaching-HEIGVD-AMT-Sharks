package ch.heigvd.amt.fishing.application;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExpeditionOutcomeDTO {
  private final String message;
  private int numberOfCapturedSharks;
}
