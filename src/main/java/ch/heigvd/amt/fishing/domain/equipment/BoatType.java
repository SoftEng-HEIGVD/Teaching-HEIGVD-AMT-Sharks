package ch.heigvd.amt.fishing.domain.equipment;

public enum BoatType {

  SMALL(1 * 1000),
  MEDIUM(10 * 1000),
  LARGE(100 * 1000);

  private final int price;

  private BoatType(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }
}
