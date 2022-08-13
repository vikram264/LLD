package com.parking.lot.model.vehicle;

import com.parking.lot.model.parking.ParkingTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    private String licensePlateId;
    private VehicleType vehicleType;
    private ParkingTicket parkingTicket;

    public Vehicle(String licensePlateId, VehicleType vehicleType) {
        this.licensePlateId = licensePlateId;
        this.vehicleType = vehicleType;
    }



}
