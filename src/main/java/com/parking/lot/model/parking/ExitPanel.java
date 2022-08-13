package com.parking.lot.model.parking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExitPanel {
    String id;

    public ParkingTicket scanAndVacate(ParkingTicket parkingTicket) {
       ParkingSpot ps =  ParkingLot.INSTANCE.vacateParking(parkingTicket.getParkingSpotId());
       parkingTicket.setCharges(calculateCharges(parkingTicket,ps.getParkingSpotType()));
       return parkingTicket;
    }

    private double calculateCharges(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
        long hours = duration.toHours();
        if (hours == 0)
            hours = 1;
        double amount = hours * new HourlyCost().getCost(parkingSpotType);
        return amount;
    }

}
