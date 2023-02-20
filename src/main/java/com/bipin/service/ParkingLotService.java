package com.bipin.service;

import com.bipin.exception.InvalidTicketException;
import com.bipin.exception.SlotNotAvailableException;
import com.bipin.model.*;
import com.bipin.model.enums.SlotType;
import com.bipin.model.enums.VehicleType;

import java.util.*;

public class ParkingLotService {
  private ParkingLot parkingLot;
  private static ParkingLotService parkingLotService = null;
  private final TicketService ticketService = new TicketService();

  public static ParkingLotService getInstance() {
    if (Objects.isNull(parkingLotService)) {
      parkingLotService = new ParkingLotService();
    }
    return parkingLotService;
  }

  public void createParkingLot(String parkingLotId, Integer numFloors, Integer numSlotPerFloor) {
    this.parkingLot = new ParkingLot();
    this.parkingLot.setId(parkingLotId);
    this.parkingLot.setNumFloor(numFloors);
    this.parkingLot.setNumSlotPerFloor(numSlotPerFloor);

    for (int i = 0; i < numFloors; i++) {
      addFloorToParkingLot(i);
      for (int j = 0; j < numSlotPerFloor; j++) {
        addSlotToParkingFloor(i, j);
      }
    }
  }

  private void addFloorToParkingLot(Integer floorNumber) {
    Floor floor = new Floor();
    String floorId = String.valueOf(floorNumber);
    floor.setId(floorId);
    floor.setFloorNumber(floorNumber);
    this.parkingLot.getFloors().put(floorNumber, floor);
  }

  private void addSlotToParkingFloor(Integer floorNumber, Integer slotNumber) {
    Slot slot = new Slot();
    slot.setId(String.valueOf(slotNumber));
    slot.setSlotNumber(slotNumber);
    slot.setFloorNumber(floorNumber);
    if (slotNumber == 0) slot.setSlotType(SlotType.TRUCK);
    else if (slotNumber == 1 || slotNumber == 2) slot.setSlotType(SlotType.BIKE);
    else slot.setSlotType(SlotType.CAR);
    this.parkingLot.getFloors().get(floorNumber).getSlots().put(slotNumber, slot);
  }

  public Ticket parkVehicle(Vehicle vehicle) {
    Slot availableSlot = findNearestAvailableSlot(vehicle.getType());
    if (Objects.isNull(availableSlot))
      throw new SlotNotAvailableException(
          "Slot not available for vehicle type: " + vehicle.getType());
    else availableSlot.setCurrentVehicle(vehicle).setOccupiedAt(new Date());
    Ticket ticket =
        ticketService.generateTicket(
            this.parkingLot.getId(),
            vehicle.getVehicleNumber(),
            availableSlot.getFloorNumber(),
            availableSlot.getSlotNumber());
    return ticket;
  }

  public Slot findNearestAvailableSlot(VehicleType vehicleType) {
    for (int i = 0; i < this.parkingLot.getNumFloor(); i++) {
      for (int j = 0; j < this.parkingLot.getNumSlotPerFloor(); j++) {
        Slot slot = this.parkingLot.getFloors().get(i).getSlots().get(j);
        if (slot.getSlotType().name().equals(vehicleType.name())
            && Objects.isNull(slot.getCurrentVehicle())) {
          return slot;
        }
      }
    }
    return null;
  }

  public void unparkVehicle(String ticketId) {
    Integer floorNumber = ticketService.getFloorNumber(ticketId);
    Integer slotNumber = ticketService.getSlotNumber(ticketId);
    Slot slot = this.parkingLot.getFloors().get(floorNumber).getSlots().get(slotNumber);
    if (Objects.isNull(slot.getCurrentVehicle())) throw new InvalidTicketException("Invalid slot");
    else {
      slot.setCurrentVehicle(null);
      slot.setOccupiedAt(null);
    }
  }

  public LinkedHashMap<Integer, List<Integer>> displayAvailableSlots(VehicleType vehicleType) {
    LinkedHashMap<Integer, List<Integer>> availableSlots = new LinkedHashMap<>();
    for (int i = 0; i < parkingLot.getNumFloor(); i++) {
      List<Integer> availableSlotsPerFloor = new ArrayList<>();
      this.parkingLot
          .getFloors()
          .get(i)
          .getSlots()
          .values()
          .forEach(
              slot -> {
                if (Objects.isNull(slot.getCurrentVehicle())
                    && slot.getSlotType().name().equals(vehicleType.name()))
                  availableSlotsPerFloor.add(slot.getSlotNumber());
              });
      availableSlots.put(i, availableSlotsPerFloor);
    }
    return availableSlots;
  }
}
