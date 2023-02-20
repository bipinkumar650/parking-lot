package com.bipin.model;

import com.bipin.model.enums.VehicleType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Vehicle {
  String id;
  VehicleType type;
  String color;
  String vehicleNumber;
}
