package org.example.Parking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingLot {
     private final ParkingManager parkingManager;
     private  final FareCalculator fareCalculator;
    public ParkingLot(ParkingManager parkingManager, FareCalculator fareCalculator) {
        this.parkingManager = parkingManager;
        this.fareCalculator = fareCalculator;
    }
    public  Ticket enterVehicle(Vehicle vehicle){
        ParkingSpot spot = parkingManager.findSpotForVehicle(vehicle);
        if(spot != null){
            Ticket ticket = new Ticket("1",vehicle,spot, LocalDateTime.now());
            return  ticket;
        }else{
            return  null;
        }
    }
    public void leaveVehicle(Ticket ticket){
        if(ticket != null && ticket.getExitTime() == null){
            ticket.setExitTime(LocalDateTime.now());
            parkingManager.unparkVehicle(ticket.getVehicle());

            BigDecimal fare =  fareCalculator.calculateFare(ticket);
        }else{
            return ;
        }
    }
}
