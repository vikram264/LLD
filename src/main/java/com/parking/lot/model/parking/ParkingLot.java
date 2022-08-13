package com.parking.lot.model.parking;

import com.parking.lot.model.account.common.Address;
import com.parking.lot.model.exception.ParkingLotException;
import com.parking.lot.model.vehicle.Vehicle;
import com.parking.lot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
@Getter
@Setter
public class ParkingLot {

    private String parkingLotId;
    private Address address;

    List<ParkingFloor> parkingFloors;
    List<EntryPanel> entryPanels;
    List<ExitPanel> exitPanels;

    public static final ParkingLot INSTANCE = new ParkingLot();

    private ParkingLot() {
        this.parkingFloors = new ArrayList<>();
        this.entryPanels = new ArrayList<>();
        this.exitPanels = new ArrayList<>();
    }

    public boolean isFull() {
        int index = 0;
        BitSet bitSet = new BitSet();
        for (ParkingFloor parkingFloor : parkingFloors) {
            bitSet.set(index++, parkingFloor.isFloorFull());
        }
        return bitSet.cardinality() == bitSet.size();
    }


    public boolean canPark(VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            if (parkingFloor.canPark(vehicleType))
                return true;
        }
        return false;
    }


    public ParkingSpot getParkingSpot(Vehicle vehicle) throws ParkingLotException {
        for (ParkingFloor parkingFloor : ParkingLot.INSTANCE.getParkingFloors()) {
            ParkingSpot parkingSpot = parkingFloor.getRelevantParkingSpot(vehicle);
            if (parkingSpot != null) {
                return parkingSpot;
            }
        }
        return null;
    }

    public ParkingSpot vacateParking(String spotId) {
        for (ParkingFloor parkingFloor : ParkingLot.INSTANCE.getParkingFloors()) {
            ParkingSpot parkingSpot = parkingFloor.vacateSpot(spotId);
            if (parkingSpot != null) {
                return parkingSpot;
            }
        }
        return null;
    }


}
