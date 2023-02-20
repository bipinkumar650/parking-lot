package com.bipin.command;

import com.bipin.exception.InvalidCommandException;
import com.bipin.exception.InvalidTicketException;
import com.bipin.service.ParkingLotService;

public class UnparkVehicleCommand implements InputCommand {
  private ParkingLotService parkingLotService = ParkingLotService.getInstance();

  @Override
  public void executeCommand(String command) {
    try {
      String[] commandParams = command.split("\\s+");
      if (!(commandParams.length == 2))
        throw new InvalidCommandException("command must contain ticketId");
      String ticketId = commandParams[1];
      parkingLotService.unparkVehicle(ticketId);
    } catch (InvalidTicketException exception) {
      System.out.println("Invalid Ticket");
    }
  }
}
