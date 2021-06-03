package com.example.hotelservice.constant;

public enum HotelStatusConstant {
  PLANNED("PLANNED"),
  ACTIVE("ACTIVE"),
  INACTIVE("INACTIVE");

  private String value;

  HotelStatusConstant(String value) {
    this.value = value;
  }

  public static HotelStatusConstant fromValue(String value) {
    for (HotelStatusConstant object : values()) {
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
