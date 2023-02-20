package com.bipin;

import com.bipin.command.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.bipin.constant.Commands.*;

public class ParkingLotApplication {
  public static void main(String[] args) throws IOException {
    //
    Map<String, InputCommand> commandMap = new HashMap<>();
    commandMap.put(CREATE_PARKING_LOT, new CreateParkingLotCommand());
    commandMap.put(PARK_VEHICLE, new ParkVehicleCommand());
    commandMap.put(UNPARK_VEHICLE, new UnparkVehicleCommand());
    commandMap.put(DISPLAT_FREE_SLOTS, new DisplayFreeSlotsCommand());
    commandMap.put(DISPLAT_FREE_COUNT, new DisplayFreeCountCommand());

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String inputLine;
    while ((inputLine = bf.readLine()) != null) {
      String[] command = inputLine.split("\\s+");
      InputCommand inputCommand = commandMap.get(command[0]);
      if (Objects.nonNull(inputCommand)) {
        try {
          inputCommand.executeCommand(inputLine);
        } catch (RuntimeException exception) {
          System.out.println(exception.getMessage());
        }
      } else
        System.out.println(
            "supported operations:\n"
                + "create_parking_lot\n"
                + "display_free_count\n"
                + "display_free_slots\n"
                + "park_vehicle\n"
                + "unpark_vehicle");
    }
  }
}
