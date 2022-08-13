package com.parking.lot.model.account;

import com.parking.lot.model.exception.ParkingLotException;
import com.parking.lot.model.parking.*;

import java.util.Optional;

public class Admin extends Account {

    // Admin Methods..

    //add Parking Floor
    //Add parking spot
    //etc

    public void addParkingFloor(ParkingFloor parkingFloor) {

        Optional<ParkingFloor> floor = ParkingLot.INSTANCE.getParkingFloors().stream().filter(pf -> pf.getParkingFloorId().equals(parkingFloor.getParkingFloorId())).findFirst();

        if(floor.isPresent()) return;

        ParkingLot.INSTANCE.getParkingFloors().add(parkingFloor);

    }

    public void addParkingSpot(String floorId, ParkingSpot parkingSpot) throws ParkingLotException {

        Optional<ParkingFloor> floor = ParkingLot.INSTANCE.getParkingFloors().stream().filter(pf -> pf.getParkingFloorId().equals(floorId)).findFirst();
        if(!floor.isPresent()) throw new ParkingLotException("Floor Not Present");

        Optional<ParkingSpot>  spot = floor.get().getAvailableSpots().get(parkingSpot.getParkingSpotType()).stream().filter(pF -> pF.getParkingSpotId().equals(parkingSpot.getParkingSpotId())).findFirst();
        if(spot.isPresent()) throw new ParkingLotException("Spot Already Present");
        floor.get().getAvailableSpots().get(parkingSpot.getParkingSpotType()).add(parkingSpot);
    }

    public void addEntrancePanel (EntryPanel panel) {
        Optional<EntryPanel> entryPanel = ParkingLot.INSTANCE.getEntryPanels().stream().filter(enP -> enP.getId().equals(panel.getId())).findFirst();
        if(entryPanel.isPresent()) return;
        ParkingLot.INSTANCE.getEntryPanels().add(panel);
    }

    public void addEntrancePanel (ExitPanel panel) {
        Optional<EntryPanel> entryPanel = ParkingLot.INSTANCE.getEntryPanels().stream().filter(enP -> enP.getId().equals(panel.getId())).findFirst();
        if(entryPanel.isPresent()) return;
        ParkingLot.INSTANCE.getExitPanels().add(panel);
    }
}
