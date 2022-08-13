package com.parking.lot.model.parking;

import com.parking.lot.model.exception.ParkingLotException;
import com.parking.lot.model.vehicle.Vehicle;
import com.parking.lot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
@Setter
@Getter
public class ParkingFloor {
    String parkingFloorId;

    Map<ParkingSpotType, List<ParkingSpot>> availableSpots;
    Map<String, ParkingSpot> usedSpots;


    public ParkingFloor(String parkingFloorId) {
        this.parkingFloorId = parkingFloorId;
        availableSpots.put(ParkingSpotType.BIKE, new ArrayList<>());
        availableSpots.put(ParkingSpotType.EBIKE, new ArrayList<>());
        availableSpots.put(ParkingSpotType.ELECTRIC_CAR, new ArrayList<>());
        availableSpots.put(ParkingSpotType.CAR, new ArrayList<>());
        availableSpots.put(ParkingSpotType.LARGE, new ArrayList<>());

    }

    public boolean isFloorFull() {
        BitSet fullBitSet = new BitSet();
        int bitIndex = 0;
        for (Map.Entry<ParkingSpotType, List<ParkingSpot>> entry : availableSpots.entrySet()) {
            if (entry.getValue().size() == 0) {
                fullBitSet.set(bitIndex++);
            } else {
                break;
            }
        }
        return fullBitSet.cardinality() == fullBitSet.size();
    }


    public ParkingSpot getRelevantParkingSpot(Vehicle vehicle) throws ParkingLotException {

        VehicleType vehicleType = vehicle.getVehicleType();
        if(!canPark(vehicleType)) throw new ParkingLotException("Vehicle cannot be parked");
        ParkingSpotType parkingSpotType = getCorrectParkingSpotType(vehicleType);
        List<ParkingSpot> parkingSpots =  availableSpots.get(parkingSpotType);
        ParkingSpot parkingSpot = null;
        for(ParkingSpot p : parkingSpots) {
            if(p.isFree()) {
                p.assignVehicleToSpot(vehicle.getLicensePlateId());
                parkingSpot = p;
                // important
                parkingSpots.remove(p);
                break;
            }
        }
        usedSpots.put(parkingSpot.getParkingSpotId(),parkingSpot);
        return parkingSpot;
    }


    public ParkingSpotType getCorrectParkingSpotType(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return ParkingSpotType.CAR;
            case ELECTRIC_CAR:
                return ParkingSpotType.ELECTRIC_CAR;
            case BIKE:
                return ParkingSpotType.BIKE;
            case EBIKE:
                return ParkingSpotType.EBIKE;
            default:
                return ParkingSpotType.LARGE;
        }
    }

    public boolean canPark(VehicleType vehicleType) {
       ParkingSpotType p =  getCorrectParkingSpotType(vehicleType);
        return availableSpots.get(p).size()>0;
    }


    public ParkingSpot vacateSpot(String spotId) {
       ParkingSpot p = usedSpots.remove(spotId);
       p.freeSpot();
       availableSpots.get(p.getParkingSpotType()).add(0,p);
       return p;
    }

}
