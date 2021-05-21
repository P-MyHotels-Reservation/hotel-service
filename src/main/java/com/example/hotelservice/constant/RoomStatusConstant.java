package com.example.hotelservice.constant;

public enum RoomStatusConstant {
  ACTIVE("ACTIVE"),
  BOOKED("BOOKED"),
  INACTIVE("INACTIVE");

  private String value;

  RoomStatusConstant(String value) {
    this.value = value;
  }

  public static RoomStatusConstant fromValue(String value) {
    for (RoomStatusConstant object : values()) {
      if (object.getValue().equals(value)) {
        return object;
      }
    }
    return null;
  }

  public String getValue() {
    return value;
  }
}
