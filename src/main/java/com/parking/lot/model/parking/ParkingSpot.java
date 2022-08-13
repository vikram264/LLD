package com.parking.lot.model.parking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpot {
    String parkingSpotId;
    ParkingSpotType parkingSpotType;
    boolean isFree = true;
    String vehicleId;

    public ParkingSpot(String parkingSpotId, ParkingSpotType parkingSpotType) {
        this.parkingSpotId = parkingSpotId;
        this.parkingSpotType = parkingSpotType;
    }


    public void assignVehicleToSpot(String vehicleId) {
        this.vehicleId = vehicleId;
        isFree = false;

    }
    public void freeSpot() {
        this.isFree = true;
        this.vehicleId = null;

    }


}
