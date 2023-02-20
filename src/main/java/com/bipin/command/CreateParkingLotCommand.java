package com.bipin.command;

import com.bipin.exception.InvalidCommandException;
import com.bipin.service.ParkingLotService;

public class CreateParkingLotCommand implements InputCommand {
  ParkingLotService parkingLotService = ParkingLotService.getInstance();

  @Override
  public void executeCommand(String command) {
    String[] commandParams = command.split("\\s+");
    if (!(commandParams.length == 4))
      throw new InvalidCommandException(
          "command must contain parkingLotId, numFloors & numSlotPerFloor");
    String parkingLotId = commandParams[1];
    Integer numFloors = Integer.valueOf(commandParams[2]);
    Integer numSlotPerFloor = Integer.valueOf(commandParams[3]);
    parkingLotService.createParkingLot(parkingLotId, numFloors, numSlotPerFloor);
    System.out.printf(
        "Created parking lot with %d floors & %d slots per floor%n", numFloors, numSlotPerFloor);
  }
}
