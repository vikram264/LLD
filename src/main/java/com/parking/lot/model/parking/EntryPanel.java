package com.parking.lot.model.parking;

import com.parking.lot.model.exception.ParkingLotException;
import com.parking.lot.model.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class EntryPanel {
    String id;

    public ParkingTicket getTicket(Vehicle vehicle) throws ParkingLotException {
        if(!ParkingLot.INSTANCE.canPark(vehicle.getVehicleType())) return null;
        ParkingSpot parkingSpot = ParkingLot.INSTANCE.getParkingSpot(vehicle);
        return createParkingTicket(parkingSpot.parkingSpotId,vehicle.getLicensePlateId());

    }


    private ParkingTicket createParkingTicket(String spotId, String licenseNumber) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setLicenseId(licenseNumber);
        parkingTicket.setParkingSpotId(spotId);
        parkingTicket.setTicketId(UUID.randomUUID().toString());
        parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
        return parkingTicket;
    }
}
