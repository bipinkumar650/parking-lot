package com.bipin.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;

@Data
@Accessors(chain = true)
public class Floor {
  String id;
  Integer floorNumber;
  LinkedHashMap<Integer, Slot> slots = new LinkedHashMap<>();
}
