package com.bipin.model;

import com.bipin.model.enums.SlotType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Slot {
  String id;
  Integer slotNumber;
  Integer floorNumber;
  SlotType slotType;
  Vehicle currentVehicle;
  Date occupiedAt;
}
