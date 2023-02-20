package com.bipin.service;

import com.bipin.model.Ticket;

import java.util.Date;

public class TicketService {
  public Ticket generateTicket(
      String parkingLotId, String vehicleNumber, Integer floorNumber, Integer slotNumber) {
    String ticketId = getTicketId(parkingLotId, floorNumber, slotNumber);
    return new Ticket()
        .setId(ticketId)
        .setFloorNumber(floorNumber)
        .setSlotNumber(slotNumber)
        .setVehicleNumber(vehicleNumber)
        .setCreatedAt(new Date());
  }

  public String getTicketId(String parkingLotId, Integer floorNumber, Integer slotNumber) {
    return parkingLotId + "_" + floorNumber + "_" + slotNumber;
  }

  public Integer getFloorNumber(String ticketId) {
    String[] splitTicket = ticketId.split("_");
    return Integer.valueOf(splitTicket[1]);
  }

  public Integer getSlotNumber(String ticketId) {
    String[] splitTicket = ticketId.split("_");
    return Integer.valueOf(splitTicket[2]);
  }
}
