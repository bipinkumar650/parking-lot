package com.bipin.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Ticket {
  String id;
  String vehicleNumber;
  Integer floorNumber;
  Integer slotNumber;
  Date createdAt;
}
