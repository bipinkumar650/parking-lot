package com.bipin.command;

import com.bipin.exception.InvalidCommandException;
import com.bipin.model.enums.VehicleType;
import com.bipin.service.ParkingLotService;

import java.util.LinkedHashMap;
import java.util.List;

public class DisplayFreeCountCommand implements InputCommand {
  ParkingLotService parkingLotService = ParkingLotService.getInstance();

  @Override
  public void executeCommand(String command) {
    String[] commandParams = command.split("\\s+");
    if (!(commandParams.length == 2))
      throw new InvalidCommandException(
              "command must contain vehicleType");
    VehicleType vehicleType = VehicleType.valueOf(commandParams[1]);
    LinkedHashMap<Integer, List<Integer>> availableSlots =
        parkingLotService.displayAvailableSlots(vehicleType);
    availableSlots.forEach(
        (key, value) -> System.out.printf("Free slots count for %s on Floor %d is %d%n", vehicleType, key, value.size()));
  }
}
