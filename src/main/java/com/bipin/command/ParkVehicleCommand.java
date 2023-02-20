package com.bipin.command;

import com.bipin.exception.InvalidCommandException;
import com.bipin.exception.SlotNotAvailableException;
import com.bipin.model.Ticket;
import com.bipin.model.Vehicle;
import com.bipin.model.enums.VehicleType;
import com.bipin.service.ParkingLotService;

public class ParkVehicleCommand implements InputCommand {
  private ParkingLotService parkingLotService = ParkingLotService.getInstance();

  @Override
  public void executeCommand(String command) {
    try {
      String[] commandParams = command.split("\\s+");
      if (!(commandParams.length == 4))
        throw new InvalidCommandException(
            "command must contain vehicleType, vehicleNumber & color");
      VehicleType vehicleType = VehicleType.valueOf(commandParams[1]);
      String vehicleNumber = commandParams[2];
      String color = commandParams[3];
      Vehicle vehicle =
          new Vehicle()
              .setId(vehicleNumber)
              .setVehicleNumber(vehicleNumber)
              .setColor(color)
              .setType(vehicleType);
      Ticket ticket = parkingLotService.parkVehicle(vehicle);
      System.out.printf("Parked vehicle. Ticket ID %s %n", ticket.getId());
    } catch (SlotNotAvailableException exception) {
      System.out.println("Parking Lot Full");
    }
  }
}
