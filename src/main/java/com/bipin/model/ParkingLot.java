package com.bipin.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;

@Data
@Accessors(chain = true)
public class ParkingLot {
  String id;
  Integer numFloor;
  Integer numSlotPerFloor;
  LinkedHashMap<Integer, Floor> floors = new LinkedHashMap<>();
}
